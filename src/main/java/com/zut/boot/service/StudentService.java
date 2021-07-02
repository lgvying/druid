package com.zut.boot.service;

import com.zut.boot.entity.Student;
import org.springframework.stereotype.Service;

import java.awt.geom.FlatteningPathIterator;
import java.util.*;

/**
 * @author 梁贵莹
 * @create 2021/7/2 下午 3:21
 */
@Service
public class StudentService {
    private static Map<Integer, Student> map=new HashMap<>();
    static{
        map.put(11,new Student(11,"韩梅1","女",12f));
        map.put(12,new Student(12,"韩梅2","男",13f));
        map.put(13,new Student(13,"韩梅3","男",14f));
        map.put(14,new Student(14,"韩梅4","男",15f));
        map.put(15,new Student(15,"韩梅5","女",16f));
    }
    private static Integer maxId=100;

    /**
     *
     * @param s
     * @return
     */
    public int addOne(Student s){
        s.setSid(maxId);
        map.put(maxId,s);
        maxId++;
        return 1;
    }

    /**
     *
     * @param sid
     * @return
     */
    public int deleteOne(int sid){
        if(!map.containsKey(sid)){
            return 0;
        }
        map.remove(sid);
        return 1;
    }

    /**
     *
     * @param s
     * @return
     */
    public int updateOne(Student s){
        if(!map.containsKey(s.getSid())){
            return 0;
        }
        map.put(s.getSid(),s);
        return 1;
    }

    /**
     *
     * @param sid
     * @return
     */
    public Student getOne(int sid){
        return map.get(sid);
    }

    /**
     *
     * @return
     */
    public Collection<Student> getAll(){
        return map.values();
    }

    /**
     *
     * @param sex
     * @param score
     * @return
     */
    public Collection<Student> getAll(String sex, Float score){
        Collection<Student> students = map.values();
        Iterator iterator=students.iterator();
        students.clear();
        while (iterator.hasNext()){
            Student student=(Student) iterator.next();
            if(student.getSex()==sex && student.getScore()==score){
                students.add(student);
            }
        }
        return students;
    }
}
