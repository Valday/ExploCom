package com.juliencreach.explocom.messages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class messageInfo extends message
{
    //region Constructors

    /**
     * Constructor from datas
     * @param type type de la trame
     * @param msg message a envoyer
     */
    public messageInfo (int type, String msg)
    {
        this.type = type;
        this.message = msg;
    }

    /**
     * Constructor from byte
     * @param data data reçue
     */
    public messageInfo (byte[] data)
    {
        messageInfo messageInfo = toMessageInfo(data);
        this.type = messageInfo.getType();
        this.message = messageInfo.getMessage();
    }

    //endregion Constructors

    //region Public Services

    /**
     * Convert messageInfo to a byte array
     * @return byte array
     */
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[4 + message.length]; // byte array de la taille d'un int + taille de la string en byte

        ByteBuffer.wrap(toReturn).putInt(this.type)
                .put(message);

        return toReturn;
    }

    public messageInfo toMessageInfo(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        messageInfo toReturn = new messageInfo(byteBuffer.getInt(),new String(Arrays.copyOfRange(data,4,data.length)));

        return toReturn;
    }

    /**
     * Override toString display all Attributs
     * @return result
     */
    @Override
    public String toString()
    {
        return " -> type : "+this.type+"\n message : "+this.message;
    }

    //endregion Public Services
}
