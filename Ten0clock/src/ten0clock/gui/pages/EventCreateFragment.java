package ten0clock.gui.pages;

import java.util.ArrayList;
import java.util.Date;

import ten0clock.backend.account.Event;
import ten0clock.backend.account.User;
import ten0clock.backend.account.Venue;
import ten0clock.backend.account.Venue.Atmosphere;
import ten0clock.backend.account.Venue.Volume;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

/* 
 * Events Page
 * -----------
 * Page used to create and view events.
 */
public class EventCreateFragment extends Fragment{
	private View eventsView;
	private User user;
	public EventCreateFragment(User u) {
		user = u;
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
		    	EditText ecName = (EditText) eventsView.findViewById(R.id.ecName);
		    	String name = ecName.getText().toString();
		    	Spinner ecLocation = (Spinner) eventsView.findViewById(R.id.ecLocation);
		    	int locVal = ecLocation.getSelectedItemPosition();
		    	Venue tmpV = user.Venues().get(locVal);
		    	EditText ecCategory = (EditText) eventsView.findViewById(R.id.ecCategory);
		    	String category = ecCategory.getText().toString();
		    	Button ecDatePicker = (Button) eventsView.findViewById(R.id.ecTimeChooser);
		    	String date = (String) ecDatePicker.getText();
		    	String datea[] = date.split("-");
		    	int year = Integer.parseInt(datea[0]);
		    	int month = Integer.parseInt(datea[1]);
		    	int day = Integer.parseInt(datea[2]);
		    	Date d = new Date(year, month, day);
		    	Event tmpE = new Event(name,tmpV,category,d);
		    	tmpV.Events().add(tmpE);
		    	user.Events().add(tmpE);
		    	resetInformation();
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
		
		ArrayList<String> locStrs = new ArrayList<String>();
		
		for (Venue v : user.Venues()) {
			locStrs.add(v.Name());
		}
		
		ArrayAdapter<String> a1 = new ArrayAdapter<String>(
			    eventsView.getContext(), android.R.layout.simple_spinner_item, locStrs);
		Spinner vSpinner = (Spinner) eventsView.findViewById(R.id.ecLocation);
		vSpinner.setAdapter(a1);
		
    }
    
    // When an event is created we want to clear the fields to allow the user to schedule another event
    public void resetInformation() {
    	EditText ecName = (EditText) eventsView.findViewById(R.id.ecName);
    	ecName.setText("");
    	Spinner ecLocation = (Spinner) eventsView.findViewById(R.id.ecLocation);
    	ecLocation.setSelection(0);
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
