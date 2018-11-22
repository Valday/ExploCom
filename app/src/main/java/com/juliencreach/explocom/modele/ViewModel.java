package com.juliencreach.explocom.modele;

//import android.databinding.BaseObservable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.juliencreach.explocom.BR;

public class ViewModel extends BaseObservable
{
    private String testLabel;

    @Bindable
    public String getTestLabel()
    {
        return this.testLabel;
    }

    public void setTestLabel(String testLabel)
    {
        this.testLabel = testLabel;
        notifyPropertyChanged(BR.testLabel);
    }

    /**
     * Default constructeur not accessible
     */
    private ViewModel()
    {

    }

    /**
     * Instance de classe
     */
    private static ViewModel Instance = new ViewModel();

    /**
     * Assesseur sur l'instance de classe
     * @return instance
     */
    public static ViewModel getInstance()
    {
        return Instance;
    }
}
