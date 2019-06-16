package com.allen.thrift;

import com.allen.thrift.generated.PersionService;
import com.allen.thrift.generated.Person;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @ClassName: ThriftClient
 * @description:
 * @author: Allen
 * @create: 2019-06-16 15:13
 **/
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersionService.Client client = new PersionService.Client(protocol);
        try {

            transport.open();
            Person person = client.getPersionByUserName("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            Person person2 = client.getPersionByUserName("李四");
            person2.setAge(20);
            person2.setMarried(false);
            client.savePersion(person2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            transport.close();
        }
    }

}
