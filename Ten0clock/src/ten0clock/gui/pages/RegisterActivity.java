package ten0clock.gui.pages;

import android.app.Activity;
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
		
		OnClickListener onClickListener = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	Intent intent = new Intent(context, NavTemplateActivity.class);
		    	startActivity(intent);
		    }
		};

		Button button = (Button) findViewById(R.id.registerSubmit);
		button.setOnClickListener(onClickListener);
	}
}
