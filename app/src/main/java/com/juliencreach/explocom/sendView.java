package com.juliencreach.explocom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class sendView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_view);
    }

    public void onClickSendMessage(View view) throws IOException {
        byte[] message = (((EditText) findViewById(R.id.editText_message)).getText().toString()).getBytes();

        comTCP.getInstance().write(message);
    }
}
