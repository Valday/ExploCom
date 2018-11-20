package com.juliencreach.explocom.divers;

public class ArrayUtils extends Object
{
    private ArrayUtils()
    {

    }

    public static byte[] toPrimitive(Byte[] data)
    {
        byte[] bytes = new byte[data.length];
        int i = 0;

        for(Byte b: data)
        {
            bytes[i++] = b.byteValue();
        }

        return bytes;
    }

    public static Byte[] toObject(byte[] data)
    {
        Byte[] bytes = new Byte[data.length];
        int i = 0;

        for(byte b: data)
        {
            bytes[i++] = b;
        }

        return bytes;
    }
}
