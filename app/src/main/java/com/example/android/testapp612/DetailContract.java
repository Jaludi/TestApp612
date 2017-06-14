package com.example.android.testapp612;

import android.os.Bundle;

import com.example.android.testapp612.restPack.Contact;

import java.util.ArrayList;

/**
 * Created by Android on 6/13/2017.
 */

public interface DetailContract {
    interface View{
        void ListDatShet(Contact contact);
    }
    interface Presenter{
        String glideDude();
        Contact setThatList(Bundle bundle);

    }
}
