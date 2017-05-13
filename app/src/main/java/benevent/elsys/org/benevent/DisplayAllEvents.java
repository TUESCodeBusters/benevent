package benevent.elsys.org.benevent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayAllEvents extends AppCompatActivity {

    Button b;
    ScrollView scrollview;

    private ListView mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scrollview = new ScrollView(this);
        final LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        scrollview.addView(linearlayout);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://benevent-api.herokuapp.com/events";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    private CreateEventReq mCreateEventRequest = null;
                    @Override
                    public void onResponse(String response) {
                        JsonParser parser = new JsonParser();
                        final JsonElement elem = parser.parse(response);

                        final JsonArray elemArr = elem.getAsJsonArray();

                        mEvents = (ListView) findViewById(R.id.event_list);
                        List<Event> events = new ArrayList<>();

                        Gson gson = new Gson();
                        for (JsonElement el : elemArr) {
                            Event ev = gson.fromJson(el, Event.class);
                            if(ev.getStart() != null) {
                                System.out.println(ev);
                                System.out.println(ev.getStart().split("T")[0]);
                                LinearLayout lin1 = new LinearLayout(DisplayAllEvents.this);
                                linearlayout.addView(lin1);
                                b = new Button(DisplayAllEvents.this);
                                b.setText(ev.getName() + "\n" + ev.getStart().substring(0, 10) + " " + ev.getStart().substring(11, 19) +  "\n" + ev.getEnd().substring(0, 10) + " " + ev.getEnd().substring(11, 19));
                                b.setTextSize(22);
                                b.setId(ev.getId());
                                b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                lin1.addView(b);
                                b.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        Gson gson = new Gson();
                                        Toast.makeText(getApplicationContext(), "Yipee.."+ v.getId(), Toast.LENGTH_SHORT).show();
                                        for (JsonElement el: elemArr) {
                                            Event currEv = gson.fromJson(el, Event.class);
                                            if (currEv.getId() == v.getId()) {
                                                SharedPreferences sharedPrefId = DisplayAllEvents.this.getSharedPreferences(getString(R.string.current_logged_in_user_id), Context.MODE_PRIVATE);
                                                String userId = sharedPrefId.getString(getString(R.string.current_logged_in_user_id), null);
                                                mCreateEventRequest = new CreateEventReq(userId,
                                                        currEv.getStart(),
                                                        currEv.getEnd(),
                                                        currEv.getName(),
                                                        currEv.getDescription(),
                                                        currEv.getLat(),
                                                        currEv.getLng());
                                                mCreateEventRequest.execute((String []) null);

                                            }
                                        }
                                    }
                                });
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
        });
        queue.add(stringRequest);

        this.setContentView(scrollview);
    }

    public class CreateEventReq extends AsyncTask<String, String, String> {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        private final String mUser;
        private final String mStart;
        private final String mEnd;
        private final String mName;
        private final String mDesc;
        private final String mLat;
        private final String mLong;

        CreateEventReq(String user, String start, String end, String name, String desc, String lat, String lng) {
            mUser = user;
            mStart = start;
            mEnd = end;
            mName = name;
            mDesc = desc;
            mLat = lat;
            mLong = lng;
        }

        @Override
        protected String doInBackground(String... params) {
            final String url = "http://benevent-api.herokuapp.com/events";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
                    Map<String, String> temp = new HashMap<>();
                    temp.put("user_id", mUser);
                    temp.put("start", mStart);
                    temp.put("end", mEnd);
                    temp.put("name", mName);
                    temp.put("desc", mDesc);
                    temp.put("lat", mLat);
                    temp.put("lng", mLong);

                    return temp;
                }
            };

            queue.add(postRequest);

            return "";
        }


        @Override
        protected void onPostExecute(final String success) {
            Toast.makeText(DisplayAllEvents.this, "Your event was successfully created!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(DisplayAllEvents.this, "An error occured while querying!", Toast.LENGTH_LONG).show();
        }
    }
}

