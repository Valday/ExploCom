package com.juliencreach.explocom.messages;

public abstract class message
{
    //region Protected Attributs

    protected int type;

    protected String message;

    //endregion Protected Attributs


    public int getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }
}
