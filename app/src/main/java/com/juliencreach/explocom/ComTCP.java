package com.juliencreach.explocom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ComTCP
{
    /**
     * Tag pour filtrer dans Logcat
     */
    private static final String TAG = "Message";

    //region Private Attributs

    /**
     * Socket de connexion
     */
    private Socket socket;

    /**
     * Temps maxi d'attente d'une connexion
     */
    private final static int TIMEOUT = 5000;

    /**
     * Flux de sortie sur la socket
     */
    private DataOutputStream dataOutputStream;

    /**
     * Flux d'entrée sur la socket
     */
    private DataInputStream dataInputStream;

    //endregion Private Attributs

    //region Constructor & singleton

    /**
     * Default constructeur not accessible
     */
    private ComTCP()
    {

    }

    /**
     * Instance de classe
     */
    private static ComTCP Instance = new ComTCP();

    /**
     * Assesseur sur l'instance de classe
     * @return instance
     */
    public static ComTCP getInstance()
    {
        return Instance;
    }

    //endregion Constructor & singleton

    //region Public Services

    /**
     * Connexion au serveur via socket tcp via une AsyncTask
     * @param ip addresse ip de la cible
     * @param port port utilisé sur la cible
     * @throws IOException
     */
    public void connect(String ip, String port) throws IOException
    {
        connectAT connectAT = new connectAT();
        connectAT.execute(ip, port);
    }

    /**
     * Méthode de lecture sur socket
     * @return data lues
     */
    public byte[] read()
    {
        try
        {
            if(!this.socket.isClosed())
            {
                if(this.dataInputStream != null)
                {
                    int length = this.dataInputStream.available();
                    if(length > 0)
                    {
                        byte[] message = new byte[length];

                        this.dataInputStream.readFully(message);

                        return message;
                    }
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
     * Ecriture d'un Message sur la socket TCP via une asyncTask
     * @param data Message a envoyer
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

    //endregion Public Services

    //region AsyncTask

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
                ComTCP.this.socket = new Socket();
                ComTCP.this.socket.connect(socketAddress, TIMEOUT);

                if(ComTCP.this.socket.isConnected())
                {
                    ComTCP.this.dataOutputStream = new DataOutputStream(ComTCP.this.socket.getOutputStream());
                    ComTCP.this.dataInputStream = new DataInputStream(ComTCP.this.socket.getInputStream());
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Envoi d'un Message
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

//                ComTCP.this.dataOutputStream.writeInt(data.length);
                ComTCP.this.dataOutputStream.write(data);
                ComTCP.this.dataOutputStream.flush();

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            Log.d(TAG,"bckgrnd");

            return null;
        }
    }

    //endregion AsyncTask
}
