package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{

	//Get Method
	public void get(String url) throws ClientProtocolException, IOException
	
	{
		CloseableHttpClient httpClient= HttpClients.createDefault(); 	// CreateDEfault method will create one client connection which return one closeable http client object		
		HttpGet httpGet=new HttpGet(url);  								// It will create one get connection with URL so this is http get request		
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); // For executing the URL
		
		// a. Getting Status Code
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->>>"+statusCode);
		
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");//The entire json code will convert to string and it will store in object i.e. response String	
		//UTF is the standard
		
		JSONObject responseJson = new JSONObject(responseString);// Whole code will again convert to json and will save in responseJson object.
		System.out.println("Response JSON from API--->>>"+responseJson);
		
		Header[] headersArray= closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders= new HashMap<String, String>();
		
		for(Header header: headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--->>"+allHeaders);
		
	}
	
	
	
}
