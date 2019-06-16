package com.allen.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @ClassName: ProtoBufTest
 * @description:
 * @author: Allen
 * @create: 2019-06-16 12:18
 **/
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("Allen")
                .setAge(30)
                .setAddress("北京").build();


        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());



    }

}
