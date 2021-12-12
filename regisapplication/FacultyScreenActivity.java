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

public class FacultyScreenActivity extends AppCompatActivity {

    EditText studentId;
    Button subButtn;
    CreateAccountSvcSQLiteImpl dBMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_screen);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(FacultyScreenActivity.this);
        findid();
        movetostudent();
    }

    private void movetostudent() {
        subButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student = studentId.getText().toString();

                if (student.equals("")) {
                    Toast.makeText(FacultyScreenActivity.this, "Please enter Student " +
                            "ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkStudent = dBMain.checkUsername(student);
                    if (checkStudent) {
                        Toast.makeText(FacultyScreenActivity.this, "Student Found",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(FacultyScreenActivity.this,
                                FacultyEditActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(FacultyScreenActivity.this, "Student Not Found. " +
                                        "Please have them make an account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void findid() {
        studentId = (EditText) findViewById(R.id.enterStudentId);
        subButtn = (Button) findViewById(R.id.facSubButton);
    }

}