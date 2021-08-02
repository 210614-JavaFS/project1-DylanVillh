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
						
					}else {
						
						System.out.println("Invalid Login Credential.");
						
					}
				}
				break;
				
				
			case "employee":
				if(session == null) {
					
					System.out.println("You are not logged in!");
					
				}else if (session != null) {
					response.setStatus(201);
					System.out.println("im logged in hopefully");
				
				
				if (UrlSections.length == 2) {
					switch(UrlSections[1]) {
					
						case "check":
							String userName = session.getAttribute("userName").toString();
							User user = userDAO.findByUserName(userName);
							System.out.println(user);
							String json = objectMapper.writeValueAsString(user);
							response.getWriter().print(json);
							response.setStatus(201);
							break;
					
						case "allreimbursement":
							reimbursementController.getAllReimbursement(response);
							break;
							
						case "pendingreimbursement":
							reimbursementController.getPendingReimbursement(response);
							break;
							
						case "userreimbursment":
							userName = session.getAttribute("userName").toString();
							User newUser = userDAO.findByUserName(userName);
							int nameId = newUser.getUserId();
							reimbursementController.getReimbursementByAuthor(response, nameId);
							break;
							
						case "addreimbursement":
							reimbursementController.addReimbursement(request, response);
							break;
							
						case "logout":
							session.invalidate();	
							break;
					}
				}else if (UrlSections.length == 3) {
					switch(UrlSections[1]) {
					
						case "reim":
							reimbursementController.getReimbursementById(response, Integer.parseInt(UrlSections[2]));
							break;
							
						case "approve":
							String userName = session.getAttribute("userName").toString();
							User manager = userDAO.findByUserName(userName);
							System.out.println(manager);
							userController.approveReimbursement(response, manager, Integer.parseInt(UrlSections[2]));
							break;
							
						case "deny":
							userName = session.getAttribute("userName").toString();
							manager = userDAO.findByUserName(userName);
							userController.denyReimbursement(response, manager, Integer.parseInt(UrlSections[2]));
							break;
							
					default:
						break;
					}
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
