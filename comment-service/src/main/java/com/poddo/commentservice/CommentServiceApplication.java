package com.poddo.commentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Comment Service Application
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CommentServiceApplication {

	/**
	 * Main method
	 * @param args Array of Strings
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}
