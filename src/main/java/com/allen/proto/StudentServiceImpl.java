package com.allen.proto;

/**
 * @ClassName: StudentServiceImpl
 * @description:
 * @author: Allen
 * @create: 2019-06-16 18:19
 **/
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, io.grpc.stub.StreamObserver<MyResponse> responseObserver) {

        System.out.println("接收到客户端信息" + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();


    }
}
