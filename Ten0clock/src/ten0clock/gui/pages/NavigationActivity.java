package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.Date;

import ten0clock.backend.account.Event;
import ten0clock.backend.account.Venue;
import ten0clock.backend.account.Venue.Atmosphere;
import ten0clock.backend.account.Venue.Volume;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/* 
 * NavigationActivity
 * ------------------
 * This Activity will be used to implement the navigational drawer
 * interface. Every page of the mobile application will simply
 * replace a specific fragment that exists inside of this activity.
 * In this way, the navigational drawer will always exist on the left
 * margin of the screen, and it can be swiped on and off of the screen
 * by users.
 */
@SuppressWarnings("deprecation")
public class NavigationActivity extends Activity implements OnItemClickListener{
	private ListView listView;
	private DrawerLayout drawerLayout;
	private String[] tabs;
	
    public NavigationActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);
		
		// Initialize the layout
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		
		// Initialize the drawer view
		listView = (ListView) findViewById(R.id.drawers);
		
		// Grab the predefined values for drawer options
		tabs = getResources().getStringArray(R.array.tabs);
		
		// This converts the array above into a usable array for the DrawerLayout
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tabs));
		
		initializeComponents();
    }
    
    // Initialize the first screen to the profile
    public void initializeComponents() {
    	listView.setOnItemClickListener(this);
    
    	Fragment currentFragment = new ProfileFragment();
		if (currentFragment != null) {
			FragmentManager fManager = getFragmentManager();
			fManager.beginTransaction().replace(R.id.mainContent, currentFragment).commit();
			setTitle("Profile");
			selectItem(0);

			//getActionBar().setTitle(tabs[0]);
			drawerLayout.closeDrawer(listView);
		}
		
		// Greet to the new user
		// TODO: Dynamically generate user name from account
		Toast.makeText(this, "Welcome to Ten0Clock Chuck!", Toast.LENGTH_SHORT).show();;
    }

    /*
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     * Everytime the user selections one of the drawers we need to determine which
     * drawer was selected and replace the currently displayed fragment with the correct
     * new fragment. This listener takes care of this for us.
     */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String tabSelection = tabs[position];
		Fragment currentFragment;
		// Log.i("",tabSelection);
		
		// Use a different fragment depending on the selection
		if ( tabSelection.equals("Events")) {
			Venue v1 = new Venue("New Deck Tavern", "3408 Sansom St.", Atmosphere.CASUAL, Volume.LIGHT);
			Venue v2 = new Venue("Sampan","124 S. 13th",Atmosphere.FORMAL, Volume.BUSY);
			Venue v3 = new Venue("Shake Shack","3200 Chestnut St.",Atmosphere.CASUAL, Volume.PACKED);
			Venue v4 = new Venue("North Bowl","909 North 2nd St.",Atmosphere.CASUAL, Volume.BUSY);
			
			Event e1 = new Event("Bryan's Birthday", v4, "Birthday", new Date(2015,6,7));
			Event e2 = new Event("Dinner with Mark", v2, "Work", new Date(2015,7,14));
			Event e3 = new Event("Quiz-O", v1, "Friends", new Date(2015,6,22));
			Event e4 = new Event("Dad's Visiting, lunch", v3, "Family", new Date(2015,8,1));
			
			ArrayList<Event> es = new ArrayList<Event>();
			es.add(e1);
			es.add(e2);
			es.add(e3);
			es.add(e4);
			
			currentFragment = new EventViewFragment("My Events", es);
		}
		else if ( tabSelection.equals("Chat")) {
			currentFragment = new ChatFragment();
		}
		else if ( tabSelection.equals("Venues")) {
			currentFragment = new VenuesSearchFragment();
		}
		else if ( tabSelection.equals("Polls")) {
			currentFragment = new PollCreateFragment();
		}
		else if ( tabSelection.equals("Reviews")) {
			currentFragment = new ReviewsFragment();
		}
		else if ( tabSelection.equals("Settings")) {
			currentFragment = new SettingsFragment();
		}
		else if (tabSelection.equals("Friends")) {
			currentFragment = new FriendsFragment();
		}
		else {
			currentFragment = new ProfileFragment();
		}
		
		if (currentFragment != null) {
			FragmentManager fManager = getFragmentManager();
			fManager.beginTransaction().replace(R.id.mainContent, currentFragment).commit();
			setTitle(tabSelection);
			selectItem(position);
			//getActionBar().setTitle(tabs[position]);
			drawerLayout.closeDrawer(listView);
		}
	}
	
	// This keeps tracks of which fragment we are on at all times
	public void selectItem(int position) {
		listView.setItemChecked(position, true);
	}
}