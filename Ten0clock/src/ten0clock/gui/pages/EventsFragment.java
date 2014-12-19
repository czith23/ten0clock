package ten0clock.gui.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* This fragment will represent the Events tab */
public class EventsFragment extends Fragment {

    public EventsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_layout, container, false);
        String title = "Events";

        getActivity().setTitle(title);
        return rootView;
    }
}