package com.juliencreach.explocom;

import android.util.Log;

import com.juliencreach.explocom.messages.MessageAu;
import com.juliencreach.explocom.messages.MessageEtat;
import com.juliencreach.explocom.messages.MessageInfo;
import com.juliencreach.explocom.messages.MessageInit;
import com.juliencreach.explocom.modele.ViewModel;

public class Dispatcher
{
    //region Private Attributs

    private Thread moteur;

    private volatile Thread currentThread = null;
    //endregion Private Attributs

    //region Constructor & singleton

    /**
     * Default constructeur not accessible
     */
    private Dispatcher()
    {

    }

    /**
     * Instance de classe
     */
    private static Dispatcher Instance = new Dispatcher();

    /**
     * Assesseur sur l'instance de classe
     * @return instance
     */
    public static Dispatcher getInstance()
    {
        return Instance;
    }

    //endregion Constructor & singleton

    //region Public Services

    public void StartRead()
    {
        this.moteur = new Thread(new MonRunable(), "Thread de lecture");
        this.moteur.start();
    }

    public class MonRunable implements Runnable
    {
        @Override
        public void run()
        {
            currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted())
            {
                byte[] result = ComTCP.getInstance().read();
                if(result != null)
                {
                switch (result[0])
                {
                    case -3:
                        MessageInfo messageInfo = new MessageInfo(result);
                        ViewModel.getInstance().setTestLabel(messageInfo.toString());
                        Log.d("MessageIn",""+messageInfo.toString());
                        break;
                    case -2:
                        break;
                    case -1:
                        MessageInit messageInit = new MessageInit(result);
                        ViewModel.getInstance().setTestLabel(messageInit.toString());
                        Log.d("MessageIn",""+messageInit.toString());
                        break;
                    case 1:
                        MessageAu messageAu = new MessageAu(result);
                        ViewModel.getInstance().setTestLabel(messageAu.toString());
                        Log.d("MessageIn",""+messageAu.toString());
                        break;
                    case 6:
                        MessageEtat messageEtat = new MessageEtat(result);
                        ViewModel.getInstance().setTestLabel(messageEtat.toString());
                        Log.d("MessageIn",""+messageEtat.toString());
                        break;
                    default:
                        break;
                }
                }
            }
        }
    }

    /**
     * Arret de la lecture
     */
    public void stopRead()
    {
        currentThread.interrupt();
    }

    //endregion Public Services
}
