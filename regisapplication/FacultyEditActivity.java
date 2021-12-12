package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import zimmerman.regis.regisapplication.domain.Classes;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;
import zimmerman.regis.regisapplication.service.ICreateAccountSvc;

public class FacultyEditActivity extends AppCompatActivity {

    ListView listView;
    Button addNew;
    ArrayList<Classes> classes;
    CreateAccountSvcSQLiteImpl dBMain;

    private ICreateAccountSvc classesSvc = null;
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_edit);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(FacultyEditActivity.this);
        classes = new ArrayList<>();
        findid();

        classesSvc = CreateAccountSvcSQLiteImpl.getInstance(getApplicationContext());

        addNewClass();
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
                Intent intent = new Intent(FacultyEditActivity.this,
                        ClassDetailsActivity.class);
                intent.putExtra("schedule", list.get(position));
                startActivity(intent);
            }
        });
    }

    private Classes getClasses(Cursor cursor) {
        Classes classes = new Classes();
        classes.setId(cursor.getInt(0));
        classes.setStudentIdOrFacultyName(cursor.getString(1));
        classes.setCourseNameTitle(cursor.getString(2));
        classes.setMeetingInfo(cursor.getString(3));
        classes.setLocation(cursor.getString(4));
        classes.setTerm(cursor.getString(5));
        classes.setStartDate(cursor.getString(6));
        return classes;
    }

    private void addNewClass() {
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyEditActivity.this,
                        AddClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        listView = (ListView) findViewById(R.id.classesListViewId);
        addNew = (Button) findViewById(R.id.addNewButtonId);
    }
}