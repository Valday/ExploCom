package com.juliencreach.explocom.messages;

import android.util.Log;

import com.juliencreach.explocom.R;
import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageChoixPistes extends Message
{
    //region Private Attributs

    private int id;
    private int nbRun;
    private int nbFail;

    //endregion Private Attributs

    //region Public Attributs

    public int getId()
    {
        return this.id;
    }

    public int getNbRun()
    {
        return this.nbRun;
    }

    public int getNbFail()
    {
        return this.nbFail;
    }


    //endregion Public Attributs

    //region Constructors

    public MessageChoixPistes(int id, int nbRun, int nbFail)
    {
        this.type = TypeMessages.CHOIX_PISTE;
        this.id = id;
        this.nbRun = nbRun;
        this.nbFail = nbFail;
        this.message = "";
    }

    public MessageChoixPistes(int id, int nbRun, int nbFail, String msg)
    {
        this.type = TypeMessages.CHOIX_PISTE;
        this.id = id;
        this.nbRun = nbRun;
        this.nbFail = nbFail;
        this.message = msg;
    }

    public MessageChoixPistes(TypeMessages type, int id, int nbRun, int nbFail)
    {
        this.type = type;
        this.id = id;
        this.nbRun = nbRun;
        this.nbFail = nbFail;
        this.message = "";
    }

    public MessageChoixPistes(TypeMessages type, int id, int nbRun, int nbFail, String msg)
    {
        this.type = type;
        this.id = id;
        this.nbRun = nbRun;
        this.nbFail = nbFail;
        this.message = msg;
    }

    public MessageChoixPistes(byte[] data)
    {
        MessageChoixPistes messageChoixPistes = this.toMessageChoixPiste(data);

        this.type = messageChoixPistes.getType();
        this.id = messageChoixPistes.getId();
        this.nbRun = messageChoixPistes.getNbRun();
        this.nbFail = messageChoixPistes.getNbFail();
        this.message = messageChoixPistes.getMessage();
    }

    //endregion Constructors

    // region Private services

    private MessageChoixPistes toMessageChoixPiste(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);

        MessageChoixPistes toReturn = new MessageChoixPistes(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.getInt(),
                byteBuffer.getInt(),
                byteBuffer.getInt(),
                new String(Arrays.copyOfRange(data, 13, data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[13 + message.length];

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .putInt(this.id)
                .putInt(this.nbRun)
                .putInt(this.nbFail)
                .put(message);

        return super.toByteArray();
    }

    @Override
    public String toString()
    {
        return " -> type : "+this.type
                +"\n id : "+this.id
                +"\n nombre de run : "+this.nbRun
                +"\n nombre de fail : "+this.nbFail
                +"\n message : "+this.message;
    }


    //endregion Public services
}
