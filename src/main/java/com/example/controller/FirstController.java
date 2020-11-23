package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.custom.exceptions.NotesAlreadyExistsException;
import com.example.service.ServiceLayer;
import com.example.viewModels.NotesVm;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(description="controller that handles basic operations")	
public class FirstController {
	
	@Autowired
	private ServiceLayer service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(value="/api/saveNotes")
	public ResponseEntity<Object> saveNotes(@RequestBody() NotesVm notes) {
		System.out.println("it came here");
		boolean saved = service.saveNotes(notes);
		if(saved) {
			return new ResponseEntity<Object>("saved successfully", new HttpHeaders(), HttpStatus.OK);
		} else {
			throw new NotesAlreadyExistsException("Notes already exists");
		}
	}
	
	@GetMapping(value="/api/getNotes")
	public Set<NotesVm> getNotes(@RequestParam(value="title", required=false) final String title, @RequestParam(value="tags", required=false) final String tags) {
		if(title !=null) {
			return this.service.filterByTitle(title);
		} else if(tags != null) {
			return this.service.filterByTags(tags);
		} else {
			return this.service.getAllNotes();
		}
	}
	
	@GetMapping(value="/api/consume/getNotes")
	public ResponseEntity<Set> consumeGetMethod() {
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON);
		headers.setAccept(list);
		HttpEntity<String> entity = new HttpEntity(headers);
		return this.restTemplate.exchange("http://localhost:9000/api/getNotes", HttpMethod.GET, entity, Set.class);
	}

}
