package ten0clock.gui.pages;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/* 
 * Events Page
 * -----------
 * Page used to create and view events.
 */
public class EventsFragment extends Fragment{
	private View eventsView;
	public EventsFragment() {
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Pull current views into fragment context to work with them later
        View rootView = inflater.inflate(R.layout.fragment_event_create, container, false);
        eventsView = (RelativeLayout) rootView.findViewById(R.id.eventsCreateView);
        initializeComponents();
        return rootView;
    }
    
	/*
	 * Create and set listeners
	 */
    public void initializeComponents() {
		Button dateButton = (Button) eventsView.findViewById(R.id.ecTimeChooser);
		Button submitButton = (Button) eventsView.findViewById(R.id.ecCreateButton);
		
		OnClickListener createEventListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	Toast.makeText(eventsView.getContext(), "Event Created", Toast.LENGTH_LONG).show();
		    	
		    }
		};
		
		OnClickListener dateClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button dateButton = (Button) eventsView.findViewById(R.id.ecTimeChooser);
				DialogFragment dPicker = new DatePickerFragment(dateButton);
				dPicker.show(getFragmentManager(), "datePicker");
			}
		};
		
		// Assign date picker listener
		dateButton.setOnClickListener(dateClickListener);
		
		// Assign Create Event listener
		submitButton.setOnClickListener(createEventListener);
    }
    
    // When an event is created we want to clear the fields to allow the user to schedule another event
    public void resetInformation() {
    	EditText ecName = (EditText) eventsView.findViewById(R.id.ecName);
    	ecName.setText("");
    	EditText ecLocation = (EditText) eventsView.findViewById(R.id.ecLocation);
    	ecLocation.setText("");
    	EditText ecType = (EditText) eventsView.findViewById(R.id.ecType);
    	ecType.setText("");
    	EditText ecCategory = (EditText) eventsView.findViewById(R.id.ecCategory);
    	ecCategory.setText("");
    	Button ecDatePicker = (Button) eventsView.findViewById(R.id.ecTimeChooser);
    	ecDatePicker.setText("");
    	EditText ecCost = (EditText) eventsView.findViewById(R.id.ecCost);
    	ecCost.setText("");
    }
}
