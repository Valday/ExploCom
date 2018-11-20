package com.juliencreach.explocom.messages;

import com.juliencreach.explocom.modele.TypeMessages;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MessageEtat extends Message
{//TODO Documentation

    //region Private Attributs

    private byte etat;
    private byte vitRoueGauche;
    private byte vitRoueDroite;
    private byte vitInstant;
    private short distance;

    //endregion Private Attributs

    //region Public Attributs

    public byte getEtat()
    {
        return etat;
    }

    public byte getVitRoueGauche()
    {
        return this.vitRoueGauche;
    }

    public byte getVitRoueDroite()
    {
        return this.vitRoueDroite;
    }

    public byte getVitInstant()
    {
        return this.vitInstant;
    }

    public short getDistance()
    {
        return this.distance;
    }

    //endregion Public Attributs

    //region Constructors

    public MessageEtat(TypeMessages type, byte etat, byte vitesseRoueGauche, byte vitesseRoueDroite, byte vitesseInstant, short distance, String msg)
    {
        this.type = type;
        this.etat = etat;
        this.vitRoueGauche = vitesseRoueGauche;
        this.vitRoueDroite = vitesseRoueDroite;
        this.vitInstant = vitesseInstant;
        this.distance = distance;
        this.message = msg;
    }

    public MessageEtat(byte etat, byte vitesseRoueGauche, byte vitesseRoueDroite, byte vitesseInstant, short distance, String msg)
    {
        this.type = TypeMessages.ETAT;
        this.etat = etat;
        this.vitRoueGauche = vitesseRoueGauche;
        this.vitRoueDroite = vitesseRoueDroite;
        this.vitInstant = vitesseInstant;
        this.distance = distance;
        this.message = msg;
    }

    public MessageEtat(byte[] data)
    {
        MessageEtat messageEtat = toMessageEtat(data);
        this.type = messageEtat.getType();
        this.etat = messageEtat.getEtat();
        this.vitRoueGauche = messageEtat.getVitRoueGauche();
        this.vitRoueDroite = messageEtat.getVitRoueDroite();
        this.vitInstant = messageEtat.getVitInstant();
        this.distance = messageEtat.getDistance();
        this.message = messageEtat.getMessage();
    }

    //endregion Constructors

    //region Private services

    private MessageEtat toMessageEtat(byte[] data)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        MessageEtat toReturn = new MessageEtat(TypeMessages.getValueFromId(byteBuffer.get()),
                byteBuffer.get(),
                byteBuffer.get(),
                byteBuffer.get(),
                byteBuffer.get(),
                byteBuffer.getShort(),
                new String(Arrays.copyOfRange(data,7,data.length)));

        return toReturn;
    }

    //endregion Private services

    //region Public services

    @Override
    public byte[] toByteArray()
    {
        byte[] message = this.message.getBytes();
        byte[] toReturn = new byte[4 + message.length]; // byte array de la taille de deux short + taille de la string en byte

        ByteBuffer.wrap(toReturn).put(this.type.getIndice())
                .put(this.etat)
                .put(this.vitRoueGauche)
                .put(this.vitRoueDroite)
                .put(this.vitInstant)
                .putShort(this.distance)
                .put(message);

        return toReturn;
    }

    /**
     * Override toString display all Attributs
     * @return result
     */
    @Override
    public String toString()
    {
        return " -> type : "+this.type
                +"\n etat : "+this.etat
                +"\n vitesse roue gauche : "+this.vitRoueGauche
                +"\n vitesse roue droite : "+this.vitRoueDroite
                +"\n vitesse instant : "+this.vitInstant
                +"\n Distance : "+this.distance
                +"\n Message : "+this.message;
    }

    //endregion Public services
}
