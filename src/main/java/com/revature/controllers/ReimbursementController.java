package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

public class ReimbursementController extends HttpServlet{

	private static ReimbursementService reimbursementService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public void getAllReimbursement(HttpServletResponse response) throws IOException {
		List<Reimbursement> list = reimbursementService.getAllReimbursement();
		String json = objectMapper.writeValueAsString(list);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	
	public void getPendingReimbursement(HttpServletResponse response) throws IOException {
		List<Reimbursement> list = reimbursementService.getPendingReimbursement();
		String json = objectMapper.writeValueAsString(list);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void getReimbursementByAuthor(HttpServletResponse response, int userId) throws IOException {
		List<Reimbursement> list = reimbursementService.getReimbursementByAuthor(userId);
		String json = objectMapper.writeValueAsString(list);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void addReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reimbursement = objectMapper.readValue(body, Reimbursement.class);
		
		if(reimbursementService.addReimbursement(reimbursement)) {
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
	}
	
	
}
