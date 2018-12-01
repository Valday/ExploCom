package com.juliencreach.explocom;

import com.juliencreach.explocom.messages.Message;

import java.io.IOException;

public class Proxy<T extends Message>
{
    private T t;

    public Proxy(T msg)
    {
        this.t = msg;
    }

    public void Send()
    {
        try
        {
            ComTCP.getInstance().write(t.toByteArray());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
