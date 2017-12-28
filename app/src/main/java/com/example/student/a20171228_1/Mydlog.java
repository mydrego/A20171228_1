package com.example.student.a20171228_1;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by student on 2017/12/28.
 */

public class Mydlog extends DialogFragment {
    EditText ed;
    Button bt;
    ArrayList<String> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.mylayout, null);
        bt = view.findViewById(R.id.button);
        ed = view.findViewById(R.id.editText);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File myfile2 = new File(getActivity().getFilesDir(), "myfile2.txt");
                String str = "";
                try {
                    FileReader fileReader = new FileReader(myfile2);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    str = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (str.equals(""))
                {
                    arrayList = new ArrayList<String>();
                }
                else
                    {
                    Gson gson = new Gson();
                    arrayList = gson.fromJson(str , new TypeToken<ArrayList<String>>() {}.getType());
                }

                try {
                    arrayList.add(ed.getText().toString());
                    FileWriter fw = new FileWriter(myfile2);
                    BufferedWriter bw = new BufferedWriter(fw);
                    Gson gson = new Gson();
                    bw.write(gson.toJson(arrayList));
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dismiss();
            }
        });
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ((MainActivity) getActivity()).reLoad();
    }


}
