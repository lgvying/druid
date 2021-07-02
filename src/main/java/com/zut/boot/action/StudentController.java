package com.zut.boot.action;

import com.zut.boot.dao.StudentDao;
import com.zut.boot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 梁贵莹
 * @create 2021/7/2 上午 10:46
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentDao studentDao;
    @RequestMapping("/addOne")
    @ResponseBody
    public String  addOne(){
        int hang=studentDao.insertOne(new
                Student(null,"111","妖",111f));
        return "添加"+hang+"行成功！";
    }
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        return studentDao.getAll();
    }
    @RequestMapping("/getOne")
    @ResponseBody
    public Student getOne(int sid){
        return studentDao.getOne(sid);
    }
}
