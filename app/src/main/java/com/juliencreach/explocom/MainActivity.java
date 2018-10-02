package com.juliencreach.explocom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "Message";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonConnexion(View view) throws IOException
    {
        String ip = ((EditText) findViewById(R.id.editText_IP)).getText().toString();
        String port = ((EditText) findViewById(R.id.editText_Port)).getText().toString();

        comTCP.getInstance().connect(ip, port);

    }

    public void onClickButtonSendMessage(View view) throws IOException
    {
        byte[] message = (((EditText) findViewById(R.id.editText_message)).getText().toString()).getBytes();

        comTCP.getInstance().write(message);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        try
        {
            Log.d(TAG,"on stop !");
            comTCP.getInstance().disconnect();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
