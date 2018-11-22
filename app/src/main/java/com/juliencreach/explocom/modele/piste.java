package com.juliencreach.explocom.modele;

public class piste
{
    //region Private Attributs

    private int id;
    private String nom;
    private String description;

    //endregion Private Attributs

    //region Public Attributs

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    //endregion Public Attributs

    //region Constructors

    public piste(int id, String nom, String description)
    {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    //endregion Constructors

    // region Private services
    //endregion Private services

    //region Public services

    @Override
    public String toString()
    {
        return " -> Piste, ID : "+this.id
                +"\n Nom : "+this.nom
                +"\n Description : "+this.description;
    }

    //endregion Public services
}
