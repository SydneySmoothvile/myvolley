package com.example.myvolley;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initialize the view using the ID's you set in profile.xml
        ImageView profileImageView = findViewById(R.id.profileImageView);
        TextView userNameTextView = findViewById(R.id.usernameTextView);
        ImageButton shareProfile = findViewById(R.id.shareProfile);
        TextView developerUrl = findViewById(R.id.developerUrl);

        //Getting the intents send from main activity
        Intent intent = getIntent();
        final String userName = intent.getStringExtra(DevelopersAdapter.KEY_NAME);
        String image = intent.getStringExtra(DevelopersAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(DevelopersAdapter.KEY_URL);
        //Setting the views
        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        userNameTextView.setText(userName);
        developerUrl.setText(profileUrl);

        //set on click listener to developUrl so as to open the developer link using implicit intent
        developerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //set on click listener to image button shareProfile and implement implicit intent for sharing developers profile
        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer"
                        +userName+"," +profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
            }
        });
    }
}