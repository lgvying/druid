package com.zut.boot.action;

import com.zut.boot.dao.StudentDao;
import com.zut.boot.entity.Student;
import com.zut.boot.service.StudentService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author 梁贵莹
 * @create 2021/7/2 下午 3:17
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生管理相关功能")
public class StudentAction {
    @Autowired
    private StudentService studentService;

    /**
     * 添加
     * @param student
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加学生")//方法注解，方法功能描述
    @ApiImplicitParam(name = "student",value = "添加的学生",dataTypeClass = Student.class)
    public String addOne(@RequestBody Student student){
        return "添加"+studentService.addOne(student)+"行成功！";
    }

    /**
     * 删除
     * @param sid
     * @return
     */
    @DeleteMapping("/delete/{sid}")
    @ApiOperation("删除学生")//方法注解，方法功能描述
    public String deleteOne(@PathVariable("sid")Integer sid){
        return "删除"+studentService.deleteOne(sid)+"行成功！";
    }

    /**
     * 修改
     * @param student
     * @return
     */
    @PutMapping("update")
    @ApiOperation("更新学生")//方法注解，方法功能描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex",value = "学生性别",required = false),
            @ApiImplicitParam(name = "sname",value = "学生姓名",required = false),
            @ApiImplicitParam(name = "score",value = "学生分数",required = false),
            @ApiImplicitParam(name = "sid",value = "学生学号",required = false)

    })
    public String updateOne(Student student){
        return "修改"+studentService.updateOne(student)+"行成功！";
    }

    /**
     * 获取一个 使用URL模板映射
     * @param sid
     * @return
     */
    @GetMapping("/getOne1/{sid}")
    @ApiOperation("获取一个学生1")
    public Student getOne1(@PathVariable("sid") Integer sid){
        return studentService.getOne(sid);
    }

    /**
     * 获取一个 使用api标签
     * @param sid
     * @return
     */
    @GetMapping("/getOne2")
    @ApiOperation("获取一个学生2")
    @ApiImplicitParam(name = "sid",value = "学生编号")
    public Student getOne2(Integer sid){
        return studentService.getOne(sid);
    }
    /**
     * 获取全部
     * @return
     */
    @ApiOperation("获取所有学生")
    @GetMapping("/getAll")
    public Collection<Student> getAll(){
        return studentService.getAll();
    }

    /**
     * 根据条件获取所有学生
     * @param sex
     * @param score
     * @return
     */
    @ApiOperation("根据条件，获取所有学生")
    @GetMapping("/getAllBySexScore")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex",value = "学生性别",required = false),
            @ApiImplicitParam(name = "score",value = "学生分数",required = false)
    })
    public Collection<Student> getAll(String sex,Float score){
        return studentService.getAll(sex,score);
    }


}
