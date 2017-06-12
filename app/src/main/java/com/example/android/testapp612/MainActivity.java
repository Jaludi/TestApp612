package com.example.android.testapp612;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.testapp612.restPack.Contact;
import com.example.android.testapp612.restPack.RandomAPI;
import com.example.android.testapp612.restPack.Result;
import com.example.android.testapp612.restPack.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String RETROFIT_URL = "https://randomuser.me/";
    ArrayList<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void doRetrofitNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
                if(response.isSuccessful()){
                    RandomAPI randomAPI =  response.body();
                    for(Result result:randomAPI.getResults()){
                        Log.d(TAG, "onResponse: Name is " + result.getName());

                        String fullName = nameFusion(result.getName().getFirst(), result.getName().getLast());

                        String location = result.getLocation().getStreet()+ ", " + result.getLocation().getCity()
                                + ", " + result.getLocation().getState() + ", " + result.getLocation().getPostcode();
                        Contact user = new Contact(location,fullName,result.getGender(),result.getEmail(),result.getLogin().toString(),result.getDob(),result.getRegistered(),result.getPhone(),result.getCell(),result.getId().toString(),result.getNat());
                        contactList.add(user);

                    }

                    Log.d(TAG, "onResponse: arrayCount = " + contactList.size());
                }
            }
            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {

            }
        });

}
    public  String nameFusion(String first, String last){
        StringBuilder builder = new StringBuilder();

        builder.append(first);
        builder.append(" ");
        builder.append(last);
        return builder.toString();

    }
}

