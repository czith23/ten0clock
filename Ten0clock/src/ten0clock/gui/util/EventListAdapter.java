package ten0clock.gui.util;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.gui.pages.EventViewFragment.EventColumn;
import ten0clock.gui.pages.R;
import ten0clock.gui.pages.VenuesViewFragment.VenueColumn;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter {
	public ArrayList<HashMap<EventColumn, String>> list;
	Fragment fragment;
	TextView eventName;
	TextView eventDate;
	TextView location;

	public EventListAdapter (Fragment _fragment, ArrayList<HashMap<EventColumn, String>> list){
		super();
		fragment = _fragment;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
 
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		
		
		LayoutInflater inflater=fragment.getActivity().getLayoutInflater();
		
		if(convertView == null){
			
			convertView=inflater.inflate(R.layout.event_columns, null);
			
			eventName =(TextView) convertView.findViewById(R.id.eventNameColumn);
			eventDate = (TextView) convertView.findViewById(R.id.eventDateColumn);
			location =(TextView) convertView.findViewById(R.id.eventBarColumn);
		}
		
		HashMap<EventColumn, String> map= list.get(position);
		eventName.setText(map.get(EventColumn.NAME));
		eventDate.setText(map.get(EventColumn.DATE));
		location.setText(map.get(EventColumn.LOCATION));
		
		return convertView;
	}
}
