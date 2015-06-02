package ten0clock.gui.pages;

import java.util.Random;

import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.gui.util.DiscussArrayAdapter;
import ten0clock.gui.util.OneComment;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BubbleChatFragment extends Fragment {
	private DiscussArrayAdapter adapter;
	private ListView lv;
	private EditText editText1;
	private RelativeLayout bubbleChatView;
	private User user;
	private Venue venue;
	private String title = "No Recent Chat";
	
	public BubbleChatFragment(User u) {
		user = u;
	}
	
	public BubbleChatFragment(Venue v, User u) {
		venue = v;
		user = u;
		title = v.Name();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bubble_chat, container, false);
		bubbleChatView = (RelativeLayout) rootView.findViewById(R.id.chatView);

		lv = (ListView) bubbleChatView.findViewById(R.id.chatListView);

		adapter = new DiscussArrayAdapter(bubbleChatView.getContext(), R.layout.listitem_discuss);
		TextView titleView = (TextView) bubbleChatView.findViewById(R.id.chatListingLabel);
		titleView.setText(title);
		lv.setAdapter(adapter);

		editText1 = (EditText) bubbleChatView.findViewById(R.id.chatEditText);
		editText1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					adapter.add(new OneComment(false, editText1.getText().toString()));
					editText1.setText("");
					return true;
				}
				return false;
			}
		});
		
		Button chatSendButton = (Button) bubbleChatView.findViewById(R.id.chatSendMessageButton);
		chatSendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.add(new OneComment(false, editText1.getText().toString()));
				editText1.setText("");
			}
			
		});
		
		return rootView;
	}

	private void addItem(String chatMessage) {
		adapter.add(new OneComment(true, chatMessage));
	}
}