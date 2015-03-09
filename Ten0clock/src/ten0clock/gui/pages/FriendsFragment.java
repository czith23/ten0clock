package ten0clock.gui.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* 
 * Friends Page
 * ------------
 * Page used to view which friends are online, and search
 * or add new friends
 */
public class FriendsFragment extends Fragment {
	private View friendsView;
	public FriendsFragment() {
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsView = (RelativeLayout) rootView.findViewById(R.id.friendsView);
        initializeComponents();
        return rootView;
    }
    
    public void initializeComponents() {

    }
}
