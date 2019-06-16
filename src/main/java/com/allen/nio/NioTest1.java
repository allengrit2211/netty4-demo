package com.allen.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @ClassName: NioTest1
 * @description:
 * @author: Allen
 * @create: 2019-06-16 21:28
 **/
public class NioTest1 {


    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
