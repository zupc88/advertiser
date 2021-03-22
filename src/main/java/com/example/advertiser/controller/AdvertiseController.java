package com.example.advertiser.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.advertiser.modal.Advertiser;
import com.example.advertiser.services.AdvertiseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class AdvertiseController {
	
	@Autowired
	AdvertiseMapper advertiseService;
	
	@Autowired
	ObjectMapper mapper;
	
	@ResponseBody
	@RequestMapping(value="/advertise/{id}",method=RequestMethod.GET, headers = "Accept=application/json", produces="application/json")
	public Advertiser getTemperatureForToday(@PathVariable("id")int advertiserID) {
		
		return advertiseService.findById(advertiserID);
	}
	
	@ResponseBody
	@RequestMapping(value="/advertise/{id}/hasCredit",method=RequestMethod.GET, headers = "Accept=application/json", produces="application/json")
	public boolean hasCredit(@PathVariable("id")int advertiserID) {
		
		Advertiser adv =  advertiseService.findById(advertiserID);
		if(adv.getCreditLimit() > 0){
			return true;
		} else {
			return false;
		}
	}

	
	@ResponseBody
	@RequestMapping(value="/advertise",method=RequestMethod.GET, produces="application/json")
	public List<Advertiser> getAdvertiser() {
		
		List<Advertiser> sample = advertiseService.findAll();
		return sample;
	}
	
	@ResponseBody
	@RequestMapping(value="/advertise",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Void> storeAdvertiser(@RequestBody Advertiser adv) {
		
		int id = advertiseService.insertAdvertiser(adv);
		if (id == 0)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@ResponseBody
	@RequestMapping(value="/advertise/{id}",method=RequestMethod.PUT, consumes="application/json")
	public Advertiser storeAdvertiser(@RequestBody Advertiser adv, @PathVariable int id,
			HttpServletResponse response) {
		try {
			adv.setId(id);
			advertiseService.updateAdvertiser(adv);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception exception) {
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
			System.err.println(exception.getMessage());
			return null;
		}
		return adv;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/advertise/{id}",method=RequestMethod.DELETE)
	public int storeAdvertiser(@PathVariable int id, HttpServletResponse response) {
		try{
			advertiseService.deleteAdvertiser(id);
			response.setStatus( HttpServletResponse.SC_OK  );
		}
		catch(Exception exception){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
			System.err.println(exception.getMessage());
		}
		
		return 200;
		
	}
}
