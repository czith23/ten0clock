package ten0clock.backend.dao;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.HttpResponse;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import ten0clock.backend.account.Ten0ClockAccount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@SuppressLint("SimpleDateFormat")
public class Ten0ClockAccountDAO {
	private final static String getAndPostAddress = "http://ten0clock.no-ip.org:9001/user";
	
	
	
	// Returns object representation of new account, or null if failed
	
	public static Ten0ClockAccount createAccount(String firstName, String lastName, String userID, String password, String email, String phoneNumber, String school, String birthday) {
		String accountCreationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		try {
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(getAndPostAddress);
			
			StringEntity jsonString = new StringEntity("{\"first_name\":\"" + firstName + "\",\"last_name\":\"" + lastName + "\",\"username\":\"" 
					+ userID + "\",\"password\":\"" + password + "\",\"account_creation_date\":\"" + accountCreationDate + "\",\"email_address\":\"" 
					+ email + "\",\"birth_date\":\"" + birthday + "\",\"phone_number\":\"" + phoneNumber + "\",\"university\":\"" + school + "\"}");
			jsonString.setContentType("application/json");
			p.setEntity(jsonString);
			
			HttpResponse r = c.execute(p);			 
            BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String s = "", response = "";
            
            while ((s = rd.readLine()) != null) {
               response += s;
            }  
            
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            
            Long internalUserID = (Long) jsonObject.get("status");
            if (internalUserID == -1) { // if the row was not created the internalUserID (row number) comes back as null
            	return null;
            }
            return new Ten0ClockAccount(internalUserID, firstName, lastName, userID, password, email, phoneNumber, school, birthday);
            
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}
	
	
	public static Ten0ClockAccount getFullAccountByUserID(String userID) {
		try {
			HttpClient c = new DefaultHttpClient();
			HttpGet g = new HttpGet(getAndPostAddress + "/" + userID);		
			HttpResponse r = c.execute(g);			 
            BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String s = "", response = "";
            while ((s = rd.readLine()) != null) {
               response += s;
            }   
            if (response.indexOf("Failure") != -1) return null;
            	
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            
            Long internalUserID = (Long) jsonObject.get("id");
        	String firstName = (String) jsonObject.get("first_name");
        	String lastName = (String) jsonObject.get("last_name");
        	String password = (String) jsonObject.get("password");
        	String email = (String) jsonObject.get("email_address"); 
        	String phoneNumber = (String) jsonObject.get("phone_number");
        	String school = (String) jsonObject.get("university");
        	String birthday = ((String) jsonObject.get("birth_date"));
        	if (birthday != null) {
        		birthday = birthday.substring(0, 10); // stupid workaround cause java is stupid
        	}
        	
            return new Ten0ClockAccount(internalUserID, firstName, lastName, userID, password, email, phoneNumber, school, birthday);          
		} catch (Exception e) {
			e.printStackTrace(); // still lazy
		}
		
		return null;

	}	
}	
