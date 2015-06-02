package ten0clock.gui.pages;

import java.util.ArrayList;

import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.backend.account.Venue.Atmosphere;
import ten0clock.backend.account.Venue.Volume;
import ten0clock.gui.util.OnSwipeTouchListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/* 
 * Venues Search Page
 * ------------------
 * This page allows users to look up venues in their area
 * based on a wide range of criteria
 */
public class VenuesSearchFragment extends Fragment {
	private View venuesView;
	private User user;
	
	public VenuesSearchFragment(User u) {
		user = u;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_venue, container, false);
        venuesView = (LinearLayout) rootView.findViewById(R.id.venueView);
        initializeComponents();
        return rootView;
    }
    
    public void initializeComponents() {
    	TextView vNameSearchLabel = (TextView) venuesView.findViewById(R.id.vNameSearchLabel);
    	vNameSearchLabel.setText("Venue Name: ");
    	
    	TextView vLocationSearchLabel = (TextView) venuesView.findViewById(R.id.vLocationSearchLabel);
    	vLocationSearchLabel.setText("Location: ");
    	
    	TextView vAtmosphereSearchLabel = (TextView) venuesView.findViewById(R.id.vAtmosphereSearchLabel);
    	vAtmosphereSearchLabel.setText("Atmosphere: ");
    	
    	TextView vMenuItemSearchLabel = (TextView) venuesView.findViewById(R.id.vMenuItemSearchLabel);
    	vMenuItemSearchLabel.setText("Menu Item: ");
    	
    	TextView vVolumeSearchLabel = (TextView) venuesView.findViewById(R.id.vVolumeSearchLabel);
    	vVolumeSearchLabel.setText("Volume: ");
    	
    	TextView vFriendSearchLabel = (TextView) venuesView.findViewById(R.id.vFriendSearchLabel);
    	vFriendSearchLabel.setText("Friends: ");
    	
    	TextView vEventSearchLabel = (TextView) venuesView.findViewById(R.id.vEventSearchLabel);
    	vEventSearchLabel.setText("Event: ");
    	
    	Button vSearchButton = (Button) venuesView.findViewById(R.id.vSearchButton);
    	vSearchButton.setText("Search Venues");
    	vSearchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
    			ArrayList<Venue> vs = new ArrayList<Venue>();
    			Venue v1 = new Venue("Howl at The Moon", "258 S 15th St.", Atmosphere.LIVEMUSIC, Volume.BUSY);
    			Venue v2 = new Venue("Union Transfer","1026 Spring Garden St.",Atmosphere.LIVEMUSIC, Volume.AVERAGE);
    			Venue v3 = new Venue("The Mann Center","5201 Parkside Ave.",Atmosphere.LIVEMUSIC, Volume.AVERAGE);
    			vs.add(v1);
    			vs.add(v2);
    			vs.add(v3);

    			FragmentManager fManager = getFragmentManager();
    			Fragment venuesViewFragment = new VenuesViewFragment("Matching Venues", vs, user);

    			fManager.beginTransaction().replace(R.id.mainContent, venuesViewFragment).commit();
			}
    		
    	});
    	
    	venuesView.setOnTouchListener(new OnSwipeTouchListener(venuesView.getContext()) {
    		@Override
    		public void onSwipeLeft() {
    			ArrayList<Venue> vs = new ArrayList<Venue>();
    			Venue v1 = new Venue("Paddy's Irish Pub", "228 Race St.", Atmosphere.CASUAL, Volume.EMPTY);
    			Venue v2 = new Venue("Sampan","124 S. 13th",Atmosphere.FORMAL, Volume.BUSY);
    			Venue v3 = new Venue("Shake Shack","3200 Chestnut St.",Atmosphere.CASUAL, Volume.PACKED);
    			vs.add(v1);
    			vs.add(v2);
    			vs.add(v3);
    			FragmentManager fManager = getFragmentManager();
    			Fragment venuesViewFragment = new VenuesViewFragment("Your Favorite Venues",vs, user);

    			fManager.beginTransaction().replace(R.id.mainContent, venuesViewFragment).commit();
    		}
    	});
    	
    	
    }
}
