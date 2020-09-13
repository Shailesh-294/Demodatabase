package com.example.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText fname,lname,marks;
    Button save,show;
    TextView setdata;
    Databasehelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        marks = findViewById(R.id.mark);
        setdata = findViewById(R.id.setdata);
        save = findViewById(R.id.Save);
        show = findViewById(R.id.showdata);
        myDb = new Databasehelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname,lastname,mark;
                firstname = fname.getText().toString();
                lastname = lname.getText().toString();
                mark = marks.getText().toString();
                boolean result = myDb.insertData(firstname,lastname,mark);
                if(result){
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    fname.setText("");
                    lname.setText("");
                    marks.setText("");
                    fname.requestFocus();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                Cursor cursor = myDb.getAllData();
                if(cursor != null && cursor.getCount()>0) {
                    cursor.moveToFirst();
                    do {
                        String firstN = cursor.getString(1);
                        String lastN = cursor.getString(2);
                        String Mark = cursor.getString(3);
                        data += firstN +" "+ lastN +" " +Mark +"\n";
                    } while (cursor.moveToNext());
                    setdata.setText(data);
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}