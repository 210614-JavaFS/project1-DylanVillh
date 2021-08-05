package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.services.UserService;

public class FrontController extends HttpServlet{
	
	private ReimbursementController reimbursementController = new ReimbursementController();
	private UserDAO userDAO = new UserDAOImpl();
	private UserController userController = new UserController(); 
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json");
		response.setStatus(404);
		
		final String URL = request.getRequestURI().replace("/project1/", "");
		
		System.out.println(URL);
		
		String[] UrlSections = URL.split("/"); 
		
		switch(UrlSections[0]) {
			case "login":
				if (request.getMethod().equals("POST")) {
					if(userController.checkUser(request, response)) {						
						System.out.println("User Login is successful");	
						String username = session.getAttribute("username").toString();
						User user = userDAO.findByUserName(username);
						String json = objectMapper.writeValueAsString(user);
						response.getWriter().print(json);
						
					}else {					
						System.out.println("Invalid Login Credential.");					
					}
				}
				break;
				
				
			case "employee":
				if(session == null) {
					
					System.out.println("You are not logged in!");
				}
					
				
				
				if (UrlSections.length == 2) {
					switch(UrlSections[1]) {
					
						case "checkemployee":
							String username = session.getAttribute("username").toString();
							User user = userDAO.findByUserName(username);
							System.out.println(user);
							String json = objectMapper.writeValueAsString(user);
							response.getWriter().print(json);
							response.setStatus(201);
							break;
					
						case "userreimbursments":
							username = session.getAttribute("username").toString();
							User currentUser= userDAO.findByUserName(username);
							int userId = currentUser.getUserId();
							reimbursementController.getReimbursementByAuthor(response, userId);
							break;
							
						case "addreimbursement":
							reimbursementController.addReimbursement(request, response);
							break;
							
						case "logout":
							session.invalidate();	
							break;
		
					default:
						break;
					}
				}
				
			case "manager":
				if(session == null) {
					
					System.out.println("You are not logged in!");
				}
				
				
				if (UrlSections.length == 2) {
					switch(UrlSections[1]) {
					
						case "checkmanager":
							String username = session.getAttribute("username").toString();
							User user = userDAO.findByUserName(username);
							System.out.println(user);
							String json = objectMapper.writeValueAsString(user);
							response.getWriter().print(json);
							response.setStatus(201);
							break;
					
						case "allreimbursements":
							reimbursementController.getAllReimbursement(response);
							break;
							
						case "pendingreimbursements":
							reimbursementController.getPendingReimbursement(response);
							break;
						
						case "approve":
							username = session.getAttribute("username").toString();
							User manager = userDAO.findByUserName(username);
							System.out.println(manager);
							userController.approveReimbursement(request, response, manager);
							break;
							
						case "deny":
							username = session.getAttribute("username").toString();
							manager = userDAO.findByUserName(username);
							userController.denyReimbursement(request, response, manager);
							break;
							
						case "logout":
							session.invalidate();	
							break;
							
					default:
						break;
					}

			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request,response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	
}
