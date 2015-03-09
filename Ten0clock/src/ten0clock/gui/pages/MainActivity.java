package ten0clock.gui.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*
 * Landing Page
 * ------------
 * This is the page that will be displayed once Ten0Clock is downloaded, 
 * installed, and opened for the first time. Browsers have the option to 
 * register with Ten0Clock or just browse venues without becoming a member. 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeComponents();
	}
	
	/*
	 * Fill in button listeners, additional details
	 */
	protected void initializeComponents() {
		final Context context = this;
		
		OnClickListener onClickListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	// Create Registration activity and launch it
		    	Intent intent = new Intent(context, RegisterActivity.class);
		    	startActivity(intent);
		    }
		};

		Button button = (Button) findViewById(R.id.registerButton);
		button.setOnClickListener(onClickListener);
	}
}
