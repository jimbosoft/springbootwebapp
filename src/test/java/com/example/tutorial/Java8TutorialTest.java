package com.example.tutorial;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.WebyApplication;
import com.example.WebyApplicationTests;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebyApplicationTests.class})
public class Java8TutorialTest
{

	//@Autowired
	Java8Tutorial tut = new Java8Tutorial();
	
	@Test
	public void test()
	{
		tut.runTest2();
		assertTrue(true);
	}

}
