package com.example.dell.yummy.user.dishes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.yummy.Constants;
import com.example.dell.yummy.R;
import com.example.dell.yummy.user.IUserViewListener;

import java.util.List;

public class UserDishesAdapter extends RecyclerView.Adapter<UserDishesAdapter.DishesViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    IUserViewListener miUserViewListener;
    //we are storing all the products in a list
    private List<DishesDetails> dishesDetailsList;

    //getting the context and product list with constructor
    public UserDishesAdapter(Context mCtx, List<DishesDetails> dishesDetailsList, IUserViewListener miUserViewListener) {
        this.mCtx = mCtx;
        this.dishesDetailsList = dishesDetailsList;
        this.miUserViewListener = miUserViewListener;
    }

    @Override
    public DishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_dishes, null);
        return new DishesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishesViewHolder holder, int position) {
        //getting the product of the specified position
        DishesDetails dishesDetails = dishesDetailsList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(dishesDetails.getTitle());
        holder.textViewShortDesc.setText(dishesDetails.getShortdesc());
        holder.textViewRating.setText(String.valueOf(dishesDetails.getRating()));
        holder.textViewPrice.setText(String.valueOf(dishesDetails.getPrice()));

    }


    @Override
    public int getItemCount() {
        return dishesDetailsList.size();
    }


    class DishesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;


        public DishesViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

            textViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mCtx,"success",Toast.LENGTH_SHORT).show();
                    if(miUserViewListener != null){
                        miUserViewListener.addFragment(Constants.SCREEN_DISHES_DETAILS);
                    }
                }
            });

        }
    }
}