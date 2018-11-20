package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageControl extends Message
{
    //region Private Attributs
//TODO Documentation
    private static final byte typeMessageControl = TypeMessages.CONTROL.getIndice();

    private byte x;
    private byte y;
    private int distance;

    //endregion Private Attributs

    //region Public Attributs
//TODO Documentation

    public byte getX()
    {
        return x;
    }

    public byte getY()
    {
        return y;
    }

    public int getDistance()
    {
        return distance;
    }

    //endregion Public Attributs

    //region Constructors
//TODO Documentation

    public MessageControl(byte x, byte y, int disance, String msg)
    {
        this.type = TypeMessages.CONTROL;
        this.x = x;
        this.y = y;
        this.distance = disance;
        this.message = msg;
    }
//TODO Documentation

    public MessageControl(TypeMessages type, byte x, byte y, int disance, String msg)
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.distance = disance;
        this.message = msg;
    }
//TODO Documentation

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
//TODO Documentation

    private MessageControl toMessageControl(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageControl toReturn = new MessageControl(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.get(),
                byteBuffer.get(),
                byteBuffer.getInt(),
                new String(Arrays.copyOfRange(data,7,data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services
//TODO Documentation

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[7 + message.length]; // byte array de la taille de 3 bytes et d'un int + taille de la string en byte
        //TODO rectifier taille

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .put(this.x)
                .put(this.y)
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
