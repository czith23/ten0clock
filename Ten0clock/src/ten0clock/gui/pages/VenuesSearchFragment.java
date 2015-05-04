package ten0clock.gui.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
	public VenuesSearchFragment() {
		
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
    }
}
