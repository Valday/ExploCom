package com.juliencreach.explocom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class comTCP
{
    private static final String TAG = "Message";

    private Socket socket;
    private final static int TIMEOUT = 5000;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private comTCP()
    {

    }

    private static comTCP Instance = new comTCP();

    public static comTCP getInstance()
    {
        return Instance;
    }

    /**
     * Connexion au serveur via socket tcp via une AsyncTask
     * @param ip
     * @param port
     * @throws IOException
     */
    public void connect(String ip, String port) throws IOException
    {
        connectAT connectAT = new connectAT();
        connectAT.execute(ip, port);
    }

    public byte[] read()
    {
        try
        {
            if(this.dataInputStream != null)
            {
                int length = this.dataInputStream.readInt();
                if(length > 0)
                {
                    byte[] message = new byte[length];

                    this.dataInputStream.readFully(message,0,message.length);

                    return message;
                }
            }
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            Log.d(TAG," ==> Perte de connexion avec le serveur");
        }
        return null;
    }

    /**
     * Ecriture d'un message sur la socket TCP via une asyncTask
     * @param data message a envoyer
     * @throws IOException
     */
    public void write(byte[] data) throws IOException
    {
        writeAT writeAT = new writeAT();
        writeAT.execute(data);
    }

    /**
     * Deconnexion
     * @throws IOException
     */
    public void disconnect() throws IOException
    {
        if(this.socket != null)
        {
            this.dataOutputStream.close();
            this.dataInputStream.close();
            this.socket.close();
        }
    }

    public Boolean socketState()
    {
        return this.socket.isConnected();
    }

    /**
     * Connexion au serveur
     */
    private class connectAT extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings)
        {
            try
            {
                SocketAddress socketAddress = new InetSocketAddress(strings[0],Integer.parseInt(strings[1]));
                comTCP.this.socket = new Socket();
                comTCP.this.socket.connect(socketAddress, TIMEOUT);

                if(comTCP.this.socket.isConnected())
                {
                    comTCP.this.dataOutputStream = new DataOutputStream(comTCP.this.socket.getOutputStream());
                    comTCP.this.dataInputStream = new DataInputStream(comTCP.this.socket.getInputStream());
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Envoi d'un message
     */
    private class writeAT extends AsyncTask<byte[],Void, Void>
    {
        @Override
        protected Void doInBackground(byte[]... bytes)
        {
            try
            {
                byte[] data = bytes[0];
                Log.d(TAG,new String(data));

                comTCP.this.dataOutputStream.writeInt(data.length);
                comTCP.this.dataOutputStream.write(data);
                comTCP.this.dataOutputStream.flush();

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            Log.d(TAG,"bckgrnd");

            return null;
        }
    }
}
