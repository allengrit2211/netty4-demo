protoc --java_out=src/main/java src/protobuf/Student.proto

thrift 0.11.0
thrift --gen java src/thrift/data.thrift
