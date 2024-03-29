package com.allen.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NioTest4
 * @description:
 * @author: Allen
 * @create: 2019-06-17 21:27
 **/
public class NioTest4 {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        while(true){
            buffer.clear();
            int read = inputChannel.read(buffer);

            System.out.println("read:" + read);
            if(-1==read){
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);

        }

        inputStream.close();
        outputStream.close();

    }

}











