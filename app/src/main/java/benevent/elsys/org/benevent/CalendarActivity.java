package benevent.elsys.org.benevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), year + " " + month + " "+dayOfMonth, Toast.LENGTH_LONG).show();
                Intent dailyEvents = new Intent(CalendarActivity.this, DailyEventsActivity.class);
                String dailyEventsExtra = year + "-" + ((month < 10)? "0" + (month + 1) : (month + 1)) + "-" + ((dayOfMonth < 10)? "0" + dayOfMonth : dayOfMonth);
                dailyEvents.putExtra("dailyEventsExtra", dailyEventsExtra);
                startActivity(dailyEvents);
            }
        });
    }
}
