### Helium Off-chain Poc extractor

This project aim to extract the data coming from off-PoC helium foundation and build information about
hotspots health. It also moves data into a MongoDb Database for later research on it.

The choice of MongoDB allows to create a scalable architecture with sharding and mirroring to avoid having
too large single tenant DB as we have with postgresql.

### Recommanded HW
- 160-256GB RAM server
- 3x or 4x SSD/NVMe units for storage

Works with 64G systems (but have some swap even if memory not fully allocated, 
use docker-compose and Dockerfile with -64G extension)

It's important to enable swap account in kernel otherwise docker limits will not be applied
for swap and the system will start swapping as soon as the memory limit is reached. We don't want
java process to swap as the performance with GC become catastrophic.

```bash
$ vi /etc/default/grub
...
GRUB_CMDLINE_LINUX_DEFAULT="swapaccount=1"
...
$ update-grub
```

It's better to reduce the swappiness parameter to limit swap usage when memory still available 
```bash
$ sysctl vm.swappiness=10
```
make it permanent by adding the following line to /etc/sysctl.conf
```bash
vm.swappiness=10
```

### Install
Tested on Ubuntu 22.04LTS
- [Install Docker](https://docs.docker.com/engine/install/ubuntu/)
- Install make `sudo apt-get install make`
- Clone this repo locally

- **setup AWS S3 credentials** in `docker-compose.yml`
```yaml
  environment:
    - AWS_ACCESS_KEY=
    - AWS_SECRET_KEY=
```
AWS credential are needed to retrieve data from helium repository, these data are
accessible with *requester_pays* mode. It means you pay for any S3 action made on the
repository. Your credentials are created with IAM AWS tool and need to have `AmazonS3ReadOnlyAccess` role.

-- **setup NGINX** in `etl/nginx/default.conf` and `etl/nginx/default.conf.withssl`
Change the name of the service `etl.foo.bar` with your dns name

- **create the environment**
```agsl
make install
```
This is creating the MongoDB cluster with 3 shards, 3 config service and 2 routers.
All the configuration files are located in /etl. You can pre-make the /etl directory to
point where you want. The purpose is to move the different shards on different ssd to improve
the performance. 

You can eventually move the data on different SSD after this setup by remapping the
directories on different drives.

- **setup nginx certificates**

Run the following command / replace `etl.foo.bar` by your DNS entry
```
docker compose --profile webserver up -d
docker compose run --rm certbot certonly --webroot --webroot-path /var/www/certbot/ -d etl.foo.bar
docker compose --profile webserver down
mv /etl/configuration/nginx/default.conf /etl/configuration/nginx/default.conf.withoutssl
mv /etl/configuration/nginx/default.conf.withssl /etl/configuration/nginx/default.conf
```

if you want to use self signed certificate
```agsl
openssl req -x509 -nodes -subj "/CN=etl.foo.bar" \
        -addext "subjectAltName=DNS:etl.foo.bar" -days 9999 -newkey rsa:2048 \
        -keyout /etl/configuration/ssl/live/etl.foo.bar/privkey.pem \
        -out /etl/configuration/ssl/live/etl.foo.bar/fullchain.pem
```

Later to renew the certificate
```
docker compose run --rm certbot renew
```


- **start etl**
```
make start
```
This is starting every thing, including Mongo, the monitoring suite, the etl software.
If later you need to stop the etl, it's important to let it time to terminate data loading properly.
For this, use the following command `make stop-etl`. If you need to stop everything, you
can use `make stop`.

## tips

When using **ufw** to protect ports, you need to allow docker communication with port 9100 used by
node-exporter in host mode. For this add a rule:

```
ufw allow from 172.0.0.0/8 to any port 9100
```

## Developers
This is only for developers who want to work on the etl solution

### Run locally

Setup a database cluster with docker compose
```bash
make setup-dev
make start-dev
```

Configure your application-dev.properties file in `etl/src/main/resources/` from a copy of `application-dev.properties-sample` file.
You need to add the AWS credentials.

Add `-Dspring.active.profiles=dev -Xmx8G -Xms8G` as a **VM option** in your IDE to run the application in dev mode. You need to use `More Options` 
and select `VM options` with intelliJ.


### Re-Generate protobuf

- Helium service GRPC proto can be found on - https://github.com/helium/proto/blob/master/src/

- Protoc - https://github.com/protocolbuffers/protobuf
- Command lines
```agsl
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ data_rate.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ service/poc_lora.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ service_provider.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ hex_boosting.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ mapper.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ service/packet_verifier.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ service/poc_mobile.proto
```

### Mongo clustering
- largely inspired from https://github.com/minhhungit/mongodb-cluster-docker-compose