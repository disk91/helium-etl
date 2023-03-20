### Helium Off-chain Poc extractor

This project aim to extract the data coming from off-PoC helium foundation and build information about
hotposts health. It also moves data into a MongoDb Database for later research on it.

The choice of MongoDB allows to create a scalable architecture with sharding and mirroring to avoid having
too large single tenant DB as we have with postgresql.

### Install
- **setup AWS S3 credentials** in `docker-compose.yml`
```yaml
  environment:
    - AWS_ACCESS_KEY=
    - AWS_SECRET_KEY=
```
AWS credential are needed to retrieve data from helium repository, these data are
accessible with *requester_pays* mode. It means you pay for any S3 action made on the
repository. Your credentials are created with IAM AWS tool and need to have `AmazonS3ReadOnlyAccess` role.

- **create the environment**
```agsl
make install
```
This is creating the Mongo cluster with 3 shards, 3 config service and 2 routers.
All the configuration files are located in /etl. You can pre-make the /etl directory to
point where you want. The purpose is to move the different shards on different ssd to improve
the performance. 

You can eventually move the data on different SSD after this setup by remapping the
directories on different drives.

- **start etl**
```
make start
```
This is starting every thing, including Mongo, the monitoring suite, the etl software.
If later you need to stop the etl, it's important to let it time to terminate data loading properly.
For this, use the following command `make stop-etl`. If you need to stop everything, you
can use `make stop`.


## Developpers
This is only for developper who want to work on the etl solution

### Re-Generate protobug 

- Nova service GRPC proto can be found on - https://github.com/helium/proto/blob/master/src/service/iot_config.proto
- Update data_rate.proto file
```agsl
syntax = "proto3";

package helium;
option java_package = "xyz.nova.grpc";
option java_outer_classname = "PocLoRa";
option java_multiple_files = true;

enum data_rate {
```

- Update service/poc_lora.proto file
```agsl
package helium.poc_lora;
option java_package = "xyz.nova.grpc";
option java_outer_classname = "PocLoRa";
option java_multiple_files = true;

import "data_rate.proto";
```

- Protoc - https://github.com/protocolbuffers/protobuf
- Command lines
```agsl
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ data_rate.proto
./protoc/bin/protoc --java_out=./myHeliumProto/proto/out --proto_path=./myHeliumProto/proto/src/ service/poc_lora.proto
```

### Mongo clustering
- largely inspired from https://github.com/minhhungit/mongodb-cluster-docker-compose