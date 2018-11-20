package com.ericandben.cs336.backendapp.sells;

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
@RequestMapping(path="/sells")
public class SellsController {
	@Autowired
	private SellsRepository sellsRepository;
	
	@Autowired
	private BarRepository barRepository;
	
	@Autowired
	private ItemRepository itemRepository;


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/summary")
	public @ResponseBody Page<Sells> summary(@RequestParam(value = "page", defaultValue = "0") int page,
	@RequestParam(value = "limit", defaultValue = "15") int limit) {
		return sellsRepository.findAll(PageRequest.of(page, limit));

	}

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewSells (@RequestParam String barName, 
				@RequestParam String itemName, @RequestParam double price) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Sells n = new Sells();
		Bar bar = barRepository.findByName(barName);
		Item item = itemRepository.findByName(itemName);
		//Item item = new Item();
		//item.setName("Nachos");
		
		//n.getPkey().setItem(item);
		// n.getPkey().setBar(bar);
		n.setItem(item);
		n.setBar(bar);
		n.setPrice(price);
		
		// get bar with name barName
		// get item with item itemName
		// set appropriate properties of Sells object
		// persist the object

		//bar.getItemsSold().add(n);
		//item.getBarsSellingThis().add(n);

		sellsRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Sells> getAllItems() {
		// This returns a JSON or XML with the users
		return sellsRepository.findAll();
	}
}