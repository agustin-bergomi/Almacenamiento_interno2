package com.agustinbergomi.tictactoe;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName, editTextData;
    Button saveButton, readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = (EditText) findViewById(R.id.editText1);
        editTextData = (EditText) findViewById(R.id.editText2);
        saveButton = (Button) findViewById(R.id.button1);
        readButton = (Button) findViewById(R.id.button2);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = editTextFileName.getText().toString();
                String data = editTextData.getText().toString();

                FileOutputStream fos;
                try{
                    fos=MainActivity.this.openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this,filename + " saved", Toast.LENGTH_LONG).show();
                }catch (FileNotFoundException e){e.printStackTrace();}
                catch (IOException e){e.printStackTrace();

                }

            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = editTextFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();

                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(MainActivity.this.openFileInput(filename)));
                    String inputString;
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, stringBuffer.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}