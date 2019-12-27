package com.ssm.promotion.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ssm.promotion.core.entity.Student;

@Controller
@RequestMapping("basketball") //请求的路径
public class BasketballController {
 
    /**
    * 直接返回字符串
    * @param teamname
    * @param request
    * @return
    */
    //请求的路径，方式
    @RequestMapping(value = "v1.0/new/{teamname}", method = RequestMethod.GET)
    @ResponseBody public String foo4(@PathVariable String teamname, HttpServletRequest request) {
 
        //可以使用teamname获取url路径分隔
 
        //获取请求的参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
 
        Student student = new Student(name, age);
 
        return JSON.toJSONString(student);
    }
 
    /**
    * 直接返回对象，自动转化为JSON格式
    * @param teamname
    * @param request
    * @return
    */
    @RequestMapping(value = "v2.0/new/{teamname}", method = RequestMethod.GET)
    @ResponseBody public Student foo5(@PathVariable String teamname, HttpServletRequest request) {
 
        //可以使用teamname获取url路径分隔
 
        //获取请求的参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
 
        Student student = new Student(name, age);
 
        return student;
    }
    /**
    * 直接返回List，自动转化为JSON格式
    * @param teamname
    * @param request
    * @return
    */
    @RequestMapping(value = "v3.0/new/{teamname}", method = RequestMethod.GET)
    @ResponseBody public List<Student> foo6(@PathVariable String teamname, HttpServletRequest request) {
 
        //可以使用teamname获取url路径分隔
 
        //获取请求的参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
 
        Student student = new Student(name, age);
        Student student1 = new Student(name + name, age + age);
 
        List<Student> list = new ArrayList<Student>();
        list.add(student);
        list.add(student1);
 
        return list;
    }
}
