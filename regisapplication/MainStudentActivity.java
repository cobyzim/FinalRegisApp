package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;

public class MainStudentActivity extends AppCompatActivity {

    Button classSchedule, campusMap, schoolEvents, buildingHours, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findid();
        goToCampusMap();
        goToEvents();
        goToBuildingHours();
        goToClassSchedule();
        goBack();

    }

    private void goBack() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStudentActivity.this,
                        StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToClassSchedule() {
        classSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStudentActivity.this,
                        StudentClassScheduleMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToBuildingHours() {
        buildingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStudentActivity.this,
                        BuildingHoursActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToEvents() {
        schoolEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStudentActivity.this,
                        EventsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToCampusMap() {
        campusMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStudentActivity.this,
                        CampusMapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        classSchedule = (Button) findViewById(R.id.classSchedButtonId);
        campusMap = (Button) findViewById(R.id.mapButtonId);
        schoolEvents = (Button) findViewById(R.id.schoolEventsButtonId);
        buildingHours = (Button) findViewById(R.id.buildingHoursButtonId);
        backButton = (Button) findViewById(R.id.stuMainBackButtonId);
    }
}