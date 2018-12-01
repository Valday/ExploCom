package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.Enumerations.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageInfo extends Message
{
    //region Constructors

    /**
     * Constructor from datas
     * @param type type de la trame
     * @param msg Message a envoyer
     */
    public MessageInfo(TypeMessages type, String msg)
    {
        this.type = type;
        this.message = msg;
    }

    /**
     * Constructor from datas
     * @param msg Message a envoyer
     */
    public MessageInfo(String msg)
    {
        this.type = TypeMessages.INFO;
        this.message = msg;
    }

    /**
     * Constructor from byte
     * @param data data reçue
     */
    public MessageInfo(byte[] data)
    {
        MessageInfo messageInfo = this.toMessageInfo(data);
        this.type = messageInfo.getType();
        this.message = messageInfo.getMessage();
    }

    //endregion Constructors

    //region Private Services

    /**
     * TODO
     * @param data
     * @return
     */
    private MessageInfo toMessageInfo(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageInfo toReturn = new MessageInfo(TypeMessages.getValueFromId(byteBuffer.get()),new String(Arrays.copyOfRange(data,1,data.length)));

        return toReturn;
    }

    //endregion Private Services
}
