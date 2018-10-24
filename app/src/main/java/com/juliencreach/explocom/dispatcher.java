package com.juliencreach.explocom;

import android.util.Log;

public class dispatcher extends Thread
{
    private Boolean isRunning = false;
    private dispatcher()
    {

    }

    private static dispatcher Instance = new dispatcher();

    public static dispatcher getInstance()
    {
        return Instance;
    }

    @Override
    public void run()
    {
        this.isRunning = true;

        while (this.isRunning)
        {
            byte[] result = comTCP.getInstance().read();
            if(result != null)
            {
                String resultMsg = new String(result);
                Log.d("MessageIn",resultMsg);
            }
        }
    }

    public void stopRead()
    {
        this.isRunning = false;
    }


}
