package com.jas.web.test;

import com.jas.web.bean.domain.ChoiceCoursesDO;
import com.jas.web.service.ICourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    @Resource
    ICourseService courseService;

    @Test
    public void test1(){
        List<ChoiceCoursesDO> coursesDOS = courseService.getChoiceCourseByStudentId(6);
        System.out.println(coursesDOS);
    }
}
