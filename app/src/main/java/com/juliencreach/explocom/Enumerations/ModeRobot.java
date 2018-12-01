package com.juliencreach.explocom.Enumerations;

public enum ModeRobot
{
    IDLE(-1),
    MANUEL(1),
    AUTO(2);

    private byte indice;

    private ModeRobot(int indice)
    {
        this.indice = (byte)indice;
    }

    public byte getIndice()
    {
        return this.indice;
    }

    public static ModeRobot getValueFromId(byte id)
    {
        for(ModeRobot e : values()) {
            if(e.indice == id) return e;
        }

        return IDLE;
    }
}
