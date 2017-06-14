package com.example.android.testapp612;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.testapp612.restPack.Contact;
import com.example.android.testapp612.restPack.MainHelper;
import com.example.android.testapp612.restPack.RandomAPI;
import com.example.android.testapp612.restPack.Result;
import com.example.android.testapp612.restPack.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 6/13/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    ArrayList<Contact> contactList = new ArrayList<>();
    private static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    private static final String RETROFIT_URL = "https://randomuser.me/";
    MainHelper helper;
    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        helper = new MainHelper();
        this.view = view;
    }

    @Override
    public void retroCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
                if (response.isSuccessful()) {
                    RandomAPI randomAPI = response.body();
                    for (Result result : randomAPI.getResults()) {
                        Log.d(TAG, "onResponse: Name is " + result.getName());

                        String fullName = helper.nameFusion(result.getName().getFirst(), result.getName().getLast());

                        String location = result.getLocation().getStreet() + ", " + result.getLocation().getCity()
                                + ", " + result.getLocation().getState() + ", " + result.getLocation().getPostcode();
                        Contact user = new Contact(location, fullName, result.getGender(), result.getEmail(), result.getLogin().toString(), result.getDob(), result.getRegistered(), result.getPhone(), result.getCell(), result.getId().toString(), result.getNat(), result.getPicture().getLarge());
                       listBuilder(user);


                    }

                    view.showList();
                    Log.d(TAG, "onResponse: arrayCount = " + contactList.size());
                }
            }

            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {
                view.showErrorMessage(t.getMessage());
            }
        });

    }


    @Override
    public void caller() {
        retroCall();
    }

    @Override
    public void listBuilder(Contact u) {
        contactList.add(u);
    }

    @Override
    public Bundle intentThing(int pos) {
        Bundle b = new Bundle();
        Contact c = contactList.get(pos);
        b.putParcelable("rList", c);
        return b;
    }

    @Override
    public ArrayList<Contact> getList() {
        return contactList;
    }
}
