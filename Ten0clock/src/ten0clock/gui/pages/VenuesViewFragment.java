package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.gui.util.OnSwipeTouchListener;
import ten0clock.gui.util.VenueListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VenuesViewFragment extends Fragment {
	public enum VenueColumn{
		VENUENAME,
		VENUELOCATION
	}
	
	private View venuesViewView;
	private ArrayList<HashMap<VenueColumn,String>> venueList = new ArrayList<HashMap<VenueColumn,String>>();
	private ArrayList<Venue> venues = new ArrayList<Venue>();
	private String listingName;
	private int currentPosition;
	private User user;
	
	public VenuesViewFragment(User u) {
		listingName = "Matching Venues";
		user = u;
		venues = u.Venues();
		for (Venue v : venues) {
			HashMap<VenueColumn, String> hMap = new HashMap<VenueColumn, String>();
			hMap.put(VenueColumn.VENUENAME, v.Name());
			hMap.put(VenueColumn.VENUELOCATION, v.Location());
			venueList.add(hMap);
		}
		user = u;
	}
	
	public VenuesViewFragment(String name, User u) {
		listingName = name;
		venues = u.Venues();
		for (Venue v : venues) {
			HashMap<VenueColumn, String> hMap = new HashMap<VenueColumn, String>();
			hMap.put(VenueColumn.VENUENAME, v.Name());
			hMap.put(VenueColumn.VENUELOCATION, v.Location());
			venueList.add(hMap);
		}
		user = u;
	}
	
	public VenuesViewFragment(String name, ArrayList<Venue> vs, User u) {
		listingName = name;
		venues = vs;
		for (Venue v : venues) {
			HashMap<VenueColumn, String> hMap = new HashMap<VenueColumn, String>();
			hMap.put(VenueColumn.VENUENAME, v.Name());
			hMap.put(VenueColumn.VENUELOCATION, v.Location());
			venueList.add(hMap);
		}
		user = u;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_venue_view, container, false);
        venuesViewView = (RelativeLayout) rootView.findViewById(R.id.venueListingView);

        initializeComponents();
        return rootView;
    }
	
	public void initializeComponents() {
    	venuesViewView.setOnTouchListener(new OnSwipeTouchListener(venuesViewView.getContext()) {
    		@Override
    		public void onSwipeRight() {
    			FragmentManager fManager = getFragmentManager();
    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
    		}
    	});
    	venuesViewView.findViewById(R.id.venueListing).setOnTouchListener(new OnSwipeTouchListener(venuesViewView.getContext()) {
    		@Override
    		public void onSwipeRight() {
    			FragmentManager fManager = getFragmentManager();
    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
    		}
    	});
    	Button searchButton = (Button) venuesViewView.findViewById(R.id.venueSearchButtonFromView);
    	searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
			}
    		
    	});
    	
    	
    	TextView vTitle = (TextView) venuesViewView.findViewById(R.id.venueListingLabel);
    	vTitle.setText(listingName);
    	
    	ListView lView = (ListView) venuesViewView.findViewById(R.id.venueListing);
    	lView.setAdapter(new VenueListAdapter(this, venueList));	
    	
    	lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
				currentPosition = position;
				FragmentManager fManager = getFragmentManager();
    			Fragment venuesDetailFragment = new VenueDetailFragment(venues.get(currentPosition), user);
    			fManager.beginTransaction().replace(R.id.mainContent, venuesDetailFragment).commit();
			}
    		
    	});
	}
}
