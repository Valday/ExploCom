package com.juliencreach.explocom.messages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageControl extends Message
{
    //region Private Attributs

    private short x;
    private short y;
    private int distance;

    //endregion Private Attributs

    //region Public Attributs

    public short getX()
    {
        return x;
    }

    public short getY()
    {
        return y;
    }

    public int getDistance()
    {
        return distance;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageControl(short type, short x, short y, int disance, String msg)
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.distance = disance;
        this.message = msg;
    }

    public MessageControl(byte[] data)
    {
        MessageControl messageControl = this.toMessageControl(data);

        this.type = messageControl.getType();
        this.x = messageControl.getX();
        this.y = messageControl.getY();
        this.distance = messageControl.getDistance();
        this.message = messageControl.getMessage();
    }

    //endregion Constructors

    //region Private services

    private MessageControl toMessageControl(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageControl toReturn = new MessageControl(byteBuffer.getShort(),
                byteBuffer.getShort(),
                byteBuffer.getShort(),
                byteBuffer.getInt(),
                new String(Arrays.copyOfRange(data,10,data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[10 + message.length]; // byte array de la taille de short et d'un int + taille de la string en byte

        ByteBuffer.wrap(toReturn).putShort(this.type)
                .putShort(this.x)
                .putShort(this.y)
                .putInt(this.distance)
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
                +"\n x : "+this.x
                +"\n y : "+this.y
                +"\n distance : "+this.distance
                +"\n Message : "+this.message;
    }

    //endregion Public services
}
