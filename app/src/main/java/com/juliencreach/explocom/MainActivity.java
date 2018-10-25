package com.juliencreach.explocom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.juliencreach.explocom.messages.messageInfo;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    /**
     * Tag pour filtrer dans Logcat
     */
    private static final String TAG = "Message";

    //region Events

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

        comTCP.getInstance().connect(ip, port); //connexion au serveur

        dispatcher.getInstance().start(); // Lancement du thread de lecture
    }

    public void onClickButtonSendMessage(View view) throws IOException
    {
        messageInfo msg = new messageInfo(0,((EditText) findViewById(R.id.editText_message)).getText().toString());
        byte[] message = msg.toByteArray();
        comTCP.getInstance().write(message);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        dispatcher.getInstance().stopRead();

        try
        {
            Log.d(TAG,"on stop !");
            comTCP.getInstance().disconnect();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //endregion Events
}
