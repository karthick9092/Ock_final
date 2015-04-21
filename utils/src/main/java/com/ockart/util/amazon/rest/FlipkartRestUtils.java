package com.ockart.util.amazon.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public abstract class FlipkartRestUtils {

	 /***
     * queries the URL and gets back the response as string.
     * @param urlString
     * @return
     * @throws AffiliateAPIException, with different error codes explained.
     */
    public String queryService(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Fk-Affiliate-Token", getAffiliateToken());
            con.setRequestProperty("Fk-Affiliate-Id", getAffiliateId());
            int status = con.getResponseCode();

            switch(status) {

                case HttpURLConnection.HTTP_GONE:
                    // The timestamp is expired.
                    throw new Exception("URL expired");

                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    // The API Token or the Tracking ID is invalid.
                    throw new Exception("API Token or Affiliate Tracking ID invalid.");

                case HttpURLConnection.HTTP_FORBIDDEN:
                    // Tampered URL, i.e., there is a signature mismatch.
                    // The URL contents are modified from the originally returned value.
                    throw new Exception("Tampered URL - The URL contents are modified from the originally returned value");

                case HttpURLConnection.HTTP_OK:

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    return response.toString();

                default:
                    throw new Exception("Connection error with the Affiliate API service: HTTP/" + status);
            }
        } catch (Exception e) {
        	
        }
		return urlString;
    }


    /***
     *
     * @return the locally stored product directory information (A list of categories and the corresponding URLs).
     * Originally updated using initializeProductDirectory() and it should be updated again if the URLs are expired.
     * @throws Exception 
     */
    public abstract Map<String, String> getProductDirectory() throws Exception;

    /***
     *
     * @param category
     * @return list of products for the given categery from the API service.
     * @throws AffiliateAPIException
     */
    //abstract List<ProductInfo> getProductList(String category) throws Exception;/

    // Affiliate related information.
    public abstract String getAffiliateId();
    public abstract String getAffiliateToken();
 
}
