package com.example;

import com.example.models.User;
import com.example.repositories.CRUDRepository;
import com.example.repositories.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

    @Autowired
    UserRepository userRepository;



	@Test
	public void contextLoads() {

	}



	/*@Test
	public void databaseTest(){
        User user = setUpUser();
        Assert.assertNotNull(user.getId());
    }*/



}
