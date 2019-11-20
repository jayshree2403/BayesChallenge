package com.bayes.hero;

import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bayes.hero.dto.HeroDto;
import com.fasterxml.jackson.databind.JsonNode;

@SpringBootApplication
public class BayesChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BayesChallengeApplication.class, args);
	}
	
	

}
