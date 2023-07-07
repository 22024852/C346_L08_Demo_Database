package sg.edu.rp.c346.id22024852.demodatabase;

import static android.R.layout.simple_list_item_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView listView;
    EditText date;
    EditText task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        listView = findViewById(R.id.listView);
        date = findViewById(R.id.editTextDate);
        task = findViewById(R.id.editTextTask);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String dateInput = date.getText().toString();
                String taskInput = task.getText().toString();
                // Insert a task
                db.insertTask(taskInput, dateInput);

                task.setText("");
                date.setText("");
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += (i+1) + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                ArrayList<Task> data2 = db.getTasks();
                ArrayAdapter aaTask = new ArrayAdapter<>(MainActivity.this, simple_list_item_1,data2);
                listView.setAdapter(aaTask);

                task.setText("");
                date.setText("");



            }
        });

    }
}