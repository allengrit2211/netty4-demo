package com.allen.thrift;

import com.allen.thrift.generated.DataException;
import com.allen.thrift.generated.PersionService;
import com.allen.thrift.generated.Person;
import org.apache.thrift.TException;

/**
 * @ClassName: PersonServiceImpl
 * @description:
 * @author: Allen
 * @create: 2019-06-16 15:55
 **/
public class PersonServiceImpl implements PersionService.Iface {
    @Override
    public Person getPersionByUserName(String username) throws DataException, TException {
        System.out.println("Got Client Param:" + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePersion(Person person) throws DataException, TException {
        System.out.println("Got Client Param: ");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
