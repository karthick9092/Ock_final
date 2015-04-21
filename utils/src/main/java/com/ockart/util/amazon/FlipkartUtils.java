/***
 * The class to parse JSON data.
 * 
 * @author Logesh 
 * @version 1.0
 * Copyright (c) Refulgent Technologies
 */

package com.ockart.util.amazon;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ockart.entity.common.OckFlipkartConfig;
import com.ockart.util.amazon.bean.ProductInfo;
import com.ockart.util.amazon.helper.FlipkartCategoryConstant;
import com.ockart.util.amazon.helper.ProvCommonConstants;
import com.ockart.util.amazon.repository.FlipkartConfigRepository;
import com.ockart.util.amazon.rest.FlipkartRestUtils;

@Service
public class FlipkartUtils extends FlipkartRestUtils{
	
	@Autowired
	FlipkartConfigRepository flipkartConfigRepository;
	
	private String affiliateId;
    private String affiliateToken;
    private String affiliateBaseUrl;
    private Map<String, String> productDirectory;
    OckFlipkartConfig ockFlipkartConfig = null;
       		 
	/***
	 * Initializing all the config credentials from database
	 */
	
    public void initializeConfigDetails() {
		// TODO Auto-generated method stub
    	try {
    		productDirectory = new HashMap<String, String>();
    		this.ockFlipkartConfig = flipkartConfigRepository.findByStatus(ProvCommonConstants.ACTIVE);
    		this.affiliateId = ockFlipkartConfig.getAffiliateId();
    		this.affiliateToken = ockFlipkartConfig.getAffiliateToken();
    		this.affiliateBaseUrl = ockFlipkartConfig.getBaseUrl() + this.affiliateId  + ".json";
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
	}

	/*** 
     * Getting list of URL of  all the categories from rest request and stored it in a hashmap
     * @return false if initialization fails
     * @throws AffiliateAPIException x
     */
					      
    public Map<String, String> getFKProductDirectory() throws Exception {
    	
        try {
        	
            // Query the API service and get back the result.
            String jsonData = queryService(affiliateBaseUrl);
            System.out.println("Category Json >>>>>"+jsonData);
      
            // Bookkeep the retrieved data in a local productDirectory Map.
            JSONObject obj = new JSONObject(jsonData);
            JSONObject listing = obj.getJSONObject("apiGroups").getJSONObject("affiliate").getJSONObject("apiListings");
            Iterator keys = listing.keys();
            while(keys.hasNext()) {

                String category_name = (String)keys.next();
                JSONObject variants = listing.getJSONObject(category_name).getJSONObject("availableVariants");

                // Sort the variants and get the latest version
                Iterator v_iterator = variants.keys();
                List<String> variant_keys = new ArrayList<String>();
                while(v_iterator.hasNext()) {
                    variant_keys.add((String)v_iterator.next());
                }
                Collections.sort(variant_keys, Collections.reverseOrder());
                String category_url = variants.getJSONObject(variant_keys.get(0)).getString("get");
                productDirectory.put(category_name, category_url);
            }
        }
        catch(JSONException je) {
            
        }
        return productDirectory;
    }
    
    
    /***
    *
    * @param category
     * @param URL 
    * @return list of products for the given category from the API service.
    * @throws AffiliateAPIException
    */
    
   public List<ProductInfo> getProductList(String category, String URL) throws Exception {
	          
	   List<ProductInfo> plist = new ArrayList<ProductInfo>();
       try {    	   
    	   if (category.equals(FlipkartCategoryConstant.BAGS_WALLETS_BELTS)) {
    		   	 String queryUrl = URL;
    		   	 while (queryUrl != null && !queryUrl.isEmpty()) {	    		   	   
		               String jsonData = queryService(queryUrl);	               		
		               System.out.println(category + "JSON >>>>>" + jsonData);
		               JSONObject obj = new JSONObject(jsonData);
		               JSONArray productArray = obj.getJSONArray("productInfoList");	               
			               for(int i =0; i < productArray.length(); i++) {		            				               
				            	   ProductInfo pinfo = new ProductInfo();
				                   JSONObject inner_obj = productArray.getJSONObject(i).getJSONObject("productBaseInfo");
				                   pinfo.setId(inner_obj.getJSONObject("productIdentifier").getString("productId"));			
				                   JSONObject attributes = inner_obj.getJSONObject("productAttributes");
				                   pinfo.setTitle(attributes.getString("title"));
				                   pinfo.setDescription(attributes.optString("productDescription", ""));
				                   pinfo.setMrp(attributes.getJSONObject("maximumRetailPrice").getDouble("amount"));
				                   pinfo.setSellingPrice(attributes.getJSONObject("sellingPrice").getDouble("amount"));
				                   pinfo.setProductUrl(attributes.getString("productUrl"));
				                   pinfo.setInStock(attributes.getBoolean("inStock"));
				                   pinfo.setEmiAvailable(attributes.getBoolean("emiAvailable"));			
				                   plist.add(pinfo);
				               }
			               
		               // Fetch the products from the next URL. Here we set the limit to 500 products.
			               
		               queryUrl = obj.optString("nextUrl", "");
		               if(queryUrl != null && !queryUrl.isEmpty() && plist.size() > 500) { queryUrl = ""; }
	    		  }
    	   }
       }
       catch(JSONException je) {
    	   
       } catch (Exception e) {
    	   e.printStackTrace();
    	   
       }       
       return plist;
   }

    
   
	public String getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getAffiliateToken() {
		return affiliateToken;
	}

	public void setAffiliateToken(String affiliateToken) {
		this.affiliateToken = affiliateToken;
	}

	public String getAffiliateBaseUrl() {
		return affiliateBaseUrl;
	}

	public void setAffiliateBaseUrl(String affiliateBaseUrl) {
		this.affiliateBaseUrl = affiliateBaseUrl;
	}

	public Map<String, String> getProductDirectory() {
		return productDirectory;
	}

	public void setProductDirectory(Map<String, String> productDirectory) {
		this.productDirectory = productDirectory;
	}
}
