package com.juliencreach.explocom.messages;

import java.nio.ByteBuffer;

public abstract class Message
{
    //region Protected Attributs

    protected short type;

    protected String message;

    //endregion Protected Attributs

    //region Public Attributs

    public short getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }

    //endregion Public services

    //region Public services

    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[2 + message.length]; // byte array de la taille d'un short + taille de la string en byte

        ByteBuffer.wrap(toReturn).putShort(this.type)
                .put(message);

        return toReturn;
    }

    /**
     * Override toString display all Attributs
     * @return result
     */
    @Override
    public String toString()
    {
        return " -> type : "+this.type
                +"\n Message : "+this.message;
    }

    //endregion Public services
}
