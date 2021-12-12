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

public class FacultyLoginActivity2 extends AppCompatActivity {

    EditText username, password;
    Button loginBttn, backBttn;
    CreateAccountSvcSQLiteImpl dBMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login2);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(FacultyLoginActivity2.this);
        findid();
        login();
        goBack();
    }

    private void goBack() {
        backBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyLoginActivity2.this,
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
                    Toast.makeText(FacultyLoginActivity2.this, "Please enter all of " +
                            "the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = dBMain.checkUsernamePassword(user, pass);
                    if (checkuserpass) { //changed from checkuserpass == true
                        Toast.makeText(FacultyLoginActivity2.this, "Sign in successful",
                                Toast.LENGTH_SHORT).show();
                        //Intent to go to the next main faculty page when login button clicked
                        Intent intent = new Intent(FacultyLoginActivity2.this,
                                FacultyScreenActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(FacultyLoginActivity2.this, "Sign in unsuccessful." +
                                        " Please make sure your username and password are correct",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void findid() {
        username = (EditText) findViewById(R.id.facultyUsernameId);
        password = (EditText) findViewById(R.id.facultyPasswordId);
        loginBttn = (Button) findViewById(R.id.loginButt);
        backBttn = (Button) findViewById(R.id.backButt);
    }
}