package com.juliencreach.explocom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Facteur extends AsyncTask<String, Void, Void>
{
    private static final String TAG = "IP";

    private static Socket  socket;
    private static PrintWriter printWriter;

    private Facteur()
    {

    }

    private static class SingletonHolder
    {
        static volatile Facteur INSTANCE = new Facteur();
    }

    public static Facteur getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    @Override
    protected Void doInBackground(String... strings)
    {
        int port = Integer.parseInt(strings[1]);

        try
        {
            Log.d(TAG,"Ip : "+strings[0]);
            Log.d(TAG,"Port : "+port);

            socket = new Socket(strings[0],port);

            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("This is a message!");
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
