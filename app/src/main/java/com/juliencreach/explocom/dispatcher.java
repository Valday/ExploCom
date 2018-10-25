package com.juliencreach.explocom;

import android.util.Log;

import com.juliencreach.explocom.messages.messageInfo;

public class dispatcher extends Thread
{
    //region Private Attributs

    private Boolean isRunning = false;

    //endregion Private Attributs

    //region Constructor & singleton

    /**
     * Default constructeur not accessible
     */
    private dispatcher()
    {

    }

    /**
     * Instance de classe
     */
    private static dispatcher Instance = new dispatcher();

    /**
     * Assesseur sur l'instance de classe
     * @return instance
     */
    public static dispatcher getInstance()
    {
        return Instance;
    }

    //endregion Constructor & singleton

    //region Public Services

    /**
     * Thread de lecture sur la socket
     */
    @Override
    public void run()
    {
        this.isRunning = true;

        while (this.isRunning)
        {
            byte[] result = comTCP.getInstance().read();
            if(result != null)
            {
                messageInfo messageInfo = new messageInfo(result);
                Log.d("MessageIn",""+messageInfo.toString());
            }
        }
    }

    /**
     * Arret de la lecture
     */
    public void stopRead()
    {
        this.isRunning = false;
    }

    //endregion Public Services
}
