package com.android.project.sqlite_employee;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    TextView name,age;
    ImageView imageView;
    DBhelper dbHelper;
    byte[] image ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= (TextView)findViewById(R.id.nameValue_textView);
        age= (TextView)findViewById(R.id.ageValue_textView);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(0);
        dbHelper = new DBhelper(this);

        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.jamesbond);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,bos);
        image= bos.toByteArray();

        dbHelper = new DBhelper(this);
        dbHelper.insertData("James Bond",35,image);
        dbHelper.getData(name,age,imageView);

    }
}