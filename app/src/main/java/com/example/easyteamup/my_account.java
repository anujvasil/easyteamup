package com.example.easyteamup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class my_account extends AppCompatActivity {

    Button logout;
    Button events;
    Button discover;
    Button invite;

    TextView fullnameView, emailView;

    String email, fullname, username;
    Uri profile_pic;
    ImageView pic_view;
    private static final int SELECT_PICTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        email = ((App)getApplication()).getEmail();
        fullname = ((App)getApplication()).getFullname();
        username = ((App)getApplication()).getUsername();
        profile_pic = ((App)getApplication()).getProfile_pic();
        if (email == null || fullname == null || username == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        logout = (Button) findViewById(R.id.button5);
        events = (Button) findViewById(R.id.button8);
        discover = (Button) findViewById(R.id.button7);
        invite = (Button) findViewById(R.id.button6);
        pic_view = (ImageView) findViewById(R.id.imageView4);
        fullnameView = findViewById(R.id.textView);
        emailView = findViewById(R.id.textView2);
        emailView.setText(email);
        fullnameView.setText(fullname);
        pic_view.setImageURI(profile_pic);

        pic_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_PICTURE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();

            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();

            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();

            }
        });

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            profile_pic = data.getData();
            pic_view.setImageURI((profile_pic));
            ((App)getApplication()).setProfile_pic(profile_pic);
        }
    }

    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openEvents(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }

    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }

    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
}