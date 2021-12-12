package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;

public class AddClassActivity extends AppCompatActivity {

    EditText studentId, classTitleName, meetingTime, location, term, startDate;
    Button addButton, cancelButton;
    CreateAccountSvcSQLiteImpl dBMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(AddClassActivity.this);
        findid();
        addNewClass();
        cancelGoBack();
    }

    private void cancelGoBack() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddClassActivity.this,
                        FacultyEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addNewClass() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentid = studentId.getText().toString();
                String classtitlename = classTitleName.getText().toString();
                String meettime = meetingTime.getText().toString();
                String loc = location.getText().toString();
                String classterm = term.getText().toString();
                String date = startDate.getText().toString();

                if (studentid.equals("") || classtitlename.equals("") ||  meettime.equals("") ||
                        loc.equals("") || classterm.equals("") || date.equals("")) {

                    Toast.makeText(AddClassActivity.this, "Please enter all of " +
                            "the fields (If no meeting time for online class, put NONE)",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkClassExists = dBMain.checkClass(classtitlename);
                    if (checkClassExists) {
                        Toast.makeText(AddClassActivity.this, "The provided class is " +
                                        "already in the student's schedule.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean addClass = dBMain.insertSchedule(studentid, classtitlename, meettime,
                                loc, classterm, date);
                        if (addClass) {
                            Toast.makeText(AddClassActivity.this, "Class " +
                                    "Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddClassActivity.this,
                                    FacultyEditActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    private void findid() {
        studentId = (EditText) findViewById(R.id.addStuIDid);
        classTitleName = (EditText) findViewById(R.id.addClassNameId);
        meetingTime = (EditText) findViewById(R.id.addMeetTimeId);
        location = (EditText) findViewById(R.id.addLocationId);
        term = (EditText) findViewById(R.id.addTermId);
        startDate = (EditText) findViewById(R.id.addStartDateId);
        addButton = (Button) findViewById(R.id.addButtonId);
        cancelButton = (Button) findViewById(R.id.cancelButId);
    }
}