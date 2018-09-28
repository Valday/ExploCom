package com.juliencreach.explocom;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class comTCP
{
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

    public void connect(String ip, String port) throws IOException
    {
        connectAT connectAT = new connectAT();
        connectAT.execute(ip, port);
    }

    public byte[] read() throws IOException
    {
        this.dataInputStream = new DataInputStream((this.socket.getInputStream()));

        int length = this.dataInputStream.readInt();
        if(length > 0)
        {
            byte[] message = new byte[length];
            this.dataInputStream.readFully(message,0,message.length);

            return message;
        }

        return null;
    }

    public void write(byte[] data) throws IOException
    {
        writeAT writeAT = new writeAT();
        writeAT.execute(ArrayUtils.toObject(data));
    }

    public void disconnect() throws IOException
    {
        this.socket.close();
    }

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

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class writeAT extends AsyncTask<Byte[],Void, Void>
    {
        @Override
        protected Void doInBackground(Byte[]... bytes)
        {

            try
            {
                comTCP.this.dataOutputStream = new DataOutputStream(comTCP.this.socket.getOutputStream());
                comTCP.this.dataOutputStream.writeInt(bytes[0].length);
                comTCP.this.dataOutputStream.write(ArrayUtils.toPrimitive(bytes[0]));
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

}
