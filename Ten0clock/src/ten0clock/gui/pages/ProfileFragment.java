package ten0clock.gui.pages;

import ten0clock.backend.account.User;
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

/*
 * Profile Page
 * ------------
 * Displays user information, pictures, favorites, and current location
 */
public class ProfileFragment extends Fragment {
	private View profileView;
	private User user;
	
	public ProfileFragment(User u) {
		user = u;
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
    	profileName.setText(user.Name());
    	
    	TextView location = (TextView) profileView.findViewById(R.id.location);
    	if (user.MostRecent() == null) {
    		location.setText("Never Checked In");
    	}
    	else {
    	    location.setText(user.MostRecent().Name());
    	}
    	TextView university = (TextView) profileView.findViewById(R.id.university);
    	TextView bday = (TextView) profileView.findViewById(R.id.bday);
    	TextView userid = (TextView) profileView.findViewById(R.id.userId);
    	university.setText(user.University());
    	bday.setText(user.Birthday().toString());
    	userid.setText(user.UserID());
    	
    	Button eventButton = (Button) profileView.findViewById(R.id.profileEventButton);
    	Button venuesButton = (Button) profileView.findViewById(R.id.profileVenuesButton);
    	eventButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment tFragment = new EventViewFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, tFragment).commit();
			}
    		
    	});
    	
    	venuesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment eventCreateFragment = new VenuesViewFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, eventCreateFragment).commit();
			}
    		
    	});
    }
}
