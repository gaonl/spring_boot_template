package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;

/**
 * Created by gaonl on 2018/10/3.
 */
@Controller
@RequestMapping({"/", "/index"})
public class IndexController {
    @Autowired
    private DataSource dataSource;

    /**
     *
     * @return 返回页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
