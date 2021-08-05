package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController extends HttpServlet{

	private static UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public boolean checkUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		System.out.println(body);
		User user = objectMapper.readValue(body, User.class);
		String name = user.getUsername();
		String password = user.getPassword();
		
		if(userService.userLogin(name, password)!=null) {
			user = userService.getUserByUserName(name);
			if(!user.getPassword().equals(password)) {
				System.out.println("Login Failed.");
				response.setStatus(406);
				return false;
			}
			String json = objectMapper.writeValueAsString(userService.getUserByUserName(name));
			response.getWriter().print(json);
			System.out.println("Login Successful!");
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("roleId", user.getRoleId());
			

			response.setStatus(201);

			return true;
			
		}else {
			System.out.println("Login Failed.");
			response.setStatus(406);
			return false;
		}
	}
	
	
	public void approveReimbursement(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
			
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reimbursement = objectMapper.readValue(body, Reimbursement.class);
		int reimbId = reimbursement.getReimbId();
		userService.approveReimbursement(user, reimbId);
		System.out.println("The reimbursement has been approved");
		response.setStatus(201);
			
	}
	
	
	public void denyReimbursement(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reimbursement = objectMapper.readValue(body, Reimbursement.class);
		int reimbId = reimbursement.getReimbId();
		userService.denyReimbursement(user, reimbId);
		System.out.println("The reimbursement has been rejected");
		response.setStatus(201);
	}
	
}
