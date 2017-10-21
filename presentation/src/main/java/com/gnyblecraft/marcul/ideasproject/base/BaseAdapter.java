package com.gnyblecraft.marcul.ideasproject.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 08.10.2017.
 */

public abstract class BaseAdapter
        <Model,
        ViewModel extends BaseItemViewModel<Model>>
        extends RecyclerView.Adapter<BaseItemViewHolder<Model, ViewModel, ?>> {

    private List<Model> items = new ArrayList<>();

    public BaseAdapter() {}

    public void setItems(List<Model> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder<Model, ViewModel, ?> holder, int position) {
        Model item = items.get(position);
        holder.bindTo(item, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}

