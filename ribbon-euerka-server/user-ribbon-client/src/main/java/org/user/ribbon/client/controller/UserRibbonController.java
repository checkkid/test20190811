package org.user.ribbon.client.controller;

import java.io.IOException;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.user.api.domain.User;

@RestController
public class UserRibbonController {

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${provider.service.name")
	private String providerservicename;
	
	
	@GetMapping("")
	public String index() throws IOException{
		
		User user = new User();
		user.setId(2L);
		user.setName("xiaomage");
		
		ServiceInstance serviceInstance = loadBalancerClient.choose(providerservicename);
		
		return loadBalancerClient.execute(providerservicename, serviceInstance, (Instance ->{
			String host =Instance.getHost();
			int port = Instance.getPort();
			String url = "http://"+host+":"+port+"/user/save";
			RestTemplate restTemplate = new RestTemplate();
			
			return restTemplate.postForObject(url, user,String.class);
			
			
			
		}));
		
		
		
		
	}
	
}
