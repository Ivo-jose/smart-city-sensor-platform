package br.com.ivogoncalves.smartcitysensorplatform.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivogoncalves.smartcitysensorplatform.models.Greeting;

@RestController
@RequestMapping("/greenting")
public class BrightnessSensorController {

	/*
	 * @Autowired private BrightnessSensorService service;
	 */

	private static final String template = "Hello, %s!";
	private static final AtomicLong counter = new AtomicLong();
	
	@GetMapping()
	public Greeting greenting(@RequestParam(value = "name", defaultValue = "World") String name ) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name)); 
		}
}	

