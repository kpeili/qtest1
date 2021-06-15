package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// add annotation to greetingcontroller to convert it to a rest controller
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	private GreetingComponent g;
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g =g;
	
	}
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(g.getContent());
	}
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue ="World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}	
	
	@GetMapping("/add/{num1}, {num2}")
    public int addTwoNumber(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
		return num1-num2;
	}
}
