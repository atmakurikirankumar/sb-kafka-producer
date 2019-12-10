package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

	@Autowired
	private KafkaTemplate<String, Person> kafkaPersonTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaStringTemplate;

	private static final String TOPIC = "AKK_SAMPLE_TOPIC";
	
	// send simple string
	@PostMapping(value = "/kafka/postString")
	public String postString(@RequestParam("message") String message) {
		kafkaStringTemplate.send(TOPIC, message);
		return "==== Message published successfully ====";
	}

	// send any object
	@PostMapping(value = "/kafka/postObject")
	public String postPerson(@RequestBody Person person) {
		kafkaPersonTemplate.send(TOPIC, person);
		return "==== Message published successfully ====";
	}
}
