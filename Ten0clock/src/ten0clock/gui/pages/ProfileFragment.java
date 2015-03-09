package ten0clock.gui.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * Profile Page
 * ------------
 * Displays user information, pictures, favorites, and current location
 */
public class ProfileFragment extends Fragment {
	private View profileView;
	public ProfileFragment() {
		
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profileView = (RelativeLayout) rootView.findViewById(R.id.profileView);
        initializeComponents();
        return rootView;
    }
    
    // This generates information based on the current Account and fills in the profile
    // slots
    public void initializeComponents() {
    	TextView profileName = (TextView) profileView.findViewById(R.id.profName);
    	profileName.setText("Chuck Taylor");
    	
    	TextView profileNum = (TextView) profileView.findViewById(R.id.favoritesNum);
    	profileNum.setText("9");
    	
    	Button viewFavoritesButton = (Button) profileView.findViewById(R.id.viewFavoritesButton);
    	viewFavoritesButton.setText("View Favorites");
    	
    	TextView location = (TextView) profileView.findViewById(R.id.location);
    	location.setText("Panera Bread");
    	
    	Button viewLocationButton = (Button) profileView.findViewById(R.id.viewLocationButton);
    	viewLocationButton.setText("View Location");
    }
}
