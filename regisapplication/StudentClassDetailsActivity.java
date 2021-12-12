package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import zimmerman.regis.regisapplication.domain.Classes;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;
import zimmerman.regis.regisapplication.service.ICreateAccountSvc;

public class StudentClassDetailsActivity extends AppCompatActivity {

    private TextView studentId, classNameTitle, meetingInfo, location, term, startDate;
    private Classes classes;
    private ICreateAccountSvc classesSvc;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_schedule);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        classesSvc = CreateAccountSvcSQLiteImpl.getInstance(getApplicationContext());
        findid();
        classes = (Classes) getIntent().getSerializableExtra("schedule");
        if (classes != null) {
            studentId.setText(classes.getStudentIdOrFacultyName());
            classNameTitle.setText(classes.getCourseNameTitle());
            meetingInfo.setText(classes.getMeetingInfo());
            location.setText(classes.getLocation());
            term.setText(classes.getTerm());
            startDate.setText(classes.getStartDate());
        }

        goBack();
    }

    private void goBack() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentClassDetailsActivity.this,
                        StudentClassScheduleMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        studentId = (TextView) findViewById(R.id.schedStuIDid);
        classNameTitle = (TextView) findViewById(R.id.schedClassNameId);
        meetingInfo = (TextView) findViewById(R.id.schedMeetTimeId);
        location = (TextView) findViewById(R.id.schedLocationId);
        term = (TextView) findViewById(R.id.schedTermId);
        startDate = (TextView) findViewById(R.id.schedStartDateId);
        backButton = (Button) findViewById(R.id.backButId);
    }
}