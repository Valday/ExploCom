package com.juliencreach.explocom.messages;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class messageInfoTest
{
    private static final int type = 0;
    private static final String msg = "azerty";
    private static final byte[] resultExpected = {0,0,0,0,97,122,101,114,116,121};

    @Test
    public void toByteArray()
    {
        messageInfo messageInfo = new messageInfo(type,msg);
        byte[] msgInByte = messageInfo.toByteArray();
        assertArrayEquals(resultExpected,msgInByte);
    }
}