package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.backend.account.Venue;
import ten0clock.gui.util.OnSwipeTouchListener;
import ten0clock.gui.util.VenueListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class VenuesViewFragment extends Fragment {
	public enum VenueColumn{
		VENUENAME,
		VENUELOCATION
	}
	
	private View venuesViewView;
	private ArrayList<HashMap<VenueColumn,String>> venueList = new ArrayList<HashMap<VenueColumn,String>>();
	private ArrayList<Venue> venues = new ArrayList<Venue>();
	
	public VenuesViewFragment() {
		
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
    			Fragment venuesSearchFragment = new VenuesSearchFragment();
    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
    		}
    	});
    	
    	HashMap<VenueColumn, String> vc1 = new HashMap<VenueColumn, String>();
    	vc1.put(VenueColumn.VENUENAME, "Paddy's Irish Pub");
    	vc1.put(VenueColumn.VENUELOCATION, "300 Race Street");
    	HashMap<VenueColumn, String> vc2 = new HashMap<VenueColumn, String>();
    	vc2.put(VenueColumn.VENUENAME, "Fox and the Hound");
    	vc2.put(VenueColumn.VENUELOCATION, "1200 Spruce Street");
    	venueList.add(vc1);
    	venueList.add(vc2);
    	ListView lView = (ListView) venuesViewView.findViewById(R.id.venueListing);
    	lView.setAdapter(new VenueListAdapter(this, venueList));
    	
	}
}
