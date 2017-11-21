package com.example.ankith.dailycalorie;

/**
 * Created by ankit on 03-05-2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>  {
    ArrayList<User> friends;
    User user;
    public FriendsAdapter(ArrayList<User> friends) {
        this.friends = friends;
    }
    /**
     *Called till the screen is full of views
     * @param parent the intended parent object (our RecyclerView)
     * @param viewType unused in our function (enables having different kinds of views in the same RecyclerView)
     * @return the new ViewHolder we allocate
     */
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View moviesListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.food_list_item, parent, false);
        return new FriendsAdapter.ViewHolder(moviesListItem);
    }
    /**
     * To update the content of the views.
     * @param holder the ViewHolder that knows about the Views we need to update
     * @param position the index into the array of FoodItems
     */
    @Override
    public void onBindViewHolder(FriendsAdapter.ViewHolder holder, int position) {
        final User friend= friends.get(position);
        holder.titleView.setText(friend.getEmail());
        holder.OverviewView.setText(friend.getDescription());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    /**
     * Recycler view must know the size of the array to know when to stop
     * @return number of FoodItems in the array.
     */
    @Override
    public int getItemCount() {
        return friends.size();
    }
    /**
     * A ViewHolder class for the adapter that "reserves" the references to the subviews, so we don't need to find them each time.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView OverviewView;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.titleTextView);
            OverviewView = (TextView) itemView.findViewById(R.id.OverviewTextView);
        }
    }
}

