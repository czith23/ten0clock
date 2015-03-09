package ten0clock.gui.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
	private String dateStr;
	private Button dateButton;
	
	public DatePickerFragment(Button _dateButton) {
		dateButton = _dateButton;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH); 

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(year, monthOfYear, dayOfMonth);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = sdf.format(c.getTime());
		dateButton.setText(dateStr);
	}
	
	public String getDate() {
		if (dateStr==null) {
			return "";
		}
		else {
			return dateStr;
		}
	}
	
}
