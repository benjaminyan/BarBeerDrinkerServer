package com.ericandben.cs336.backendapp.bar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.TimeZone;

import com.ericandben.cs336.backendapp.transaction.Transaction;

import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/bars") // This means URL's start with /demo (after Application path)
public class BarController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private BarRepository barRepository;

	private static final Logger logger = LoggerFactory.getLogger(BarController.class);

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Bar n = new Bar(name);
		barRepository.save(n);
		return "Saved";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Bar> getAllBars() {
		// This returns a JSON or XML with the users
		return barRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/topdrinkersperbar")
	public @ResponseBody List topdrinkersperbar(@RequestParam String bar) {
		// This returns a JSON or XML with the users
		return barRepository.drinkerSpentTotal(bar);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/timedistsales")
	public @ResponseBody Map<String,Double> timeDistSales(@RequestParam String bar, @RequestParam String begin, @RequestParam String end) {
		// This returns a JSON or XML with the users
		return getTimeDistQueryResult(bar,begin,end);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/topFiveBeers")
	public @ResponseBody Page<List<Object[]>> getTopFiveBeers(@RequestParam String barName) {
		return barRepository.mostPopularBeersPerBar(PageRequest.of(0,5), barName);
	}



	public Map<String,Double> getTimeDistQueryResult(String bar, String beginDate, String endDate){
		Map<String,String[]> intervals = new HashMap<>();
		String[] morning = {"09:00:00","12:00:00"};
		String[] afternoon = {"12:00:00","18:00:00"};
		String[] evening = {"18:00:00","23:59:59"};
		String[] lateEvening = {"00:00:00","03:00:00"};
		intervals.put("morning 09:00:00 - 12:00:00",morning);
		intervals.put("afternoon 12:00:00 - 18:00:00",afternoon);
		intervals.put("evening 18:00:00 - 23:59:59",evening);
		intervals.put("lateEvening 00:00:00 - 03:00:00",lateEvening);
		Map<String,Double> results = new HashMap<>();
		for (Map.Entry<String, String[]> entry : intervals.entrySet())
		{
			try{
				DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				ft.setTimeZone(TimeZone.getTimeZone("UTC-5"));
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				formatter.setTimeZone(TimeZone.getTimeZone("UTC-5"));
				Time beginTime = new Time(formatter.parse(entry.getValue()[0]).getTime());
				Time endTime = new Time(formatter.parse(entry.getValue()[1]).getTime());
				Date beginDateObj = ft.parse(beginDate);
				Date endDateObj = ft.parse(endDate);
				Double br = barRepository.timeDistSalesPerBar(bar, beginDateObj, endDateObj,
				beginTime,
				endTime);
				logger.warn(br + "");
				logger.warn(beginTime.toString());
				logger.warn(endTime.toString());
				logger.warn(beginDateObj.toString());
				logger.warn(endDateObj.toString());
				results.put(entry.getKey(),br);
			}
			catch(ParseException e){
				System.out.println(e);
			}
		}
		return results;
	}

}