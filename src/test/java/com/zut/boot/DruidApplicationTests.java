package com.zut.boot;

import com.zut.boot.dao.StudentDao;
import com.zut.boot.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DruidApplicationTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void contextLoads() {
    }
    @Test
    void testStudentDao(){
        Student student=new Student(null,"lgy","男",32F);
        System.out.println(studentDao.insertOne(new Student(null,"111","妖",111f)));
        System.out.println(studentDao.getOne(38));
        System.out.println(studentDao.updateOne(student));
        System.out.println(studentDao.getAll());
        System.out.println(studentDao.deleteOne(38));
        System.out.println(studentDao.getAll());
    }

}
