package com.example.android.testapp612;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements RecycleviewAdapater.ItemClickListener, MainContract.View{

    ArrayList<Contact> contactList;
    MainPresenter presenter = new MainPresenter(this);
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://randomuser.me/api";
    private static final String RETROFIT_URL = "https://randomuser.me/";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        presenter.caller();
    }

    @Override
    protected void onStart() {
        super.onStart();

        contactList = presenter.getList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //contactList = presenter.getList();



        //showList();


    }

////    private void doRetrofitNetworkCall() {
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl(RETROFIT_URL)
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////        RetrofitService service = retrofit.create(RetrofitService.class);
////        Call<RandomAPI> call = service.getRandomUser();
////        call.enqueue(new Callback<RandomAPI>() {
////            @Override
////            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
////                if(response.isSuccessful()){
////                    RandomAPI randomAPI =  response.body();
////                    for(Result result:randomAPI.getResults()){
////                        Log.d(TAG, "onResponse: Name is " + result.getName());
////
////                        String fullName = nameFusion(result.getName().getFirst(), result.getName().getLast());
////
////                        String location = result.getLocation().getStreet()+ ", " + result.getLocation().getCity()
////                                + ", " + result.getLocation().getState() + ", " + result.getLocation().getPostcode();
////                        Contact user = new Contact(location,fullName,result.getGender(),result.getEmail(),result.getLogin().toString(),result.getDob(),result.getRegistered(),result.getPhone(),result.getCell(),result.getId().toString(),result.getNat(),result.getPicture().getLarge());
////                        contactList.add(user);
////
////
////                    }
////                    adapater.notifyDataSetChanged();
////
////                    Log.d(TAG, "onResponse: arrayCount = " + contactList.size());
////                }
////            }
//            @Override
//            public void onFailure(Call<RandomAPI> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "failed!!", Toast.LENGTH_SHORT).show();
//                theShit();
//            }
//        });
//
//}
//    public  String nameFusion(String first, String last){
//        StringBuilder builder = new StringBuilder();
//
//        builder.append(first);
//        builder.append(" ");
//        builder.append(last);
//        return builder.toString();
//
//    }
    @Override
    public void onItemClick(View view, int position) {
        //Log.i("TAG", "You clicked number " + adapater.getItem(position) + ", which is at cell position " + position);
        Toast.makeText(this, "touched!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        //intent.putParcelableArrayListExtra("rList", contactList);
        Bundle b = presenter.intentThing(position);
        //Contact c = contactList.get(position);
        //b.putParcelable("rList", c);
        intent.putExtras(b);
        startActivity(intent);
    }
//    public void theShit(){
//        //for(int i = 0; i<20;i++)
//        doRetrofitNetworkCall();
//
//    }

    @Override
    public void showList() {
        RecycleviewAdapater adapater;
        adapater= new RecycleviewAdapater(this, contactList);
        adapater.setClickListener(this);
        recyclerView.setAdapter(adapater);
        //adapater.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String error) {
        Log.d(TAG, "showErrorMessage: "+ error);
    }


//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.btn_id:
//                theShit();
//        }
//    }
}

