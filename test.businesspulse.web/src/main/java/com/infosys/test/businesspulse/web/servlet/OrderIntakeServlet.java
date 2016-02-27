package com.infosys.test.businesspulse.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = false, name = "OrderIntakeServlet", urlPatterns = { "/orderIntake" })
public class OrderIntakeServlet extends HttpServlet
{
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.service(arg0, arg1);
        System.out.println("Started OrderIntake Servlet service");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	response.sendRedirect("index.html");
    }
    
    @Override
    public String getServletInfo()
    {
	return "Short description";
    }
    
}
