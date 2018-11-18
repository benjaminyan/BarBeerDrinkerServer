package com.ericandben.cs336.backendapp.beer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/beers") // This means URL's start with /demo (after Application path)
public class BeerController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private BeerRepository beerRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewBeer (@RequestParam String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Beer n = new Beer();
		n.setName(name);
		beerRepository.save(n);
		return "Saved";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Beer> getAllBeers() {
		// This returns a JSON or XML with the users
		return beerRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/topFiveBars")
	public @ResponseBody Page<List<Object[]>> getTopFiveBars(@RequestParam String beerName) {
		return beerRepository.topFiveBars(PageRequest.of(0,5), beerName);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/topFiveDrinkers")
	public @ResponseBody Page<List<Object[]>> getTopFiveDrinkers(@RequestParam String beerName) {
		return beerRepository.topFiveDrinkers(PageRequest.of(0,5), beerName);
	}
}