package com.example.android.testapp612;

import android.os.Bundle;

import com.example.android.testapp612.restPack.Contact;

/**
 * Created by Android on 6/14/2017.
 */

public class DetailPresenter implements DetailContract.Presenter {
    DetailContract.View view;
    Contact c;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public String glideDude() {
        return c.getImageURL();
    }

    @Override
    public Contact setThatList(Bundle bundle) {
        c = bundle.getParcelable("rList");
        return c;
    }
}
