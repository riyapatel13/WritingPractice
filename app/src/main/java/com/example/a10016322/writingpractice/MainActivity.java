package com.example.a10016322.writingpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button write, display;
    TextView newMessage;
    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) (findViewById(R.id.editText));
        write = (Button)(findViewById(R.id.button));
        display = (Button)(findViewById(R.id.button2));
        newMessage = (TextView) (findViewById(R.id.textView));

        final String message = (editText.getText()).toString();
        final String file = "practiceData.json";

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write.setText("Written");

                try {
                    String[] split = message.split("\\s");
                    for (int i=0; i<split.length; i++)
                    {
                        jsonArray.put(i, split[i]);
                    }

                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_WORLD_WRITEABLE));
                    writer.write(jsonArray.toString());
                    writer.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(file)));
                    JSONArray jsonArray1 = new JSONArray(bufferedReader.readLine());
                    newMessage.setText(String.valueOf(jsonArray1));
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
