package com.ericandben.cs336.backendapp.hour;

import com.ericandben.cs336.backendapp.item.*;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.drinker.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.Duration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;


@Controller
@RequestMapping(path="/hours")
public class HourController {
	@Autowired
	private HourRepository hourRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/summary")
	public @ResponseBody Page<Hour> summary(@RequestParam(value = "page", defaultValue = "0") int page,
	@RequestParam(value = "limit", defaultValue = "15") int limit) {
		return hourRepository.findAll(PageRequest.of(page, limit));
	}
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewHour (@RequestParam String barName, 
				@RequestParam int weekday, @RequestParam String openingString, @RequestParam String closingString) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Hour n = new Hour();
		Bar testBar = new Bar(barName);
        HourKey testHK = new HourKey();
        testHK.setBar(testBar);
        testHK.setWeekday(weekday);
        n.setPkey(testHK);
        String[] openingStringArr = openingString.split(":");
        int openingHour = Integer.parseInt(openingStringArr[0]);
        openingHour -= 5;
        String opening = "" + openingHour + ":" + openingStringArr[1] + ":" + openingStringArr[2];
        String[] closingStringArr = closingString.split(":");
        int closingHour = Integer.parseInt(closingStringArr[0]);
        closingHour -= 5;
        String closing = "" + closingHour + ":" + closingStringArr[1] + ":" + closingStringArr[2];
        n.setOpening(java.sql.Time.valueOf(opening));
        n.setClosing(java.sql.Time.valueOf(closing));
		hourRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Hour> getAllHours() {
		// This returns a JSON or XML with the users
		return hourRepository.findAll();
	}
}