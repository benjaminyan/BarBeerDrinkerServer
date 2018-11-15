package com.ericandben.cs336.backendapp.includes;

import com.ericandben.cs336.backendapp.item.*;
import com.ericandben.cs336.backendapp.softdrink.*;
import com.ericandben.cs336.backendapp.food.*;
import com.ericandben.cs336.backendapp.transaction.*;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.drinker.*;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/includes") // This means URL's start with /demo (after Application path)
public class IncludesController {

	private static final Logger logger = LoggerFactory.getLogger(IncludesController.class);	

	 // This means to get the bean called userRepository
	 // Which is auto-generated by Spring, we will use it to handle the data
	@Autowired
	private IncludesRepository includesRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired BarRepository barRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewIncludes (@RequestParam String barName, @RequestParam int tid, 
				@RequestParam String itemName, @RequestParam int quantity) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request


		Item testItem = new Food(); // if this is a SoftDrink it doesn't work
		testItem.setName(itemName);
		//Item testItem = itemRepository.findByName(itemName);
		Bar testBar = new Bar(barName);

		TransactionKey testTK = new TransactionKey();
		testTK.setBar(testBar);
		testTK.setTid(tid);

		IncludesKey testIK = new IncludesKey();
		testIK.setTransactionKey(testTK);
		testIK.setItem(testItem);
		Includes testIncludes = new Includes();
		testIncludes.setPkey(testIK);
		testIncludes.setQuantity(quantity);
		includesRepository.save(testIncludes);
		return "Saved";
	}

	@GetMapping(path="/item")
	public @ResponseBody Item getItem(@RequestParam String itemName) {
		return itemRepository.findByName(itemName);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Includes> getAllTuples() {
		// This returns a JSON or XML with the users
		return includesRepository.findAll();
	}

}