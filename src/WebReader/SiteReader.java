/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author himanshupahuja
 */
public class SiteReader {
    
    
    ArrayList<String> localURLS;
    
    
    public static void main(String [] args)
    {

        int indexOf = 0;
        String url = "http://www.desirulez.net",substr,parentstr;
        substr = FilterContent(url,"Colors Channel");
        System.out.println("St 1 "+ substr);
        
        parentstr = substr;
        substr = FilterContent(url+"/"+parentstr,"Comedy Nights");
         System.out.println("St 2a "+ substr);
         
        substr = FilterContent(url+"/"+parentstr,"Bigg Boss");
         System.out.println("St 2b "+ substr);
         
        substr = FilterContent(url+"/"+parentstr,"Twenty Four");
         System.out.println("St 2c "+ substr);
    }
    
    
    private static String FilterContent(String url, String keyword)
    {
        int indexOf = 0;
        String substr;
        String response = APICall(url);
        
        //System.out.println("API RESP : \n" + response);
        indexOf = response.indexOf(keyword);
        substr = response.substring(indexOf-50, indexOf+50);
        //System.out.println("API RESP : \n" + substr.indexOf("<a href") + " : " + substr.indexOf("</a>"));
        
        substr = substr.substring(substr.indexOf("<a href=") , substr.indexOf("</a>"));
        //System.out.println("API RESP : \n" +substr + "-->" +  substr.indexOf("=\"") + ":" + substr.indexOf("\">") + ":" + substr.length());
        substr = substr.substring(substr.indexOf("=\"") , substr.indexOf("\">"));
        //System.out.println("API RESP : \n" +substr);
        substr = substr.replace("=\"", "");
        
        return substr;
    }
    
    
    private static String APICall(String url) {
                
        try
        {
		
 
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
 
		// add request header
		//request.addHeader("User-Agent", USER_AGENT);
 
		HttpResponse response = client.execute(request);
 
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + 
                //       response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        
	}
    
    
    
    
}
