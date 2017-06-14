package com.example.android.testapp612;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.testapp612.restPack.Contact;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    DetailPresenter presenter;
    Contact contact;
    DetailContract.View view;
    TextView locationTV;
    TextView nameTV;
    TextView genderTV;
    TextView emailTV;
    String imageURL;
    TextView dobTV;
    TextView registeredTV;
    TextView phoneTV;
    TextView cellTV;
    ImageView image_IV;
    TextView natTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenter = new DetailPresenter(this);
        Bundle b = getIntent().getExtras();
        contact = presenter.setThatList(b);
        //contact = getIntent().getExtras().getParcelable("rList");
        nameTV = (TextView)findViewById(R.id.textView);
        locationTV= (TextView)findViewById(R.id.textView2);
         genderTV = (TextView)findViewById(R.id.textView3);
         emailTV = (TextView)findViewById(R.id.textView4);
        image_IV = (ImageView)findViewById(R.id.image_iv);
         dobTV = (TextView)findViewById(R.id.textView6);
         registeredTV = (TextView)findViewById(R.id.textView7);
         phoneTV = (TextView)findViewById(R.id.textView8);
         cellTV = (TextView)findViewById(R.id.textView9);

         natTV = (TextView)findViewById(R.id.textView11);

    }


    @Override
    protected void onStart() {
        super.onStart();
        ListDatShet(contact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "name: "+contact.getName(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void ListDatShet(Contact contact) {
        nameTV.setText("Name: " + contact.getName());
        locationTV.setText("Location: " + contact.getLocation());
        genderTV.setText("Gender: " + contact.getGender());
        emailTV.setText("Email: " + contact.getEmail());
        imageURL = contact.getImageURL();
        dobTV.setText("Date of Birth: " + contact.getDob());
        registeredTV.setText("Registration Date: " + contact.getRegistered());
        phoneTV.setText("Phone Number: " + contact.getPhone());
        cellTV.setText("Cell Number: " + contact.getCell());

        natTV.setText("Nationality: " + contact.getNat());
        Glide.with(this)
                .load(presenter.glideDude())
                .into(image_IV);
    }


}
