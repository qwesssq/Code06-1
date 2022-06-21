package com.example.code06.ui.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications person");
    }

    public LiveData<String> getText() {
        return mText;
    }
}