package ten0clock.gui.pages;

import java.util.ArrayList;

import ten0clock.backend.account.Event;
import ten0clock.backend.account.Poll;
import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

// TODO: implement polls
public class PollCreateFragment extends Fragment {
	private View pollsCreateView;
	private User user;
	
	public PollCreateFragment(User u) {
		user = u;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_poll_create, container, false);
        pollsCreateView = (RelativeLayout) rootView.findViewById(R.id.pollCreateView);
        initializeComponents();
        return rootView;
    }
    
	/*
	 * Create and set listeners
	 */
    public void initializeComponents() {
		Button createButton = (Button) pollsCreateView.findViewById(R.id.pollCreateButton);
		Button clearButton = (Button) pollsCreateView.findViewById(R.id.clearPollButton);
		ArrayList<String> vs = new ArrayList<String>();
		ArrayList<String> es = new ArrayList<String>();
		
		vs.add("None");
		for (Venue tv : user.Venues()) {
			vs.add(tv.Name());
		}
		es.add("None");
		for (Event te : user.Events()) {
			es.add(te.Name());
		}
		ArrayAdapter<String> a1 = new ArrayAdapter<String>(
			    pollsCreateView.getContext(), android.R.layout.simple_spinner_item, vs);
		ArrayAdapter<String> a2 = new ArrayAdapter<String>(
			    pollsCreateView.getContext(), android.R.layout.simple_spinner_item, es);
		a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner vSpin = (Spinner) pollsCreateView.findViewById(R.id.pollBindVenue);
		Spinner eSpin = (Spinner) pollsCreateView.findViewById(R.id.pollBindEvent);
		vSpin.setAdapter(a1);
		eSpin.setAdapter(a2);
		
		
		OnClickListener createPollListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	Toast.makeText(pollsCreateView.getContext(), "Poll Created", Toast.LENGTH_LONG).show();
		    	EditText pollQuestion = (EditText) pollsCreateView.findViewById(R.id.pollQuestion);
		    	Spinner eventBind = (Spinner) pollsCreateView.findViewById(R.id.pollBindEvent);
		    	Spinner venueBind = (Spinner) pollsCreateView.findViewById(R.id.pollBindVenue);
		    	
		    	String pollQuestionStr = pollQuestion.getText().toString();
		    	Poll p = new Poll(pollQuestionStr);
		    	
		    	if (eventBind.getSelectedItemPosition() != 0) {
		    		p.setEvent(user.Events().get(eventBind.getSelectedItemPosition()-1));
		    		user.Events().get(eventBind.getSelectedItemPosition()-1).addPoll(p);
		    	}
		    	if (venueBind.getSelectedItemPosition() != 0) {
		    		p.setVenue(user.Venues().get(venueBind.getSelectedItemPosition()-1));
		    		user.Venues().get(venueBind.getSelectedItemPosition()-1).addPoll(p);
		    	}
		    	
		    	resetInformation();
		    }
		};
		
		OnClickListener clearClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button dateButton = (Button) pollsCreateView.findViewById(R.id.ecTimeChooser);
				DialogFragment dPicker = new DatePickerFragment(dateButton);
				dPicker.show(getFragmentManager(), "datePicker");
			}
		};
		
		// Assign date picker listener
		createButton.setOnClickListener(createPollListener);
		
		// Assign Create Event listener
		clearButton.setOnClickListener(clearClickListener);
    }
    
    // When an event is created we want to clear the fields to allow the user to schedule another event
    public void resetInformation() {
    	EditText pollQuestion = (EditText) pollsCreateView.findViewById(R.id.pollQuestion);
    	pollQuestion.setText("");
    }
}
