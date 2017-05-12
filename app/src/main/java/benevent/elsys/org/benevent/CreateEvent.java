package benevent.elsys.org.benevent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {



    private int mYear, mMonth, mDay, mHour, mMin;
    private Double lonG, latG;

    private Button startDate, endDate, startTime, endTime;
    private TextView startDatetView, endDateView, startTimeView, endTimeView;
    private TextView nameView, descView;

    private CreateEventRequest mCreateEventRequest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        startDate = (Button) findViewById(R.id.pick_start_date);
        startDatetView = (TextView) findViewById(R.id.start_date);
        endDate = (Button) findViewById(R.id.pick_end_date);
        endDateView = (TextView) findViewById(R.id.end_date);

        startTime = (Button) findViewById(R.id.pick_start_time);
        startTimeView = (TextView) findViewById(R.id.start_time);
        endTime = (Button) findViewById(R.id.pick_end_time);
        endTimeView = (TextView) findViewById(R.id.end_time);

        nameView = (TextView) findViewById(R.id.input_name);
        descView = (TextView) findViewById(R.id.input_desc);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                startDate.setText(sdf.format(myCalendar.getTime()));
            }


        };

        final DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                endDate.setText(sdf.format(myCalendar.getTime()));
            }


        };

        startDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(CreateEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear, mMonth, mDay);

                                startDatetView.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(CreateEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear, mMonth, mDay);

                                endDateView.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startTimeView.setText(hourOfDay + ":" + ((minute < 10)? "0" + minute : minute));
                            }
                        }, mHour, mMin, false);
                timePickerDialog.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                endTimeView.setText(hourOfDay + ":" + ((minute < 10)? "0" + minute : minute));
                            }
                        }, mHour, mMin, false);
                timePickerDialog.show();
            }
        });


    }



    public void makeRequest(View view) {

        mCreateEventRequest = new CreateEventRequest("1", startTimeView.getText().toString(),
                                                     endTimeView.getText().toString(),
                                                     nameView.getText().toString(),
                                                     descView.getText().toString(),
                                                     latG.toString(),
                                                     latG.toString());
        mCreateEventRequest.execute((String []) null);
    }


    public class CreateEventRequest extends AsyncTask<String, String, String> {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        private final String mUser;
        private final String mStart;
        private final String mEnd;
        private final String mName;
        private final String mDesc;
        private final String mLat;
        private final String mLong;

        CreateEventRequest(String user, String start, String end, String name, String desc, String lat, String lng) {
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
            Toast.makeText(CreateEvent.this, "Your event was successfully created!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(CreateEvent.this, "An error occured while querying!", Toast.LENGTH_LONG).show();
        }
    }


    public void selectLocation(View view) {
        Intent intent = new Intent(this, SelectLocation.class);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView location = (TextView) findViewById(R.id.select_location);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String locationStr = data.getStringExtra("point");
                String[] longLat = locationStr.split(" ");
                Toast.makeText(CreateEvent.this, locationStr, Toast.LENGTH_SHORT).show();
                location.setText(locationStr);
                lonG = Double.parseDouble(longLat[0]);
                latG = Double.parseDouble(longLat[1]);
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}