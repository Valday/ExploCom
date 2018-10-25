package com.juliencreach.explocom.messages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageEtat extends Message
{
    //region Private Attributs

    private short etat;

    //endregion Private Attributs

    //region Public Attributs

    public short getEtat()
    {
        return etat;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageEtat(short type, short etat, String msg)
    {
        this.type = type;
        this.etat = etat;
        this.message = msg;
    }

    public MessageEtat(byte[] data)
    {
        MessageEtat messageEtat = toMessageEtat(data);
        this.type = messageEtat.getType();
        this.etat = messageEtat.getEtat();
        this.message = messageEtat.getMessage();
    }

    //endregion Constructors

    //region Private services

    private MessageEtat toMessageEtat(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageEtat toReturn = new MessageEtat(byteBuffer.getShort(),
                byteBuffer.getShort(),
                new String(Arrays.copyOfRange(data,4,data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[4 + message.length]; // byte array de la taille de deux short + taille de la string en byte

        ByteBuffer.wrap(toReturn).putShort(this.type)
                .putShort(this.etat)
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
                +"\n etat : "+this.etat
                +"\n Message : "+this.message;
    }

    //endregion Public services
}
