package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zimmerman.regis.regisapplication.domain.Classes;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;
import zimmerman.regis.regisapplication.service.ICreateAccountSvc;

public class StudentClassScheduleMainActivity extends AppCompatActivity {

    ListView listView;
    Button backButton;
    ArrayList<Classes> classes;
    CreateAccountSvcSQLiteImpl dBMain;

    private ICreateAccountSvc classesSvc = null;
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_schedule_main);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(StudentClassScheduleMainActivity.this);
        classes = new ArrayList<>();
        findid();

        classesSvc = CreateAccountSvcSQLiteImpl.getInstance(getApplicationContext());

        goBack();
    }

    private void goBack() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentClassScheduleMainActivity.this, MainStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final List<Classes> list = classesSvc.retrieveAll();
        adapter = new ArrayAdapter<Classes>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentClassScheduleMainActivity.this,
                        StudentClassDetailsActivity.class);
                intent.putExtra("schedule", list.get(position));
                startActivity(intent);
            }
        });

    }

    private void findid() {
        listView = (ListView) findViewById(R.id.studentClassesListViewId);
        backButton = (Button) findViewById(R.id.backBId);
    }
}