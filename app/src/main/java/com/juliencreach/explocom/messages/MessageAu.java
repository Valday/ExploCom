package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.Enumerations.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageAu extends Message
{
    //region Private Attributs

    private byte etatAu;

    //endregion Private Attributs

    //region Public Attributs

    public byte getEtatAu()
    {
        return this.etatAu;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageAu(byte etatAu)
    {
        this.type = TypeMessages.AU;
        this.etatAu = etatAu;
        this.message = "";
    }

    public MessageAu(TypeMessages type, byte etatAu)
    {
        this.type = type;
        this.etatAu = etatAu;
        this.message = "";
    }

    public MessageAu(TypeMessages type, byte etatAu, String msg)
    {
        this.type = type;
        this.etatAu = etatAu;
        this.message = msg;
    }

    public MessageAu(byte[] data)
    {
        MessageAu messageAu = this.toMessageAu(data);
        this.type = messageAu.getType();
        this.etatAu = messageAu.getEtatAu();
        this.message = messageAu.getMessage();
    }

    //endregion Constructors

    // region Private services

    private MessageAu toMessageAu(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap( data);
        MessageAu toReturn = new MessageAu(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.get(),
                new String(Arrays.copyOfRange(data,2,data.length)));

        return toReturn;
    }



    //endregion Private services

    //region Public services
//TODO Documentation
    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[2 + message.length];

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .put(this.etatAu)
                .put(message);

        return toReturn;
    }

    @Override
    public String toString() {
        return " -> type : "+this.type
                +"\n etat Au : "+this.etatAu
                +"\n message : "+this.message;
    }

    //endregion Public services
}
