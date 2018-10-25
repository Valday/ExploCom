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
    private comTCP()
    {

    }

    /**
     * Instance de classe
     */
    private static comTCP Instance = new comTCP();

    /**
     * Assesseur sur l'instance de classe
     * @return instance
     */
    public static comTCP getInstance()
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

    //endregion AsyncTask
}
