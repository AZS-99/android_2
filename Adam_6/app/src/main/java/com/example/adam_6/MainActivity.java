package com.example.adam_6;

import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    DataBase senecaDB;
    EditText name, id, marks;
    Button addStudent, viewStudents, getStudent, deleteStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senecaDB = new DataBase(this);
        name = (EditText) findViewById(R.id.nameEditText);
        id = (EditText) findViewById(R.id.idEditText);
        marks = (EditText) findViewById(R.id.marksEditText);
        addStudent = (Button) findViewById(R.id.addButton);
        viewStudents = (Button) findViewById(R.id.viewStudentsButton);
        getStudent = (Button) findViewById(R.id.getStudentButton);
        deleteStudent = (Button) findViewById(R.id.deleteStudentButton);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean insertionSuccess = senecaDB.insert(id.getText().toString(), name.getText().toString(),
                        Integer.parseInt(marks.getText().toString()));
                if (insertionSuccess) {
                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                }

                id.getText().clear();
                name.getText().clear();
                marks.getText().clear();

            }
        });

        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor allData = senecaDB.getAllData();

                StringBuffer buffer = new StringBuffer();
                while(allData.moveToNext()) {
                    buffer.append("id: " + allData.getString(0));
                    buffer.append(", Name: " + allData.getString(1));
                    buffer.append(", Mark: " + allData.getString(2));
                    buffer.append("\n");
                }
                showAlert("The following students have been added", buffer.toString());

            }
        });

        getStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor studentRow = senecaDB.getStudentById(id.getText().toString());

                StringBuffer buffer = new StringBuffer();
                while(studentRow.moveToNext()) {
                    buffer.append("id: " + studentRow.getString(0));
                    buffer.append(", Name: " + studentRow.getString(1));
                    buffer.append(", Mark: " + studentRow.getString(2));
                    buffer.append("\n");
                }
                if (studentRow.getCount() > 0) {
                    showAlert("Student details are as follows", buffer.toString());
                } else {
                    showAlert("This student does not exist", "");
                }

            }
        });

        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor studentRow = senecaDB.getStudentById(id.getText().toString());

                StringBuffer buffer = new StringBuffer();
                while(studentRow.moveToNext()) {
                    buffer.append("id: " + studentRow.getString(0));
                    buffer.append(", Name: " + studentRow.getString(1));
                    buffer.append(", Mark: " + studentRow.getString(2));
                    buffer.append("\n");
                }

                Integer deleteSuccessful = senecaDB.deleteStudentById(id.getText().toString());
                if(deleteSuccessful > 0) {
                    showAlert("Following student has been deleted", buffer.toString());
                } else {
                    showAlert("This student does not exist", "");
                }
            }
        });
    }

    public void showAlert(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
