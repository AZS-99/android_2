package com.example.adam_assign_4;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileNotFoundException;



public class Main3Activity extends AppCompatActivity {
    TextView imgViewText;
    EditText nameET, cityET, mvpET, stadiumET;
    Spinner sportSpinner;
    Button updateBtn, deleteBtn, exitBtn;
    Intent intent;
    ImageView imgView;
    String imgPath;
    String city, name, sport, mvp, stadium;
    Integer id;
    final int UPDATE_IMAGE = 10;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nameET = (EditText) findViewById(R.id.nameEditText);
        cityET = (EditText) findViewById(R.id.cityEditText);
        sportSpinner = (Spinner) findViewById(R.id.sportSpinner);
        mvpET = (EditText) findViewById(R.id.mvpEditText);
        stadiumET = (EditText) findViewById(R.id.stadiumEditText);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        imgView = (ImageView) findViewById(R.id.imgView);
        imgViewText = (TextView) findViewById(R.id.imgViewText);

        intent = getIntent();
        city = intent.getStringExtra("city");
        id = intent.getIntExtra("id", 0);
        cityET.setText(intent.getStringExtra("city"));
        nameET.setText(intent.getStringExtra("name"));
        sport = intent.getStringExtra("sport");
        mvpET.setText(intent.getStringExtra("mvp"));
        stadiumET.setText(intent.getStringExtra("stadium"));

        adapter = ArrayAdapter.createFromResource(this, R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(adapter);
        if (sport != "") {
            sportSpinner.setSelection(adapter.getPosition(sport));
        }
        sportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sport = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgPath = intent.getStringExtra("imgPath");

        Uri imgURI = Uri.parse(imgPath);
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgURI));
            imgView.setImageBitmap(bitmap);
            imgViewText.setText("");
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), UPDATE_IMAGE);
            }
        });



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = cityET.getText().toString();
                name = nameET.getText().toString();
                mvp = mvpET.getText().toString();
                stadium = stadiumET.getText().toString();

                intent = new Intent();
                intent.putExtra("update", true);
                intent.putExtra("name", name);
                intent.putExtra("city", city);
                intent.putExtra("sport", sport);
                intent.putExtra("mvp", mvp);
                intent.putExtra("stadium", stadium);
                intent.putExtra("imgPath", imgPath);
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.putExtra("delete", true);
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && requestCode == UPDATE_IMAGE) {
            Uri imgURI = intent.getData();
            imgPath = imgURI.toString();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgURI));
                imgView.setImageBitmap(bitmap);
                imgViewText.setText("");
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }

    }

}