package com.example;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.tabcorp.jgroupsCluster.router.ClusterController;
import com.tabcorp.jgroupsCluster.router.NodeChangeNotifier;

@SpringBootApplication
public class WebyApplication
{
	@Bean
	ClusterController makeClusterController()
	{
		ClusterController cc = new ClusterController(new NodeChangeNotifier());
		return cc;
	}
	
	public static void main(String[] args) 
	{
		ApplicationContext ctx = SpringApplication.run(WebyApplication.class, args);

		//Tester t = ctx.getBean(Tester.class);
		//t.runTest();
	}
}
@Configuration
@ImportResource({ "classpath:clusterCommsConfig.xml" })
class ClusterConfig
{
}