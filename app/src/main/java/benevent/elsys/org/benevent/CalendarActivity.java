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
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(), year + " " + month + " "+dayOfMonth, Toast.LENGTH_LONG).show();
//                Intent dailyEvents = new Intent(CalendarActivity.this, DailyEventsActivity.class);
//                startActivity(dailyEvents);
//            }
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), year + "-" + (month + 1) + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
                Intent dailyEvents = new Intent(CalendarActivity.this, DailyEventsActivity.class);
                System.out.println("Month : " + month);
                String monthStr = "";
                if (month + 1 < 10) {
                    monthStr = "0" + (month + 1);
                } else {
                    monthStr = "" + (month + 1);
                }
                System.out.println(monthStr);
                String dailyEventsExtra = year + "-" + monthStr + "-" + ((dayOfMonth < 10)? "0" + dayOfMonth : dayOfMonth);
                dailyEvents.putExtra("dailyEventsExtra", dailyEventsExtra);
                startActivity(dailyEvents);
            }
        });
    }
}
