package ten0clock.gui.pages;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Registration Page
 * -----------------
 * This is the page that will be displayed if a customer clicks
 *  on the “Register” button of the Landing Page. Here, browsers
 *   can become members by filling out the form and clicking
 *    “Submit” to access Ten0Clock’s member features.
 */
public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		this.setTitle("Register");
		
		initializeComponents();
	}
	
	protected void initializeComponents() {
		final Context context = this;
		
		// Retrieve buttons from the view
		Button dateButton = (Button) findViewById(R.id.birthChooser);
		Button submitButton = (Button) findViewById(R.id.registerSubmit);
		
		
		// Define listeners
		OnClickListener submitListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	// Create a new activity and launch it as the new page
		    	Intent intent = new Intent(context, NavigationActivity.class);
		    	startActivity(intent);
		    }
		};
		
		OnClickListener dateClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Display a "Date chooser" pop-up window
				Button dateButton = (Button) findViewById(R.id.birthChooser);
				DialogFragment dPicker = new DatePickerFragment(dateButton);
				dPicker.show(getFragmentManager(), "datePicker");
			}
		};
		
		// Assign date picker listener
		dateButton.setOnClickListener(dateClickListener);
		
		// Assign submit listener
		submitButton.setOnClickListener(submitListener);
	}
}
