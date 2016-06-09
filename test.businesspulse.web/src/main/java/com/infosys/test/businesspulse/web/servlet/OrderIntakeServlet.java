package com.infosys.test.businesspulse.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(asyncSupported = false, name = "OrderIntakeServlet")
public class OrderIntakeServlet extends HttpServlet
{
	private static final Logger LOG =LoggerFactory.getLogger(OrderIntakeServlet.class);
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.service(arg0, arg1);
        LOG.trace("Started OrderIntake Servlet service");
        
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
