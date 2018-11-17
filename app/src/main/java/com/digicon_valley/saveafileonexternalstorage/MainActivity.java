package com.digicon_valley.saveafileonexternalstorage;

;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText messageBox;
    TextView messagesave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageBox=findViewById(R.id.edit_text);
        messagesave=findViewById(R.id.text_view);

        messagesave.setVisibility(View.GONE);
    }

    public void readMessage(View view) {


        String state;

        state=Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            File Root = Environment.getExternalStorageDirectory();

            File Dir = new File(Root.getAbsolutePath() + "/MyAppFile");

            if (!Dir.exists()) {

                Dir.mkdir();
            }
            File file = new File(Dir, "MyMessage.txt");
            String Message = messageBox.getText().toString();

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                fileOutputStream.write(Message.getBytes());
                fileOutputStream.close();
                messageBox.setText("");
                Toast.makeText(getApplicationContext(), "Message Saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getApplicationContext(), "SD Card Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    public void saveMessage(View view) {

        File Root = Environment.getExternalStorageDirectory();
        File Dir = new File(Root.getAbsolutePath() + "/MyAppFile");
        File file = new File(Dir, "MyMessage.txt");

        String Message;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((Message = bufferedReader.readLine()) != null) {

                stringBuffer.append(Message + "\n");
            }
            messagesave.setText(stringBuffer.toString());
            messagesave.setVisibility(View.VISIBLE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
