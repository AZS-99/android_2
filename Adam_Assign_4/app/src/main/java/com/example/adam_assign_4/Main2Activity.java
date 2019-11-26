package com.example.adam_assign_4;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {
    TextView imgViewText;
    EditText cityET, nameET, mvpET, stadiumET;
    Spinner sportSpinner;
    Button submit, exit;
    ImageView imageView;
    String city, name, sport, mvp, stadium;
    ArrayList<String> cities, names, sports, mvps, stadiums;
    Intent intent;
    String imgPath;
    Uri imgURI;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cityET = (EditText) findViewById(R.id.cityEditText);
        nameET = (EditText) findViewById(R.id.nameEditText);
        sportSpinner = (Spinner) findViewById(R.id.sportSpinner);
        mvpET = (EditText) findViewById(R.id.mvpEditText);
        stadiumET = (EditText) findViewById(R.id.stadiumEditText);
        submit = (Button) findViewById(R.id.submitBtn);
        exit = (Button) findViewById(R.id.exitBtn);
        imageView = (ImageView) findViewById(R.id.imgView);
        imgViewText = (TextView) findViewById(R.id.imgViewText);
        cities = new ArrayList<>();
        names = new ArrayList<>();
        sports = new ArrayList<>();
        mvps = new ArrayList<>();
        stadiums = new ArrayList<>();
        imgPath = "";

        adapter = ArrayAdapter.createFromResource(this, R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(adapter);

        sportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sport = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sport = "";
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.grantPhotoAccess) {
                    accessPhotos();
                } else {
                    showAlert("", "Allow Adam_Assign_4 to access photos, files, and media on your profile?");

                }


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(cityET.getText())|| TextUtils.isEmpty(nameET.getText())) {
                    Toast.makeText(Main2Activity.this, "City and Name are required", Toast.LENGTH_LONG).show();
                }
                else {
                    city = cityET.getText().toString();
                    name = nameET.getText().toString();


                    if (TextUtils.isEmpty(mvpET.getText()))
                        mvp = "";
                    else
                        mvp = mvpET.getText().toString();

                    if (TextUtils.isEmpty(stadiumET.getText()))
                        stadium = "";
                    else
                        stadium = stadiumET.getText().toString();


                    cityET.getText().clear();
                    nameET.getText().clear();
                    mvpET.getText().clear();
                    stadiumET.getText().clear();

                    intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("city", city);
                    intent.putExtra("sport", sport);
                    intent.putExtra("mvp", mvp);
                    intent.putExtra("stadium", stadium);
                    intent.putExtra("imgPath", imgPath);
                    setResult(RESULT_OK, intent);

                }


            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && requestCode == 0) {
            imgURI = intent.getData();
            imgPath = imgURI.toString();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgURI));
                imageView.setImageBitmap(bitmap);
                imageView.setBackgroundColor(Color.parseColor("#80000000"));
                imgViewText.setText("");

            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }

    }

    public void showAlert(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main2Activity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.grantPhotoAccess = true;
                accessPhotos();

            }
        });


        builder.show();
    }

    private void accessPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }


}