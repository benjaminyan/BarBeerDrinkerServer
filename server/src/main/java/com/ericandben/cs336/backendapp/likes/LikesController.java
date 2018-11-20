package com.ericandben.cs336.backendapp.likes;

import com.ericandben.cs336.backendapp.item.*;
import com.ericandben.cs336.backendapp.softdrink.*;
import com.ericandben.cs336.backendapp.food.*;
import com.ericandben.cs336.backendapp.beer.*;
import com.ericandben.cs336.backendapp.transaction.*;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.BaseController;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/likes") // This means URL's start with /demo (after Application path)
public class LikesController extends BaseController<Likes, LikesKey> {

	private static final Logger logger = LoggerFactory.getLogger(LikesController.class);	

	 // This means to get the bean called userRepository
	 // Which is auto-generated by Spring, we will use it to handle the data
	@Autowired
	private LikesRepository likesRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired BarRepository barRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/summary")
	public @ResponseBody Page<Item> summary(@RequestParam(value = "page", defaultValue = "0") int page,
	@RequestParam(value = "limit", defaultValue = "15") int limit) {
		return itemRepository.findAll(PageRequest.of(page, limit));
	}

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewLikes () {


		Item testItem = new Beer(); // if this is a SoftDrink it doesn't work
		testItem.setName("Bud");

        Drinker testDrinker = new Drinker();
        testDrinker.setName("Aaron Endo");
		
		Likes testLikes = new Likes();
        testLikes.setDrinker(testDrinker);
		testLikes.setItem(testItem);
		likesRepository.save(testLikes);
		return "Saved";
	}

	@GetMapping(path="/item")
	public @ResponseBody Item getItem(@RequestParam String itemName) {
		return itemRepository.findByName(itemName);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Likes> getAllTuples() {
		// This returns a JSON or XML with the users
		return likesRepository.findAll();
	}

	
	@GetMapping(path="/fetchalltest")
    public @ResponseBody Page<Likes> fetchAll(Pageable pageable) {
		return super.fetchAll(pageable);
	}


	@Override
	public PagingAndSortingRepository<Likes, LikesKey> getRepository() {
		return this.likesRepository;
	}

}