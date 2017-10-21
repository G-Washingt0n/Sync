package com.gnyblecraft.marcul.ideasproject.sideNavigationMenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gnyblecraft.marcul.ideasproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lenovo on 08.10.2017.
 */

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.Holder> {

    private Activity activity;
    private int memberCount=0;
    private int headerCount=0;
    private int textCount=0;
    private int contactCount=0;
    private ArrayList<String> urlList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> positionsList = new ArrayList<>();
    private ArrayList<String> descriptionList = new ArrayList<>();
    private ArrayList<String> headerList = new ArrayList<>();
    private ArrayList<String> largeTextList = new ArrayList<>();
    private ArrayList<String> contactsTypeList = new ArrayList<>();
    private ArrayList<String> contactsInfoList = new ArrayList<>();

    private final int TYPE_HEADER = 0;
    private final int TYPE_TEXT = 1;
    private final int TYPE_MEMBER = 2;
    private final int TYPE_CONTACT = 3;

    public AboutUsAdapter(Activity activity, ArrayList<String> urlList, ArrayList<String> nameList, ArrayList<String> positionsList, ArrayList<String> descriptionList, ArrayList<String> headerList, ArrayList<String> largeTextList, ArrayList<String> contactsTypeList, ArrayList<String> contactsInfoList ) {
        this.activity = activity;
        this.urlList = urlList;
        this.nameList = nameList;
        this.positionsList = positionsList;
        this.descriptionList = descriptionList;
        this.headerList = headerList;
        this.largeTextList = largeTextList;
        this.contactsTypeList = contactsTypeList;
        this.contactsInfoList = contactsInfoList;
    }

    public static class Holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameView;
        TextView positionView;
        TextView descriptionView;
        TextView headerView;
        TextView textView;
        TextView infoView;
        TextView typeView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.aboutUsMemberPhoto);
            nameView = (TextView) itemView.findViewById(R.id.aboutUsMemberName);
            positionView = (TextView) itemView.findViewById(R.id.aboutUsMemberPosition);
            descriptionView = (TextView) itemView.findViewById(R.id.aboutUsMemberDescription);
            headerView = (TextView) itemView.findViewById(R.id.headerAboutUs);
            textView = (TextView ) itemView.findViewById(R.id.largeTextAboutUs);
            typeView = (TextView ) itemView.findViewById(R.id.contactType);
            infoView = (TextView ) itemView.findViewById(R.id.contactsContainer);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        switch (viewType) {
            case TYPE_HEADER:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_header_text_about_us, parent, false);
                break;
            case TYPE_TEXT:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_just_text_about_us, parent, false);
                break;
            case TYPE_MEMBER:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_recycler_view_about_us, parent, false);
                break;
            case TYPE_CONTACT:
                root = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_contacts_about_us, parent, false);
                break;
        }
        return new Holder(root);


    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        int type = getItemViewType(position);

        switch (type) {

            case TYPE_HEADER:
                if(position==0)
                     holder.headerView.setText(headerList.get(position));
                 else if(position==5)
                    holder.headerView.setText(headerList.get(1));
                else if(position==7)
                    holder.headerView.setText(headerList.get(2));
                else
                    holder.headerView.setText(headerList.get(3));
                break;

            case TYPE_TEXT:
                if(position<5)
                holder.textView.setText(largeTextList.get(position-1));
                else
                    holder.textView.setText(largeTextList.get(position-2));
                break;

            case TYPE_MEMBER:
                final String url = urlList.get(position-8);

                Picasso.with(holder.imageView.getContext())
                        .load(url)
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {}
                            @Override
                            public void onError() {}
                        });
                try {
                    holder.nameView.setText(nameList.get(position-8));
                    holder.positionView.setText(positionsList.get(position-8));
                    holder.descriptionView.setText(descriptionList.get(position-8));
                } catch (Exception e) {
                    Toast.makeText(activity, "Unable to download info!", Toast.LENGTH_LONG).show();
                }

                break;

            case TYPE_CONTACT:
                holder.typeView.setText(contactsTypeList.get(position-(urlList.size()+headerList.size()+largeTextList.size())));
                holder.infoView.setText(contactsInfoList.get(position-(urlList.size()+headerList.size()+largeTextList.size())));
                if(position==getItemCount()-1){

                   //holder.infoView.setTextColor(Resources.getSystem().getColor(R.color.blue_color)); //как задать цвет?
                    holder.infoView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://save-ideas.com"));
                            activity.startActivity(webIntent);
                        }
                    });
                }

                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0||position==5||position==7||position==8+urlList.size())
            return TYPE_HEADER;
        if(position ==1 || position == 2 || position == 3 || position == 4 || position == 6 )
            return TYPE_TEXT;
        if(position >=8 && position <8+urlList.size() ) {
            return TYPE_MEMBER;
        }

        return TYPE_CONTACT;
    }

    @Override
    public int getItemCount() {
        return urlList.size()+headerList.size()+largeTextList.size()+contactsTypeList.size();
    }
}
