package com.example.student.a20171228_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reLoad();

    }

    public void reLoad() {
        ArrayList<String> arrayList = new ArrayList();
        File myfile2 = new File(getFilesDir(), "myfile2.txt");
        String str = "";

        try {
            FileReader fileReader = new FileReader(myfile2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (str.equals("")) {
            arrayList = new ArrayList<String>();
        } else {
            Gson gson = new Gson();
            arrayList = gson.fromJson(str, new TypeToken<ArrayList<String>>() {}.getType());
        }

        ListView lv = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mmadd) {
            Mydlog mydlog = new Mydlog();
            mydlog.show(getFragmentManager(), "Mydlog");
        }

        return super.onOptionsItemSelected(item);
    }
}
