package ten0clock.gui.pages;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VenueDetailFragment extends Fragment{
	private RelativeLayout venuesDetailView;
	private Venue venue;
	private User user;
	
	public VenueDetailFragment(Venue v, User u) {
		venue = v;
		user = u;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_venue_detail, container, false);
        venuesDetailView = (RelativeLayout) rootView.findViewById(R.id.venueDetailView);

        initializeComponents();
        return rootView;
    }
	
	public void initializeComponents() {
    	venuesDetailView.setOnTouchListener(new OnSwipeTouchListener(venuesDetailView.getContext()) {
    		@Override
    		public void onSwipeLeft() {
    			FragmentManager fManager = getFragmentManager();
    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
    		}
    	});
    	
    	if (venue != null) { 
	    	TextView name = (TextView) venuesDetailView.findViewById(R.id.venueName);
	    	TextView location = (TextView) venuesDetailView.findViewById(R.id.venueLocation);
	    	TextView atmosphere = (TextView) venuesDetailView.findViewById(R.id.venueAtmosphere);
	    	TextView volume = (TextView) venuesDetailView.findViewById(R.id.venueVolume);
	    	name.setText(venue.Name());
	    	location.setText(venue.Location());
	    	atmosphere.setText(venue.Atmosphere());
	    	volume.setText(venue.Volume());
    	}
    	
    	ImageView favoriteButton = (ImageView) venuesDetailView.findViewById(R.id.venueFavoriteButton);
    	final Venue tmpV = this.venue;
    	favoriteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user.Venues().add(tmpV);
				Toast.makeText(venuesDetailView.getContext(), tmpV.Name()+" added to Favorites", Toast.LENGTH_LONG).show();
			}
    		
    	});
    	
    	Button pollsButton = (Button) venuesDetailView.findViewById(R.id.viewPollsButton);
    	Button eventsButton = (Button) venuesDetailView.findViewById(R.id.viewEventsButton);
    	
    	pollsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fManager = getFragmentManager();
    			Fragment pollsViewFragment = new PollViewFragment("Polls for "+venue.Name(),venue.Polls(), user);
    			fManager.beginTransaction().replace(R.id.mainContent, pollsViewFragment).commit();
			}
    		
    	});
    	
    	eventsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment eventViewFragment = new EventViewFragment("Events for "+venue.Name(),venue.Events(), user);
    			fManager.beginTransaction().replace(R.id.mainContent, eventViewFragment).commit();
			}
    		
    	});
    	
    	Button checkInButton = (Button) venuesDetailView.findViewById(R.id.checkinButton);
    	Button chatButton = (Button) venuesDetailView.findViewById(R.id.venueChatButton);
    	
    	checkInButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user.checkIn(venue);
				Toast.makeText(venuesDetailView.getContext(), "You just checked in to "+venue.Name()+"!", Toast.LENGTH_LONG).show();
			}
    		
    	});
    	
    	chatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment chatFragment = new BubbleChatFragment(venue, user);
    			fManager.beginTransaction().replace(R.id.mainContent, chatFragment).commit();
			}
    		
    	});
    	
	}
    	
}
