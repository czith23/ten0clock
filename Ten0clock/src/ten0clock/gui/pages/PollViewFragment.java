package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.backend.account.Poll;
import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.gui.pages.VenuesViewFragment.VenueColumn;
import ten0clock.gui.util.OnSwipeTouchListener;
import ten0clock.gui.util.VenueListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PollViewFragment extends Fragment {
	private View pollsViewView;
	private ArrayList<HashMap<VenueColumn,String>> pollList = new ArrayList<HashMap<VenueColumn,String>>();
	private ArrayList<Poll> polls = new ArrayList<Poll>();
	private String listingName;
	private int currentPosition;
	private User user;
	
	public PollViewFragment(User u) {
		listingName = "My Polls";
		user = u;
	}
	
	public PollViewFragment(String name) {
		listingName = name;
	}
	
	public PollViewFragment(String name, ArrayList<Poll> ps, User u) {
		listingName = name;
		polls = ps;
		user = u;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_poll_view, container, false);
        pollsViewView = (RelativeLayout) rootView.findViewById(R.id.pollListingView);

        initializeComponents();
        return rootView;
    }
	
	public void initializeComponents() {
//    	pollsViewView.setOnTouchListener(new OnSwipeTouchListener(pollsViewView.getContext()) {
//    		@Override
//    		public void onSwipeRight() {
//    			FragmentManager fManager = getFragmentManager();
//    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
//    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
//    		}
//    	});
//    	pollsViewView.findViewById(R.id.venueListing).setOnTouchListener(new OnSwipeTouchListener(pollsViewView.getContext()) {
//    		@Override
//    		public void onSwipeRight() {
//    			FragmentManager fManager = getFragmentManager();
//    			Fragment venuesSearchFragment = new VenuesSearchFragment(user);
//    			fManager.beginTransaction().replace(R.id.mainContent, venuesSearchFragment).commit();
//    		}
//    	});
    	
    	TextView vTitle = (TextView) pollsViewView.findViewById(R.id.pollListingLabel);
    	vTitle.setText(listingName);
    	
    	ListView lView = (ListView) pollsViewView.findViewById(R.id.pollListing);
    	
    	ArrayList<String> pollStrs = new ArrayList<String>();
    	if (polls != null) {
    	
    	for (Poll p : polls) {
    		pollStrs.add(p.Question());
    	}
    	ArrayAdapter<String> pollAdapter = new ArrayAdapter<String>(
                pollsViewView.getContext(), 
                android.R.layout.simple_list_item_1,
                pollStrs);
    	
    	lView.setAdapter(pollAdapter);	
    	
    	lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
				currentPosition = position;
				FragmentManager fManager = getFragmentManager();
    			Fragment venuesDetailFragment = new PollDetailFragment();
    			fManager.beginTransaction().replace(R.id.mainContent, venuesDetailFragment).commit();
			}
    		
    	});
    	}
	}
}
