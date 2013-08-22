package com.migs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



  public class PruebaMigs extends HttpServlet{

	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		    String url = "https://migs.mastercard.com.au/vpcdps";
	 
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("vpc_Version", "1"));
			urlParameters.add(new BasicNameValuePair("vpc_Command", "pay"));
			urlParameters.add(new BasicNameValuePair("vpc_MerchTxnRef", "test1234/1"));
			urlParameters.add(new BasicNameValuePair("vpc_Merchant", "TESTWEBNAB01"));
			urlParameters.add(new BasicNameValuePair("vpc_AccessCode", "6AB89F3"));
			urlParameters.add(new BasicNameValuePair("vpc_OrderInfo", "TEST123"));
			urlParameters.add(new BasicNameValuePair("vpc_Amount", "4995"));
			urlParameters.add(new BasicNameValuePair("vpc_CardNum", "5555555555554444"));
			urlParameters.add(new BasicNameValuePair("vpc_CardExp", "1401"));
			urlParameters.add(new BasicNameValuePair("vpc_CardSecurityCode", "123"));
	 
	 
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
	 
			HttpResponse response1 = client.execute(post);
			
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + response1.getStatusLine().getStatusCode());
			System.out.println("\n");
	 
			BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			for(String migsResponse : result.toString().split("&")){
				//System.out.println(migsResponse);
				for(String migsResponseValue : migsResponse.split("=")){
					System.out.println(migsResponseValue);
				}
				
				
			}
			
		
	  }
	  
	  public void init() throws ServletException {
			// Put your code here
		}
	  
	 
}