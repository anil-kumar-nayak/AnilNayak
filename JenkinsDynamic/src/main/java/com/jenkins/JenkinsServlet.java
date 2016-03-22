package com.jenkins;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JenkinsServlet extends HttpServlet {
	
	
	
	
	

	Properties prop= null;
		public  Properties getProperty() throws IOException
		{
		     prop = new Properties();
			String propFileName ="test.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			return prop;
			
		}
		
		
		 @Override
			public void init() throws ServletException {
			 
			 try {
				prop = getProperty();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
				
			}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		

		String username=prop.getProperty("username");
		String password = prop.getProperty("password");
		pw.println("Username is :"+username);
		pw.println("Password is :"+password);
		pw.close();
		
	}
	

}
