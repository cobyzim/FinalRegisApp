package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //added for database connection
    private Button createAccountButton = null;
    private Button studentLoginButton = null;
    private Button facultyLoginButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "entering onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "entering onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Move to create account activity after clicking on create account button
        createAccountButton = (Button)findViewById(R.id.createAccountButtonID);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        CreateAccountActivity.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, "entering onResume");

        //Move to faculty login activity after clicking on the faculty button
        facultyLoginButton = (Button)findViewById(R.id.mainFacultyButtonID);
        facultyLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        FacultyLoginActivity2.class);
                startActivity(intent);
            }
        });

        //Move to student login activity after clicking on student button
        studentLoginButton = (Button)findViewById(R.id.mainStudentButtonID);
        studentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        StudentLoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "entering onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "entering onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "entering onDestroy");
    }


}