package com.ericandben.cs336.backendapp.drinker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/drinkers") // This means URL's start with /demo (after Application path)
public class DrinkerController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private DrinkerRepository drinkerRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Drinker d = new Drinker();
		d.setName(name);
		drinkerRepository.save(d);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Drinker> getAllDrinkers() {
		// This returns a JSON or XML with the users
		return drinkerRepository.findAll();
	}
}