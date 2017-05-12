package benevent.elsys.org.benevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DailyEventsActivity extends AppCompatActivity {

    private ListView mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_events);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://benevent-api.herokuapp.com/events";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonParser parser = new JsonParser();
                        JsonElement elem   = parser.parse( response );

                        JsonArray elemArr = elem.getAsJsonArray();

                        mEvents = (ListView) findViewById(R.id.event_list);
                        List<Event> events = new ArrayList<>();

                        Gson gson = new Gson();
                        for (JsonElement el : elemArr) {
                            Event ev = gson.fromJson(el, Event.class);
                            if(ev.getStart() != null) {
                                System.out.println(ev);
                                System.out.println(getIntent().getExtras().getString("dailyEventsExtra"));
                                System.out.println(ev.getStart().split("T")[0]);
                                if (ev.getStart().split("T")[0].equals(getIntent().getExtras().getString("dailyEventsExtra"))) {
                                    events.add(new Event(ev.getName(), ev.getStart().substring(11, 19), ev.getEnd().substring(11, 19)));
                                }
                            }
                        }
                        EventAdapter adapter = new EventAdapter(DailyEventsActivity.this, events);
                        mEvents.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
