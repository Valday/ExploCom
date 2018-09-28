package com.juliencreach.explocom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonConnexion(View view)
    {
        String ip = ((EditText) findViewById(R.id.editText_IP)).getText().toString();
        String port = ((EditText) findViewById(R.id.editText_Port)).getText().toString();
        Facteur.getInstance().execute(ip, port);
    }
}
