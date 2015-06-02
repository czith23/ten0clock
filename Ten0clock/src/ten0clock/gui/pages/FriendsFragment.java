package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.backend.account.User;
import ten0clock.gui.util.EventListAdapter;
import ten0clock.gui.util.FriendListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* 
 * Friends Page
 * ------------
 * Page used to view which friends are online, and search
 * or add new friends
 */
public class FriendsFragment extends Fragment {
	public enum FriendColumn {
		USERID
	}
	
	private View friendsView;
	private User user;
	private ArrayList<HashMap<FriendColumn,String>> friends = new ArrayList<HashMap<FriendColumn,String>>();
	private ArrayList<User> friendArr = new ArrayList<User>();
	private ArrayList<String> tArray = new ArrayList<String>();
	
	public FriendsFragment(User u) {
		user = u;
		friendArr = u.Friends();
		for (User tu : user.Friends()) {
			HashMap<FriendColumn, String> hMap = new HashMap<FriendColumn,String>();
			hMap.put(FriendColumn.USERID, tu.UserID());
			friends.add(hMap);
		}
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsView = (RelativeLayout) rootView.findViewById(R.id.friendsView);
        initializeComponents();
        return rootView;
    }
    
    public void initializeComponents() {
    	ListView lView = (ListView) friendsView.findViewById(R.id.fFriendList);
//    	lView.setAdapter(new FriendListAdapter(this, friends));
    	    	
        lView.setAdapter(new ArrayAdapter<String>(friendsView.getContext(),android.R.layout.simple_list_item_1,tArray));
    	
        
    	Button addFriend = (Button) friendsView.findViewById(R.id.friendAddButton);
    	addFriend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user.fakeAdd();
				addStacey();
				Log.i("", "YSDFASDFASDFA");
				recalcFriends();
			}
    		
    	});
    	
    	lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				FragmentManager fManager = getFragmentManager();
    			Fragment eventCreateFragment = new ProfileFragment(user.Friends().get(0));
    			fManager.beginTransaction().replace(R.id.mainContent, eventCreateFragment).commit();
			}

    		
    	});
    	
    	user.fakeAdd();
    	tArray.add("staceFace");
    }

    public void addStacey() {
    	
    }
    
    public void recalcFriends() {
    	for (User f : user.Friends()) {
    		HashMap<FriendColumn, String> hMap = new HashMap<FriendColumn, String>();
    		hMap.put(FriendColumn.USERID, f.UserID());
    		friends.add(hMap);
    	}
    }
}
