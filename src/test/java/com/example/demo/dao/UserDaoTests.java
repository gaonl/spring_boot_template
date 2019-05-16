package com.example.demo.dao;

import com.example.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void getById() {
		User user = userDao.getById(1);
		Assert.assertNotNull(user);
		Assert.assertEquals("username not same!!",user.getName(),"gao");
	}

}
