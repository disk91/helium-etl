### Helium Off-chain Poc extractor

This project aim to extract the data coming from off-PoC helium foundation and build information about
hotposts health. It also moves data into a MongoDb Database for later research on it.

The choice of MongoDB allows to create a scalable architecture with sharding and mirroring to avoid having
too large single tenant DB as we have with postgresql.

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