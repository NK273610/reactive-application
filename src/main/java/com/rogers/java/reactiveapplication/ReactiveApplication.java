package com.rogers.java.reactiveapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class ReactiveApplication {

	public static void main(String[] args) {
		/*
		An example to show masking pattern in log
		 */
		Map<String, String> user = new HashMap<String, String>();
		user.put("user_id", "87656");
		user.put("SSN", "786445563");
		user.put("address", "22 Street");
		String json = null;
		try {
		  json = new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException e) {
		  throw new RuntimeException(e);
		}

		log.info("User JSON: {}", json);
		SpringApplication.run(ReactiveApplication.class, args);
	}

}
