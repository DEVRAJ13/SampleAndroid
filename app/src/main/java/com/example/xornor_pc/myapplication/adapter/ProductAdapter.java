package com.example.xornor_pc.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.xornor_pc.myapplication.EmptyActivity;
import com.example.xornor_pc.myapplication.HomeActivity;
import com.example.xornor_pc.myapplication.R;
import com.example.xornor_pc.myapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private HomeActivity mCtx;

    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductAdapter(HomeActivity mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from((Context) mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Product product = productList.get(position);
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
//        holder.textViewShortDesc.setText(product.getDescription());
//        holder.textViewRating.setText(String.valueOf(product.getRating()));
//        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
//        holder.imageView.setImageDrawable(mCtx.getResources().product.getUrlToImage());
        Picasso.with(this.mCtx).load(product.getUrlToImage()).fit().into(holder.imageView);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = productList.get(position).getTitle();
                int duration = Toast.LENGTH_SHORT;
                Intent intent = new Intent(mCtx, EmptyActivity.class);
                intent.putExtra("urlString",productList.get(position).getUrl());
                mCtx.startActivity(intent);
                Toast toast = Toast.makeText(mCtx, text, duration);
                toast.show();// your click code here
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        CardView card_view;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
//            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
//            textViewRating = itemView.findViewById(R.id.textViewRating);
//            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            card_view=(CardView) itemView.findViewById(R.id.card_view);
        }
    }
}

