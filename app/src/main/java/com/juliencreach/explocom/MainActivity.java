package com.juliencreach.explocom;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.juliencreach.explocom.Enumerations.ModeRobot;
import com.juliencreach.explocom.Enumerations.TypeMessages;
import com.juliencreach.explocom.databinding.ActivityMainBinding;
import com.juliencreach.explocom.messages.MessageChoixPistes;
import com.juliencreach.explocom.messages.MessageControl;
import com.juliencreach.explocom.messages.MessageInfo;
import com.juliencreach.explocom.messages.MessageInit;
import com.juliencreach.explocom.messages.MessageMode;
import com.juliencreach.explocom.modele.ViewModel;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
//    private ViewModel viewModel;

    /**
     * Tag pour filtrer dans Logcat
     */
    private static final String TAG = "Message";

    //region Events

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

//        ViewModel.getInstance().setTestLabel("toto");
        binding.setViewModel(ViewModel.getInstance());

//        setContentView(R.layout.activity_main);
    }

    public void onClickButtonConnexion(View view) throws IOException
    {
        String ip = ((EditText) findViewById(R.id.editText_IP)).getText().toString();
        String port = ((EditText) findViewById(R.id.editText_Port)).getText().toString();

        ComTCP.getInstance().connect(ip, port); //connexion au serveur

        Dispatcher.getInstance().StartRead(); // Lancement du thread de lecture
    }

    public void onClickButtonSendMessageInfo(View view) throws IOException
    {
        MessageInfo msg = new MessageInfo(TypeMessages.INFO,((EditText) findViewById(R.id.editText_messageInfo)).getText().toString());

        new Proxy<MessageInfo>(msg).Send(); // envoie de message
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Dispatcher.getInstance().stopRead();

        try
        {
            Log.d(TAG,"on stop !");
            ComTCP.getInstance().disconnect();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickButtonSendMessageEtat(View view)
    {
//        MessageEtat msg = new MessageEtat(((EditText) findViewById(R.id.editText_etat)).getText().toString().getBytes()[0],
//                ((EditText) findViewById(R.id.editText_)).getText().toString().getBytes()[0],
//                );
    }

    public void onClickButtonSendMessageControl(View view)
    {
        MessageControl msg = new MessageControl((byte)Integer.parseInt(((EditText) findViewById(R.id.editText_x)).getText().toString()),
                ((byte)Integer.parseInt(((EditText) findViewById(R.id.editText_y)).getText().toString())),
                Integer.parseInt(((EditText) findViewById(R.id.editText_distance)).getText().toString()),
                ((EditText) findViewById(R.id.editText_messageControl)).getText().toString());


//        byte[] asup = ((EditText) findViewById(R.id.editText_y)).getText().toString().getBytes();
//        MessageControl msg = new MessageControl((byte)0,(byte)100,50,"");

        new Proxy<MessageControl>(msg).Send();
    }

    public void onClickButtonSendMessageChoixPiste(View view)
    {
        Log.d("CHOIXPISTE","obj entry");

        MessageChoixPistes msg = new MessageChoixPistes(Integer.parseInt(((EditText) findViewById(R.id.editText_piste)).getText().toString()),
                Integer.parseInt(((EditText) findViewById(R.id.editText_nbRun)).getText().toString()),
                Integer.parseInt(((EditText) findViewById(R.id.editText_nbFail)).getText().toString()),
                ((EditText) findViewById(R.id.editText_messageControl)).getText().toString());

        new Proxy<MessageChoixPistes>(msg).Send();

    }

    public void onClickButtonSendMessageRecuperationPiste(View view)
    {
    }

    public void onClickButtonInit(View view)
    {
        MessageInit msg = new MessageInit();

        new Proxy<MessageInit>(msg).Send();
    }

    public void onClickButtonModeManu(View view)
    {
        MessageMode msg = new MessageMode(ModeRobot.MANUEL);

        new Proxy<MessageMode>(msg).Send();

    }

    //endregion Events
}