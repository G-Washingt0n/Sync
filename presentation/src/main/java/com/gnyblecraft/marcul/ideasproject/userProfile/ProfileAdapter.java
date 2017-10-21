package com.gnyblecraft.marcul.ideasproject.userProfile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 12.10.2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.Holder> {

    private ArrayList<String> items = new ArrayList<>();

    public ProfileAdapter(ArrayList<String> userInfo) {
        this.items = userInfo;
        Log.e("Array size: ", String.valueOf(userInfo.size()));
    }


    public static class Holder extends RecyclerView.ViewHolder {

        TextView textView;


        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recyclerProfile);
        }


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_profile,parent,false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) { //позиция это строчка для отрисовки

        holder.textView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}