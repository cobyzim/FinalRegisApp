package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;

public class StudentLoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBttn, backBttn;
    CreateAccountSvcSQLiteImpl dBMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(StudentLoginActivity.this);
        findid();
        login();
        goBack();

    }

    private void goBack() {
        backBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentLoginActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(StudentLoginActivity.this, "Please enter all of " +
                            "the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = dBMain.checkUsernamePassword(user, pass);
                    if (checkuserpass) { //changed from checkuserpass == true
                        Toast.makeText(StudentLoginActivity.this, "Sign in successful",
                                Toast.LENGTH_SHORT).show();
                        //Intent to go to the next main student page when login button clicked
                        Intent intent = new Intent(StudentLoginActivity.this,
                                MainStudentActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(StudentLoginActivity.this, "Sign in unsuccessful. " +
                                        "Please make sure your id and password are correct",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void findid() {
        username = (EditText) findViewById(R.id.studentIDid);
        password = (EditText) findViewById(R.id.studentPasswordId);
        loginBttn = (Button) findViewById(R.id.loginButton);
        backBttn = (Button) findViewById(R.id.backButton);
    }

    //for back arrow functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}