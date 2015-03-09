package ten0clock.backend.dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Ten0ClockFriendshipDAO {
	private final static String requestAddress = "http://ten0clock.no-ip.org:9001/friendrequest";
	private final static String requestAcceptAddress = "http://ten0clock.no-ip.org:9001/friendrequestaccept";
	private final static String requestDenyAddress = "http://ten0clock.no-ip.org:9001/friendrequestdeny";
	
	public static boolean requestFriendship(Long requesterInternalId, Long requesteeInternalId) {
		try {
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(requestAddress);
			
			StringEntity jsonString = new StringEntity("{\"requester_id\":\"" + requesterInternalId + "\",\"requestee_id\":\"" + requesteeInternalId + "\"}");
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
            
            String status = (String) jsonObject.get("status");
            if (!status.equals("success")) {
            	return false;
            }
            return true;
            
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static boolean acceptFriendship(Long requesterInternalId, Long requesteeInternalId) {
		try {
			String friendshipCreationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(requestAcceptAddress);
			
			StringEntity jsonString = new StringEntity("{\"requester_id\":\"" + requesterInternalId + "\",\"requestee_id\":\"" + requesteeInternalId 
					+ "\",\"date_established\":\"" + friendshipCreationDate + "\"}");
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
            
            String status = (String) jsonObject.get("status");
            if (!status.equals("success")) {
            	return false;
            }
            return true;
            
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean denyFriendship(Long requesterInternalId, Long requesteeInternalId) {
		try {
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(requestDenyAddress);
			
			StringEntity jsonString = new StringEntity("{\"requester_id\":\"" + requesterInternalId + "\",\"requestee_id\":\"" + requesteeInternalId 
					 + "\"}");
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
            
            String status = (String) jsonObject.get("status");
            if (!status.equals("success")) {
            	return false;
            }
            return true;
            
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
	
