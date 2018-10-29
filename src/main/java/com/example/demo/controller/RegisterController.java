package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gaonl on 2018/10/4.
 */
@Controller
@RequestMapping({"/register"})
public class RegisterController {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegisterForm() {
        return "register/register";
    }

    @RequestMapping(path = "/success",method = RequestMethod.GET)
    public String registerSuccess() {
        return "register/success";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(User user) {
        user.setRegisterDateTime(System.currentTimeMillis());
        userDao.save(user);
        return "redirect:/register/success";
    }
}
