package com.migs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
 

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
import org.apache.commons.lang3.StringUtils;
import com.viajez.utils.Loader;




  public class PruebaMigs extends HttpServlet{

  public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
	 
				String url = "https://migs.mastercard.com.au/vpcdps";
			  	String responseString=null;
			  	HttpClient client = new DefaultHttpClient();
			  	HttpPost post = new HttpPost(url);
				
				try {
				Loader loader = new Loader("migs.properties"); 
				Properties properties = loader.getProperties();
				RequestValues requestValues = new RequestValues();	 
					
  				requestValues.setVpc_Version(properties.getProperty("vpc.version"));
				if(StringUtils.trimToNull(requestValues.getVpc_Version()) == null ){
				throw new NullPointerException("Vcp_Version is null");	
				}
					  
				requestValues.setVpc_Command(properties.getProperty("vpc.command"));
				if(StringUtils.trimToNull(requestValues.getVpc_Command()) == null ){
				throw new NullPointerException("Vcp_Command is null");	
				}
				
				requestValues.setVpc_Merchant(properties.getProperty("vpc.merchant"));
				if(StringUtils.trimToNull(requestValues.getVpc_Merchant()) == null ){
				throw new NullPointerException("Vpc_Merchant is null");	
				}
					  
				requestValues.setVpc_MerchTxnRef(properties.getProperty("vpc.merchtxnref"));
			    if(StringUtils.trimToNull(requestValues.getVpc_MerchTxnRef()) == null ){
				throw new NullPointerException("Vpc_MerchTxnRef is null");	
				}
					  
				requestValues.setVpc_AccessCode(properties.getProperty("vpc.accesscode"));
				if(StringUtils.trimToNull(requestValues.getVpc_AccessCode()) == null ){
				throw new NullPointerException("Vpc_AccessCode is null");	
				}
					  
				requestValues.setVpc_OrderInfo(properties.getProperty("vpc.orderinfo"));
				if(StringUtils.trimToNull(requestValues.getVpc_OrderInfo()) == null ){
				throw new NullPointerException("Vpc_OrderInfo is null");	
				}
					  
				requestValues.setVpc_Amount(properties.getProperty("vpc.amount"));
				if(StringUtils.trimToNull(requestValues.getVpc_Amount()) == null ){
				throw new NullPointerException("Vpc_Amount is null");	
				}
					  
				requestValues.setVpc_CardNum(properties.getProperty("vpc.cardnum"));
				if(StringUtils.trimToNull(requestValues.getVpc_CardNum()) == null ){
				throw new NullPointerException("Vpc_CardNum is null");	
				}
					  
				requestValues.setVpc_CardExp(properties.getProperty("vpc.cardexp"));
				if(StringUtils.trimToNull(requestValues.getVpc_CardExp()) == null ){
				throw new NullPointerException("Vpc_CardExp is null");	
				}
					  
				requestValues.setVpc_CardSecurityCode(properties.getProperty("vpc.cardsecuritycode"));
				if(StringUtils.trimToNull(requestValues.getVpc_CardSecurityCode()) == null ){
				throw new NullPointerException("Vpc_CardSecurityCode is null");	
				}
				
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			System.out.println("\n");
			
			urlParameters.add(new BasicNameValuePair("vpc_Version"          ,requestValues.getVpc_Version()));
			urlParameters.add(new BasicNameValuePair("vpc_Command"          ,requestValues.getVpc_Command()));
		    urlParameters.add(new BasicNameValuePair("vpc_MerchTxnRef"      ,requestValues.getVpc_MerchTxnRef()));
			urlParameters.add(new BasicNameValuePair("vpc_Merchant"         ,requestValues.getVpc_Merchant()));
		    urlParameters.add(new BasicNameValuePair("vpc_AccessCode"       ,requestValues.getVpc_AccessCode()));
			urlParameters.add(new BasicNameValuePair("vpc_OrderInfo"        ,requestValues.getVpc_OrderInfo()));
		    urlParameters.add(new BasicNameValuePair("vpc_Amount"      	 	,requestValues.getVpc_Amount()));
			urlParameters.add(new BasicNameValuePair("vpc_CardNum"     	 	,requestValues.getVpc_CardNum()));
		    urlParameters.add(new BasicNameValuePair("vpc_CardExp"          ,requestValues.getVpc_CardExp()));
			urlParameters.add(new BasicNameValuePair("vpc_CardSecurityCode" ,requestValues.getVpc_CardSecurityCode()));
		      
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		    HttpResponse response1 = client.execute(post);
		    	
		    System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + response1.getStatusLine().getStatusCode());
			System.out.println("\n");
		      
			BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
			  responseString=result.append(line).toString();
			 }
			 
			 System.out.println(responseString);	 	 
			 System.out.println("\n");
			 
			 String[] sapararResponse = responseString.split("&");
			 ResponseValues responsevalues = new ResponseValues();
			 
			 String [] amount = sapararResponse[0].split("=");
			 responsevalues.setVpc_Amount(amount[1]);
			 System.out.println("Amount= "+responsevalues.getVpc_Amount());
			 
			 String [] batchno = sapararResponse[1].split("=");
			 responsevalues.setVpc_BatchNo(batchno[1]);
			 System.out.println("Batchno = "+responsevalues.getVpc_BatchNo());
			 
			 String [] command = sapararResponse[2].split("=");
			 responsevalues.setVpc_Command(command[1]);
			 System.out.println("Command= "+responsevalues.getVpc_Command());
			 
			 String [] locale = sapararResponse[3].split("=");
			 responsevalues.setVpc_Locale(locale[1]);
			 System.out.println("Locale= "+responsevalues.getVpc_Locale());
			 
			 String [] merchtxnref = sapararResponse[4].split("=");
			 responsevalues.setVpc_MerchTxnRef(merchtxnref[1]);
			 System.out.println("Merchtxnref= "+responsevalues.getVpc_MerchTxnRef());
			 
			 String [] merchant = sapararResponse[5].split("=");
			 responsevalues.setVpc_Merchant(merchant[1]);
			 System.out.println("Merchant= "+responsevalues.getVpc_Merchant());
			 
			 String [] message = sapararResponse[6].split("=");
			 responsevalues.setVpc_Message(message[1]);
			 System.out.println("Message= "+responsevalues.getVpc_Message());
			 
			 String [] orderinfo = sapararResponse[7].split("=");
			 responsevalues.setVpc_OrderInfo(orderinfo[1]);
			 System.out.println("Orderinfo= "+responsevalues.getVpc_OrderInfo());
			 
			 String [] transactionno = sapararResponse[8].split("=");
			 responsevalues.setVpc_TransactionNo(transactionno[1]);
			 System.out.println("TransactionNo= "+responsevalues.getVpc_TransactionNo());
			 
			 String [] txnresponsecode = sapararResponse[9].split("=");
			 responsevalues.setVpc_TxnResponseCode(txnresponsecode[1]);
			 System.out.println("Txnresponsecode= "+responsevalues.getVpc_TxnResponseCode());
			 
			 String [] version = sapararResponse[10].split("=");
			 responsevalues.setVpc_Version(version[1]);
			 System.out.println("vcp Version= "+responsevalues.getVpc_Version());
			
			
			} catch (Exception e) {
					e.printStackTrace();
			} 	
	}
  
  public void init() throws ServletException {
  }
	  
	  
}
		  
		  

	  
  
  
  
  
	  
	  
