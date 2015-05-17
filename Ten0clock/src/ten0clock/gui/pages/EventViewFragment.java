package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.backend.account.Event;
import ten0clock.backend.account.Venue;
import ten0clock.gui.pages.VenuesViewFragment.VenueColumn;
import ten0clock.gui.util.EventListAdapter;
import ten0clock.gui.util.OnSwipeTouchListener;
import ten0clock.gui.util.VenueListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EventViewFragment extends Fragment {
	public enum EventColumn {
		NAME,
		DATE,
		LOCATION
	}

	private View eventsViewView;
	private ArrayList<HashMap<EventColumn,String>> eventList = new ArrayList<HashMap<EventColumn,String>>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private String listingName;
	private int currentPosition;
	
	public EventViewFragment() {
		listingName = "Matching Venues";
	}
	
	public EventViewFragment(String name) {
		listingName = name;
	}
	
	public EventViewFragment(String name, ArrayList<Event> vs) {
		listingName = name;
		events = vs;
		for (Event e : events) {
			HashMap<EventColumn, String> hMap = new HashMap<EventColumn, String>();
			hMap.put(EventColumn.NAME, e.Name());
			hMap.put(EventColumn.DATE, e.Date().toString());
			hMap.put(EventColumn.LOCATION, e.Location());
			eventList.add(hMap);
		}
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_event_view, container, false);
        eventsViewView = (RelativeLayout) rootView.findViewById(R.id.eventListingView);

        initializeComponents();
        return rootView;
    }
	
	public void initializeComponents() {
//    	eventsViewView.setOnTouchListener(new OnSwipeTouchListener(eventsViewView.getContext()) {
//    		@Override
//    		public void onSwipeRight() {
//    			FragmentManager fManager = getFragmentManager();
//    			Fragment venuesSearchFragment = new VenuesSearchFragment();
//    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
//    		}
//    	});
//    	eventsViewView.findViewById(R.id.venueListing).setOnTouchListener(new OnSwipeTouchListener(eventsViewView.getContext()) {
//    		@Override
//    		public void onSwipeRight() {
//    			FragmentManager fManager = getFragmentManager();
//    			Fragment venuesSearchFragment = new VenuesSearchFragment();
//    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
//    		}
//    	});
    	
    	TextView vTitle = (TextView) eventsViewView.findViewById(R.id.eventListingLabel);
    	vTitle.setText(listingName);
    	
    	ListView lView = (ListView) eventsViewView.findViewById(R.id.eventListing);
    	lView.setAdapter(new EventListAdapter(this, eventList));	
    	
    	lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
				currentPosition = position;
				FragmentManager fManager = getFragmentManager();
    			Fragment venuesDetailFragment = new EventDetailFragment(events.get(currentPosition));
    			fManager.beginTransaction().replace(R.id.mainContent, venuesDetailFragment).commit();
			}
    		
    	});
	}
}
