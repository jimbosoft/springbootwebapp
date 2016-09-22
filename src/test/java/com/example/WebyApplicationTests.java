package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = WebyApplication.class)
//@WebAppConfiguration
@SpringBootApplication
public class WebyApplicationTests 
{
	public static void main(String[] args)
	{
		SpringApplication.run(WebyApplicationTests.class, args);
	}

//	@Test
//	public void contextLoads() 
//	{
//		assertTrue(true);
//	}

}
