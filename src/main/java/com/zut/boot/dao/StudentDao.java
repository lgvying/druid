package com.zut.boot.dao;

import com.zut.boot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 梁贵莹
 * @create 2021/7/1 下午 9:19
 */
@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有
     * @return
     */
    public List<Student> getAll(){
        return jdbcTemplate.query("select * from student",getRowMapper());
    }

    /**
     * 获取一个
     * @param sid
     * @return
     */
    public Student getOne(int sid){
        List<Student> list=jdbcTemplate.query("select * from student where sid=?",getRowMapper(),sid);
        return list==null?null:list.get(0);
    }

    /**
     * 更新
     * @param student
     * @return
     */
    public int updateOne(Student student){
        return  jdbcTemplate.update("update student set sname=?,sex=?,score=? where sid=?", student.getSname(), student.getSex(), student.getScore(), student.getSid());
    }

    /**
     * 删除一个
     * @param sid
     * @return
     */
    public int deleteOne(int sid){
        return jdbcTemplate.update("delete from student where sid=?",sid);
    }

    /**
     * 添加一个
     * @param student
     * @return
     */
    public int insertOne(Student student){
        return jdbcTemplate.update("insert into student(sname,sex,score) values(?,?,?)",student.getSname(), student.getSex(), student.getScore());
    }
    private RowMapper<Student> getRowMapper() {
        //lombda表达式形式
//        return (resultSet, i) -> {
//            Student student = new Student();
//            student.setSid(resultSet.getInt("sid"));
//            student.setSname(resultSet.getString("sname"));
//            student.setSex(resultSet.getString("sex"));
//            student.setScore(resultSet.getFloat("score"));
//            return student;
//        };
        return new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setSid(resultSet.getInt("sid"));
                student.setSname(resultSet.getString("sname"));
                student.setSex(resultSet.getString("sex"));
                student.setScore(resultSet.getFloat("score"));
                return student;
            }
        };

    }
}
