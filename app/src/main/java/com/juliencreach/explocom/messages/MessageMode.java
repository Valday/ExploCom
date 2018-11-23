package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.modele.ModeRobot;
import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageMode extends Message
{//TODO Documentation

    //region Private Attributs

    private ModeRobot modeRobot;

    //endregion Private Attributs

    //region Public Attributs

    public ModeRobot getModeRobot()
    {
        return this.modeRobot;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageMode(ModeRobot modeRobot)
    {
        this.type = TypeMessages.MODE;
        this.modeRobot = modeRobot;
        this.message = "";
    }
    public MessageMode(TypeMessages type, ModeRobot modeRobot)
    {
        this.type = type;
        this.modeRobot = modeRobot;
    }
    public MessageMode( ModeRobot modeRobot, String msg)
    {
        this.type = TypeMessages.MODE;
        this.modeRobot = modeRobot;
        this.message = msg;
    }
    public MessageMode(TypeMessages type, ModeRobot modeRobot, String msg)
    {
        this.type = type;
        this.modeRobot = modeRobot;
        this.message = msg;
    }


    //endregion Constructors

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[2 + message.length]; // byte array de la taille de 2 bytes + taille de la string en byte

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .put(this.modeRobot.getIndice())
                .put(message);

        return toReturn;
    }

    @Override
    public String toString()
    {
        return " -> type : "+this.type
                +"\n mode : "+this.modeRobot
                +"\n Message : "+this.message;
    }

    //endregion Public services

    // region Private services

    private MessageMode toMessageMode(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);

        MessageMode toReturn = new MessageMode((TypeMessages.getValueFromId(byteBuffer.get())),
                ModeRobot.getValueFromId(byteBuffer.get()),
                new String(Arrays.copyOfRange(data,2,data.length)));
        return toReturn;
    }

    //endregion Private services
}
