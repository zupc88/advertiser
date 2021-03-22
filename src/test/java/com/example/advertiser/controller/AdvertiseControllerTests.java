package com.example.advertiser.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.advertiser.modal.Advertiser;
import com.example.advertiser.services.AdvertiseMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdvertiseController.class, secure = false)
public class AdvertiseControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdvertiseMapper advertiseService;
	
	Advertiser mockAdvertiser = new Advertiser(1, "MyCompany", "firstName",
			1200);
	ArrayList<Advertiser> mockAdvertiserList = new ArrayList<Advertiser>();
	
	
	String exampleAdvertiserJson = "{\"name\":\"MyCompany\",\"contactName\":\"firstName\",\"creditLimit\":\"1200\"}";
	
	@Test
	public void retrieveAdvertiserById() throws Exception {

		Mockito.when(
				advertiseService.findById(Mockito.anyInt()))
				.thenReturn(mockAdvertiser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/advertise/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedResponse = "{\"id\":1,\"name\":\"MyCompany\",\"contactName\":\"firstName\",\"creditLimit\":1200.0}";
		JSONAssert.assertEquals(expectedResponse, result.getResponse()
				.getContentAsString(), false);
		
	}

	@Test
	public void retrieveAllAdvertiser() throws Exception {
		mockAdvertiserList.add(mockAdvertiser);
		
		Mockito.when(
				advertiseService.findAll())
				.thenReturn(mockAdvertiserList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/advertise").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void postAdvertiser() throws Exception {
		
		Advertiser adv1 = new Advertiser(23, "TestAdvertiser", "developer", 10000);
		
		Mockito.when(
				advertiseService.insertAdvertiser(Mockito.any(Advertiser.class))).thenReturn(23);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/advertise")
				.accept(MediaType.APPLICATION_JSON).content(exampleAdvertiserJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/api/advertise/23",
				response.getHeader(HttpHeaders.LOCATION));
	}

	
	@Test
	public void updateAdvertiser() throws Exception {
		Mockito.when(
				advertiseService.updateAdvertiser(Mockito.any(Advertiser.class))).thenReturn(1);

				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.put("/api/advertise/1")
						.accept(MediaType.APPLICATION_JSON).content(exampleAdvertiserJson)
						.contentType(MediaType.APPLICATION_JSON);

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				MockHttpServletResponse response = result.getResponse();
				assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}

	@Test
	public void deleteAdvertiser() throws Exception {
		Mockito.when(
				advertiseService.updateAdvertiser(Mockito.any(Advertiser.class))).thenReturn(1);

		// Send course as body to /students/Student1/courses
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.delete("/api/advertise/1")
						.accept(MediaType.APPLICATION_JSON).content(exampleAdvertiserJson)
						.contentType(MediaType.APPLICATION_JSON);

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				MockHttpServletResponse response = result.getResponse();
				assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
