package benevent.elsys.org.benevent;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, List<Event> events) {
        super(context, 0, events);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_event_list_item, parent, false);
        }
        Event currentEvent;
        currentEvent = getItem(position);

        TextView event_name = (TextView) listItemView.findViewById(R.id.event_name);
        event_name.setText(currentEvent.getName());

        TextView event_start = (TextView) listItemView.findViewById(R.id.event_start);

        event_start.setText(currentEvent.getStart());

        TextView event_end = (TextView) listItemView.findViewById(R.id.event_end);

        event_end.setText(currentEvent.getEnd());

        return listItemView;


    }
}
