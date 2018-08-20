package com.jas.web.test;

import com.jas.web.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.*;

public class test {
    public static void main(String[] args) {
        /*ApplicationContext xmlBeanFactory = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        Object courseServiceImpl = xmlBeanFactory.getBean("courseServiceImpl");
        CourseServiceImpl courseServiceImpl1 = (CourseServiceImpl) courseServiceImpl;
        courseServiceImpl1.test1();*/
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        ListIterator<String> listIterator = list.listIterator();
        String next = listIterator.next();
        listIterator.add("c");
        listIterator.remove();

        System.out.println(list);
    }
}
