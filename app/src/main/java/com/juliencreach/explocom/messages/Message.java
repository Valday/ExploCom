package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.Enumerations.TypeMessages;

import java.nio.ByteBuffer;

public abstract class Message
{
    //region Protected Attributs

    protected TypeMessages type;

    protected String message;

    //endregion Protected Attributs

    //region Public Attributs

    public TypeMessages getType()
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
        byte[] toReturn = new byte[1 + message.length]; // byte array de la taille d'un byte + taille de la string en byte

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
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
