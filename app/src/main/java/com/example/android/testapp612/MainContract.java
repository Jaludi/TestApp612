package com.example.android.testapp612;

import android.os.Bundle;

import com.example.android.testapp612.restPack.Contact;

import java.util.ArrayList;

/**
 * Created by Android on 6/13/2017.
 */

public interface MainContract {
    interface View{
        void showList();
        void showErrorMessage(String error);
    }
    interface Presenter{
        void retroCall();
        void caller();
        void listBuilder(Contact c);
        Bundle intentThing(int pos);
        ArrayList<Contact> getList();
    }
}
