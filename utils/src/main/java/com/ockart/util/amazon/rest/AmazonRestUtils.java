package com.ockart.util.amazon.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.ockart.entity.amazon.com.ecs.client.jax.ItemSearchResponse;

@Service
public class AmazonRestUtils {

	public ItemSearchResponse getAmazonResponse(String requestURL) {
		
		ItemSearchResponse itemSearchResponse = null;		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/xml");
			if (urlConnection.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP error code : " + urlConnection.getResponseCode());
	           }
            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
            String responseXml = br.readLine();
            System.out.println(responseXml);
            urlConnection.disconnect();
            
            
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemSearchResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			itemSearchResponse = (ItemSearchResponse) unmarshaller
					.unmarshal(new StringReader(responseXml));
			System.out.println("checking");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemSearchResponse;
	}
}
