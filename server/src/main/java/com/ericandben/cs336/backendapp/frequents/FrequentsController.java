package com.ericandben.cs336.backendapp.frequents;

import com.ericandben.cs336.backendapp.item.*;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.drinker.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Controller
@RequestMapping(path="/frequents")
public class FrequentsController {
	@Autowired
	private FrequentsRepository frequentsRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/summary")
	public @ResponseBody Page<Frequents> summary(@RequestParam(value = "page", defaultValue = "0") int page,
	@RequestParam(value = "limit", defaultValue = "15") int limit) {
		return frequentsRepository.findAll(PageRequest.of(page, limit));
	}
	

	@GetMapping(path="/add")
	public @ResponseBody String addNewSells (@RequestParam String barName, 
				@RequestParam String itemName, @RequestParam double price) {
		
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Frequents> getAll() {
		// This returns a JSON or XML with the users
		return frequentsRepository.findAll();
	}
}