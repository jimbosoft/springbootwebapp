package com.example.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.spi.ContextAware;

import com.example.helper.PropertiesProcessor;
import com.tabcorp.jgroupsCluster.status.ClusterDetails;
import com.tabcorp.jgroupsCluster.status.ClusterStatusService;

@Controller
public class RestController implements ApplicationContextAware 
{
	private static final Logger log = Logger.getLogger(RestController.class);

	@Autowired
	PropertiesProcessor properties;

	private ClusterStatusService clusterService;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		try
		{
			clusterService = context.getBean(ClusterStatusService.class);
		} catch (Exception ex)
		{
			log.warn("Cluster Service not inilised " + ex.getMessage());
		}
	}

	@RequestMapping(value = "/versionDetails" , method = RequestMethod.GET)
	public @ResponseBody VersionDetail getVersion()
	{
		
		String version = "Unavailable";
		String name = "Unavailable";
		try
		{
			version = properties.getPropertyAsString("info.version");
			name = properties.getPropertyAsString("info.build.name");
			
		} catch (Exception ex)
		{
		}
		return new VersionDetail(version, name);
	}	

	class VersionDetail implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public VersionDetail(String versionNumber, String name){
			this.versionNumber = versionNumber;
			this.appName = name;
		};
		public String versionNumber;
		public String appName;
	}
	
	@RequestMapping(value="/clusterDetails", method=RequestMethod.GET)
	public @ResponseBody ClusterDetails getStatusClusterDetails()
	{
		if (clusterService == null)
		{
			ClusterDetails cd = new ClusterDetails();
			return cd;
		}
		return clusterService.getStatus();	
	}
	
	@RequestMapping(value="/clusterNodes", method=RequestMethod.GET)
	public @ResponseBody List<String> getClusterNodes()
	{
		if (clusterService == null)
		{
			return new ArrayList<String>();
		}
		List<String> nodes = clusterService.getNodes();
		return nodes;	
	}

	

}