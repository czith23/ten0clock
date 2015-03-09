package ten0clock.backend.dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ten0clock.backend.event.Ten0ClockEvent;

public class Ten0ClockEventDAO {
	private final static String postAddress = "http://ten0clock.no-ip.org:9001/event";
	
	public static Ten0ClockEvent createEvent(Long creatorInternalUserID, Long venueID, Timestamp dateTime, String eventName, String description) {
		try {
			HttpClient c = new DefaultHttpClient();
			HttpPost p = new HttpPost(postAddress);
			
			StringEntity jsonString = new StringEntity("{\"creator_user_id\":\"" + creatorInternalUserID + "\",\"venue_id\":\"" + venueID + "\",\"datetime\":\"" 
					+ dateTime + "\",\"name\":\"" + eventName + "\",\"description\":\"" + description + "\"}");
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
            
            Long eventID = (Long) jsonObject.get("status");
            if (eventID == -1) {
            	return null;
            }
            return new Ten0ClockEvent(eventID, creatorInternalUserID, venueID, dateTime, eventName, description);
            
            // need extra logic to add user to user_event table
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	

}
