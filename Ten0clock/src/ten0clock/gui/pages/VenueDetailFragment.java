package ten0clock.gui.pages;

import ten0clock.backend.account.Venue;
import ten0clock.gui.util.OnSwipeTouchListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VenueDetailFragment extends Fragment{
	private RelativeLayout venuesDetailView;
	private Venue venue;
	
	public VenueDetailFragment(Venue v) {
		venue = v;
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
    			Fragment venuesSearchFragment = new VenuesSearchFragment();
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
	}
    	
}
