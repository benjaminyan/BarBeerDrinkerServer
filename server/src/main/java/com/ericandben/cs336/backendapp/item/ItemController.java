package com.ericandben.cs336.backendapp.item;

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
@RequestMapping(path="/items") 
public class ItemController {
	@Autowired 
	private ItemRepository itemRepository;

	/*
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewItem (@RequestParam String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Item n = new Item();
		n.setName(name);
		itemRepository.save(n);
		return "Saved";
	}
	*/

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/summary")
	public @ResponseBody Page<Item> summary(@RequestParam(value = "page", defaultValue = "0") int page,
	@RequestParam(value = "limit", defaultValue = "15") int limit) {
		return itemRepository.findAll(PageRequest.of(page, limit));
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Item> getAllItems() {
		// This returns a JSON or XML with the users
		return itemRepository.findAll();
	}

	@GetMapping(path="/find")
	public @ResponseBody Item getByName(@RequestParam String name) {

		return itemRepository.findByName(name);
	}
}