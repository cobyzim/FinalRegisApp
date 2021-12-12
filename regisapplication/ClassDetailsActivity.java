package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zimmerman.regis.regisapplication.domain.Classes;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;
import zimmerman.regis.regisapplication.service.ICreateAccountSvc;

public class ClassDetailsActivity extends AppCompatActivity {

    private EditText studentId, classNameTitle, meetingInfo, location, term, startDate;
    private Classes classes;
    private ICreateAccountSvc classesSvc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);
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
    }

    private void findid() {
        studentId = (EditText) findViewById(R.id.detailsAddStuIDid);
        classNameTitle = (EditText) findViewById(R.id.detailsAddClassNameId);
        meetingInfo = (EditText) findViewById(R.id.detailsAddMeetTimeId);
        location = (EditText) findViewById(R.id.detailsAddLocationId);
        term = (EditText) findViewById(R.id.detailsAddTermId);
        startDate = (EditText) findViewById(R.id.detailsAddStartDateId);
    }

    public void save(View view) {
        classes = new Classes();
        classes.setStudentIdOrFacultyName(studentId.getText().toString());
        classes.setCourseNameTitle(classNameTitle.getText().toString());
        classes.setMeetingInfo(meetingInfo.getText().toString());
        classes.setLocation(location.getText().toString());
        classes.setTerm(term.getText().toString());
        classes.setStartDate(startDate.getText().toString());
        classesSvc.update(classes);
        Toast.makeText(this, "Class has been saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancel(View view) {
        finish();
    }
}