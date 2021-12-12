package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;

public class CreateAccountActivity extends AppCompatActivity {

    CreateAccountSvcSQLiteImpl dBMain;
    RadioButton stuButton, facButton;
    Button subButton, canButton;

    EditText stuFacID, accountpasswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(CreateAccountActivity.this);
        findid();
        insertData();
        cancelBack();
    }

    //for back arrow functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //cancel button functionality
    private void cancelBack() {
        canButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //insert data into database for created account if the fields are filled correctly
    private void insertData() {
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student = stuButton.getText().toString();
                String faculty = facButton.getText().toString();
                String username = stuFacID.getText().toString();
                String password = accountpasswrd.getText().toString();

                if ((!stuButton.isChecked() && !facButton.isChecked()) || username.equals("") ||
                    password.equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter all the " +
                            "fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (stuButton.isChecked()) {
                        Boolean checkUser = dBMain.checkUsername(username);
                        if (checkUser == false) {
                            Boolean insert = dBMain.insert(student, username, password);
                            if (insert == true) {
                                Toast.makeText(CreateAccountActivity.this, "Account " +
                                        "Created", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(CreateAccountActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CreateAccountActivity.this,
                                        "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(CreateAccountActivity.this, "User already " +
                                    "exists. Please log in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (facButton.isChecked()) {
                        Boolean checkUser = dBMain.checkUsername(username);
                        if (checkUser == false) {
                            Boolean insert = dBMain.insert(faculty, username, password);
                            if (insert == true) {
                                Toast.makeText(CreateAccountActivity.this, "Account " +
                                        "Created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateAccountActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CreateAccountActivity.this,
                                        "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(CreateAccountActivity.this, "User already " +
                                    "exists. Please log in", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void findid() {
        stuButton = (RadioButton)findViewById(R.id.studentRadioButton);
        facButton = (RadioButton)findViewById(R.id.facultyRadioButton);
        subButton = (Button)findViewById(R.id.submitbutton);
        stuFacID = (EditText)findViewById(R.id.studentFacultyID);
        accountpasswrd = (EditText)findViewById(R.id.accountPassword);
        canButton = (Button)findViewById(R.id.cancelbutton);
    }
}