package com.rxf113.sparkdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/spark")
public class JavaController {

    @Resource
    private ScalaController scalaController;

    /**
     *
     * @return
     */
    @GetMapping(value = "/java")
    @ResponseBody
    public Object transferSpark(){
        Object one = scalaController.findOne(5);
        System.out.println(one);
        return "success";
    }
}
