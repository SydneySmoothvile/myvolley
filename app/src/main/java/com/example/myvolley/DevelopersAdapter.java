package com.example.myvolley;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder>{
    //declare DeveloperLst private member variable
    private List<DeveloperList> developerList;
    //context variable
    private Context mContext;

    //Keys for our intents
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_URL="url";

    public DevelopersAdapter(List<DeveloperList>developerList,Context context){
        this.developerList=developerList;
        this.mContext=context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        //declare the views
        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearlayout;

        //the constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialize view objects
            login = itemView.findViewById(R.id.username);
            avatar_url = itemView.findViewById(R.id.imageView);
            html_url = itemView.findViewById(R.id.html_url);
            linearlayout = itemView.findViewById(R.id.linearLayout);
        }
    }
    @NonNull
    @Override
    public DevelopersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.developer_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopersAdapter.ViewHolder holder, final int position) {
        //create a variable that get the current instance of the developer in the list
        final DeveloperList currentDeveloper=developerList.get(position);
        //populate the text views and image view with data
        holder.login.setText(currentDeveloper.getLogin());
        holder.html_url.setText(currentDeveloper.getHtml_url());
        //Use the library Picasso to load images to prevent crashing..loading images is resource intensive
        Picasso.with(mContext)
                .load(currentDeveloper.getAvatar_url())
                .into(holder.avatar_url);

        //Set on Click Listener to handle click events
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an instance of the developer list and initialize it
                DeveloperList developersList1 = developerList.get(position);
                //Create an intent and specify the target class as Profile Activity
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                //Use intent EXTRA TO Pass data from Main Activity to Profile Activity
                skipIntent.putExtra(KEY_NAME,developersList1.getLogin());
                skipIntent.putExtra(KEY_URL, developersList1.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, developersList1.getAvatar_url());
                v.getContext().startActivity(skipIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }
}
