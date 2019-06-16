package com.allen.proto;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @ClassName: GrpcClient
 * @description:
 * @author: Allen
 * @create: 2019-06-16 19:02
 **/
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);


        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(myResponse.getRealname());



    }

}
