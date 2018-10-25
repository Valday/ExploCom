package com.juliencreach.explocom.messages;

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
    public MessageInfo(short type, String msg)
    {
        this.type = type;
        this.message = msg;
    }

    /**
     * Constructor from byte
     * @param data data reçue
     */
    public MessageInfo(byte[] data)
    {
        MessageInfo messageInfo = toMessageInfo(data);
        this.type = messageInfo.getType();
        this.message = messageInfo.getMessage();
    }

    //endregion Constructors

    //region Public Services

    public MessageInfo toMessageInfo(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageInfo toReturn = new MessageInfo(byteBuffer.getShort(),new String(Arrays.copyOfRange(data,2,data.length)));

        return toReturn;
    }

    //endregion Public Services
}
