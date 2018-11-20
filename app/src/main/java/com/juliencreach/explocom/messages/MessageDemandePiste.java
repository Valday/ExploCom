package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageDemandePiste extends Message
{
    //region Private Attributs

    private byte cmd;

    //endregion Private Attributs

    //region Public Attributs
    //endregion Public Attributs

    //region Constructors

    public MessageDemandePiste(byte cmd)
    {
        this.type = TypeMessages.RECUPERATION_PISTES;
        this.cmd = cmd;
    }

    public MessageDemandePiste(TypeMessages type, byte cmd)
    {
        this.type = type;
        this.cmd = cmd;
    }

    public MessageDemandePiste(TypeMessages type, byte cmd, String msg)
    {
        this.type = type;
        this.cmd = cmd;
        this.message = msg;
    }

    public MessageDemandePiste(byte[] data)
    {
        MessageDemandePiste messageDemandePiste = toMessageRecuperationPistes(data);

        this.type = messageDemandePiste.getType();
        this.message = messageDemandePiste.getMessage();
    }

    //endregion Constructors

    // region Private services

    private MessageDemandePiste toMessageRecuperationPistes(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);

        MessageDemandePiste toReturn = new MessageDemandePiste(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.get(),
                new String(Arrays.copyOfRange(data,2, data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[2 + message.length];

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .put(this.cmd)
                .put(message);

        return toReturn;
    }

    @Override
    public String toString()
    {
        return " -> type : "+this.type
            +"\n cmd : "+this.cmd
            +"\n message : "+this.message;
    }


    //endregion Public services
}
