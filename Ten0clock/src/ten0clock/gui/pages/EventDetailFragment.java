package ten0clock.gui.pages;

import ten0clock.backend.account.Event;
import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.gui.util.OnSwipeTouchListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventDetailFragment extends Fragment {
	private Event event;
	private RelativeLayout eventDetailView;
	private User user;
	
	public EventDetailFragment(Event e, User u) {
		event = e;
		user = u;
	}

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);
        eventDetailView = (RelativeLayout) rootView.findViewById(R.id.eventDetailView);

        initializeComponents();
        return rootView;
    }
	
	public void initializeComponents() {
//    	eventDetailView.setOnTouchListener(new OnSwipeTouchListener(eventDetailView.getContext()) {
//    		@Override
//    		public void onSwipeLeft() {
//    			FragmentManager fManager = getFragmentManager();
//    			Fragment venuesSearchFragment = new VenuesSearchFragment();
//    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
//    		}
//    	});
    	
    	if (event != null) { 
	    	TextView name = (TextView) eventDetailView.findViewById(R.id.eventName);
	    	TextView location = (TextView) eventDetailView.findViewById(R.id.eventLocation);
	    	TextView date = (TextView) eventDetailView.findViewById(R.id.eventDate);
	    	TextView category = (TextView) eventDetailView.findViewById(R.id.eventCategory);
	    	name.setText(event.Name());
	    	location.setText(event.Location());
	    	date.setText(event.Date().toString());
	    	category.setText(event.Category());
    	}
    	
    	Button viewPollButton = (Button) eventDetailView.findViewById(R.id.eventPollsButton);
    	viewPollButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fManager = getFragmentManager();
    			Fragment pollListingFragment = new PollViewFragment("Polls for "+event.Name(),event.Polls(), user);
    			fManager.beginTransaction().replace(R.id.mainContent, pollListingFragment).commit();
			}
    		
    	});
    	
    	Button viewVenueButton = (Button) eventDetailView.findViewById(R.id.eventVenueButton);
    	viewVenueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fManager = getFragmentManager();
    			Fragment venueDetailFragment = new VenueDetailFragment(event.getVenue(), user);
    			fManager.beginTransaction().replace(R.id.mainContent, venueDetailFragment).commit();
				
			}
    		
    	});
	}
}
