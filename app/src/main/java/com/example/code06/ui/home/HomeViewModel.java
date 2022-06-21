package com.example.code06.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.code06.Home_Recycleview.Mydynamic;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private  MutableLiveData<ArrayList<Mydynamic>> mlivedata ;
    public  ArrayList<Mydynamic> list;

    public HomeViewModel(){
        mlivedata = new MutableLiveData<>();
        init();
    }
    MutableLiveData<ArrayList<Mydynamic>> getMutablLiveData(){
        return mlivedata;
    }
    public void init(){
        populateList();
        mlivedata.setValue(list);
    }
    public void populateList(){

    }

}