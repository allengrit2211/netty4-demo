package com.allen.grpc;

import com.allen.proto.*;
import io.grpc.stub.StreamObserver;

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

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息" + request.getAge());


        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(20).setCity("北京").build());

        responseObserver.onCompleted();

    }
}
