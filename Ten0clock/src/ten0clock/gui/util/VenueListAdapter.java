package ten0clock.gui.util;

import java.util.ArrayList;
import java.util.HashMap;

import ten0clock.gui.pages.R;
import ten0clock.gui.pages.VenuesViewFragment.VenueColumn;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VenueListAdapter extends BaseAdapter {
	 
		public ArrayList<HashMap<VenueColumn, String>> list;
		Fragment fragment;
		TextView venueName;
		TextView location;

		public VenueListAdapter (Fragment _fragment,ArrayList<HashMap<VenueColumn, String>> list){
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
				
				convertView=inflater.inflate(R.layout.venue_columns, null);
				
				venueName =(TextView) convertView.findViewById(R.id.venueColumn);
				location =(TextView) convertView.findViewById(R.id.locationColumn);
			}
			
			HashMap<VenueColumn, String> map= list.get(position);
			venueName.setText(map.get(VenueColumn.VENUENAME));
			location.setText(map.get(VenueColumn.VENUELOCATION));
			
			return convertView;
		}

}
