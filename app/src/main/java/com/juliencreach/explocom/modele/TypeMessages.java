package com.juliencreach.explocom.modele;

public enum TypeMessages
{
    IDLE(-5),
    INFO(-3),
    FEMETURE_CONNEXION(-2),
    INIT(-1),
    AU(1),
    CONTROL(2),
    MODE(3),
    RECUPERATION_PISTES(4),
    CHOIX_PISTE(5),
    ETAT(6);



    private byte indice;

    private TypeMessages(int indice)
    {
        this.indice = (byte)indice;
    }

    public byte getIndice()
    {
        return this.indice;
    }

    public static TypeMessages getValueFromId(byte id)
    {
        for(TypeMessages e : values())
        {
            if(e.indice == id)
            {
                return e;
            }
        }

        return IDLE;
    }
}
