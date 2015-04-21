package com.ockart.util.amazon.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.ockart.entity.common.OckAmazonConfig;

@Service
public class SignedRequestsHelper {
    
    private static final String UTF8_CHARSET = "UTF-8";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final String stringToAppend = "GET\nwebservices.amazon.in\n/onca/xml\n";
    
    private String awsSecretKey = null;
    private SecretKeySpec secretKeySpec = null;
    private Mac mac = null;
    
    public static SignedRequestsHelper getInstance(
            String endpoint, 
            String awsAccessKeyId, 
            String awsSecretKey
    ) throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException
    {
        if (null == awsSecretKey || awsSecretKey.length() == 0)   
            { throw new IllegalArgumentException("awsSecretKey is null or empty"); }
        
        SignedRequestsHelper instance = new SignedRequestsHelper();
        instance.awsSecretKey = awsSecretKey;

        byte[] secretyKeyBytes = instance.awsSecretKey.getBytes(UTF8_CHARSET);
        instance.secretKeySpec = new SecretKeySpec(secretyKeyBytes, HMAC_SHA256_ALGORITHM);
        instance.mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        instance.mac.init(instance.secretKeySpec);
        
        return instance;
    }
    
    /**
     * The construct is private since we'd rather use getInstance()
     */
    private SignedRequestsHelper() {}

	public String prepareRequestURL(
			Map<String, String> requestParameterHashmap,
			OckAmazonConfig amazonConfig, String category, String keyword) {
    	String requestURL = null;
    	try {    		    		
    		String timeStamp = timestamp();    		
    		if (amazonConfig != null) {
				requestParameterHashmap.put(ProvCommonConstants.AWS_ACCESSKEY_ID, amazonConfig.getAccessKey());
				requestParameterHashmap.put(ProvCommonConstants.ASSOCIATETAG, amazonConfig.getAffiliateId());
				requestParameterHashmap.put(ProvCommonConstants.KEYWORDS, category);
				requestParameterHashmap.put(ProvCommonConstants.OPERATION, "ItemSearch");
				requestParameterHashmap.put(ProvCommonConstants.SEARCH_INDEX, keyword);				
				requestParameterHashmap.put(ProvCommonConstants.SERVICE, amazonConfig.getService());
				requestParameterHashmap.put(ProvCommonConstants.TIMESTAMP, timeStamp);
				requestParameterHashmap.put(ProvCommonConstants.VERSION, amazonConfig.getRequestVersion());
			}
    		
    		SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(requestParameterHashmap);
    		
    		// get the canonical form the query string
            String canonicalQS = this.canonicalize(sortedParamMap);
            
            // the URL string used to prepare signature
            String stringToSign = stringToAppend + canonicalQS;
			System.out.println("stringToSign >>>>>"+stringToSign);
            String signedString = hmac(stringToSign); 
            String signature = this.percentEncodeRfc3986(signedString);            
            System.out.println("Signed String" + signature);
            requestURL =  amazonConfig.getSearch_domain_URL() + canonicalQS  + "&Signature=" + signature;
            System.out.println("Request URL >>>>>>>" + requestURL);     	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return requestURL;		
	}

    /**
     * Compute the HMAC.
     *  
     * @param stringToSign  String to compute the HMAC over.
     * @return              base64-encoded hmac value.
     * @throws NoSuchAlgorithmException 
     */
	private String hmac(String stringToSign) throws NoSuchAlgorithmException {
        String signature = null;
        byte[] data;
        byte[] rawHmac;
        try {
            data = stringToSign.getBytes(UTF8_CHARSET);
           // mac = mac.getInstance(HMAC_SHA256_ALGORITHM);
            rawHmac = this.mac.doFinal(data);
            //Base64 encoder = new Base64();
            Base64 encoder = new Base64(76, new byte[0]);
            signature = new String(encoder.encode(rawHmac));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(UTF8_CHARSET + " is unsupported!", e);
        }	
        return signature;
    }

    /**
     * Generate a ISO-8601 format timestamp as required by Amazon.
     *  
     * @return  ISO-8601 format timestamp.
     */
    private String timestamp() {
        String timestamp = null;
        Calendar cal = Calendar.getInstance();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dfm.setTimeZone(TimeZone.getTimeZone("GMT"));
        timestamp = dfm.format(cal.getTime());
        return timestamp;
    }

    /**
     * Canonicalize the query string as required by Amazon.
     * 
     * @param sortedParamMap    Parameter name-value pairs in lexicographical order.
     * @return                  Canonical form of query string.
     */
    private String canonicalize(SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap.isEmpty()) {
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, String> kvpair = iter.next();
            buffer.append(percentEncodeRfc3986(kvpair.getKey()));
            buffer.append("=");
            buffer.append(percentEncodeRfc3986(kvpair.getValue()));
            if (iter.hasNext()) {
                buffer.append("&");
            }
        }
        String cannoical = buffer.toString();
        return cannoical;
    }

    /**
     * Percent-encode values according the RFC 3986. The built-in Java
     * URLEncoder does not encode according to the RFC, so we make the
     * extra replacements.
     * 
     * @param s decoded string
     * @return  encoded string per RFC 3986
     */
    private String percentEncodeRfc3986(String s) {
        String out;
        try {
            out = URLEncoder.encode(s, UTF8_CHARSET)
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            out = s;
        }
        return out;
    }

    /**
     * Takes a query string, separates the constituent name-value pairs
     * and stores them in a hashmap.
     * 
     * @param queryString
     * @return
     */
    private Map<String, String> createParameterMap(String queryString) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = queryString.split("&");

        for (String pair: pairs) {
            if (pair.length() < 1) {
                continue;
            }

            String[] tokens = pair.split("=",2);
            for(int j=0; j<tokens.length; j++)
            {
                try {
                    tokens[j] = URLDecoder.decode(tokens[j], UTF8_CHARSET);
                } catch (UnsupportedEncodingException e) {
                }
            }
            switch (tokens.length) {
                case 1: {
                    if (pair.charAt(0) == '=') {
                        map.put("", tokens[0]);
                    } else {
                        map.put(tokens[0], "");
                    }
                    break;
                }
                case 2: {
                    map.put(tokens[0], tokens[1]);
                    break;
                }
            }
        }
        return map;
    }


	
}
