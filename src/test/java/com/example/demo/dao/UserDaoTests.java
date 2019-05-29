package com.example.demo.dao;

import com.example.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void getById() {
		User user = userDao.getById(1);
		Assert.assertNotNull(user);
		Assert.assertEquals("username not same!!",user.getName(),"gaonl");
	}

	@Test
	public void getByName() {
		List<User> users = userDao.getByName("gaonl");
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() >= 1);
		for(User user: users){
			Assert.assertEquals("name not equals!!","gaonl",user.getName());
		}
	}

	@Test
	public void save() {
		User user = new User();
		user.setName("yms");
		user.setPassword("yms123456");
		user.setRegisterDateTime(new Date().getTime());

		User userSaved = userDao.save(user);
		Assert.assertNotNull(userSaved.getId());


		User userReGet = userDao.getById(userSaved.getId());
		Assert.assertNotNull(userReGet);
		Assert.assertEquals("username not same!!",user.getName(),"yms");
	}

}
