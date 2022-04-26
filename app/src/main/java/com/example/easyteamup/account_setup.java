package com.example.easyteamup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class account_setup extends AppCompatActivity {

    Button submit;
    String email, password, fullname, username;

    EditText usernameText;
    EditText fullnameText;

    ImageView profilePic;

    private static final int SELECT_PICTURE = 1;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);
        Intent intent1 = getIntent();
        email = intent1.getStringExtra("email");
        password = intent1.getStringExtra("password");
        submit = (Button) findViewById(R.id.button4);
        profilePic = (ImageView) findViewById(R.id.imageView14);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_PICTURE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameText = (EditText) findViewById(R.id.editTextTextPersonName2);
                fullnameText = (EditText) findViewById(R.id.editTextTextPersonName);
                fullname = fullnameText.getText().toString();
                username = usernameText.getText().toString();
                DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                connectionHelper.createUser(username,password,fullname,email);
                openAccount();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            profilePic.setImageURI((selectedImage));
        }
    }

    public void openAccount(){
        Intent intent = new Intent(this, my_account.class);
        ((App)getApplication()).setUsername(username);
        ((App)getApplication()).setFullname(fullname);
        ((App)getApplication()).setEmail(email);
        ((App)getApplication()).setProfile_pic(selectedImage);
        startActivity(intent);
    }
}