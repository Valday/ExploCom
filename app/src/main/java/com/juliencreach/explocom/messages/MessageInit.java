package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageInit extends Message
{
    //TODO Documentation

    //region Private Attributs
    private static final byte typeMessageInit = TypeMessages.INIT.getIndice();

    private byte value;

    //endregion Private Attributs

    //region Public Attributs

    public byte getValue()
    {
        return this.value;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageInit()
    {
        this.type = TypeMessages.INIT;
        this.value = 42;
    }

    public MessageInit(TypeMessages type,byte value, String msg)
    {
        this.type = type;
        this.value = value;
        this.message = msg;
    }

    public MessageInit(byte[] data)
    {
        MessageInit messageInit = this.toMessageInit(data);

        this.type = messageInit.getType();
        this.value = messageInit.getValue();
        this.message = messageInit.getMessage();
    }

    //endregion Constructors

    // region Private services

    private MessageInit toMessageInit(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageInit toReturn = new MessageInit(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.get(),
                new String(Arrays.copyOfRange(data, 2, data.length)));

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
                .put(this.value)
                .put(message);

        return super.toByteArray();
    }

    @Override
    public String toString()
    {
        return " -> type : "+this.type
                +"\n value : "+this.message;
    }

    //endregion Public services
}
