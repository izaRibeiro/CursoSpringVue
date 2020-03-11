package com.iza.microserviceusermanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iza.microserviceusermanagement.enums.Role;
import com.iza.microserviceusermanagement.model.User;
import com.iza.microserviceusermanagement.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private Environment environment;
	
	@Value("${spring.application.name}")
	private String serviceId;
	
	@GetMapping("/service/port")
	public String getPort() {
		return "Service port number: " + environment.getProperty("local.server.port");
	}
	
	@GetMapping("/service/instances")
	public ResponseEntity<?> getInstances(){
		return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
	}
	
	@GetMapping("/service/services")
	public ResponseEntity<?> getServices(){
		return new ResponseEntity<>(discoveryClient.getServices(), HttpStatus.OK);
	}
	
	@PostMapping("/service/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		if(userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		user.setRole(Role.USER);
		
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
	
   @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null || principal.getName() == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //return ResponseEntity.ok(userService.findByUsername(principal.getName()));
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    } 
	
	@PostMapping("/service/names")
	public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList){
		return ResponseEntity.ok(userService.findUser(idList));
	}
	
	@GetMapping("/service/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("It is working ...");
	}
}
