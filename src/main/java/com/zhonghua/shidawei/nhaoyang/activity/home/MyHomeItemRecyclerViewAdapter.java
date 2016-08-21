package com.zhonghua.shidawei.nhaoyang.activity.home;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.utils.Check;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhonghua.shidawei.nhaoyang.activity.home.HomeItemFragment.OnListFragmentInteractionListener;
import com.zhonghua.shidawei.nhaoyang.R;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;

import java.util.List;


public class MyHomeItemRecyclerViewAdapter extends RecyclerView.Adapter<MyHomeItemRecyclerViewAdapter.ViewHolder> {

    private List<ArtModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyHomeItemRecyclerViewAdapter(List<ArtModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void replaceData(List<ArtModel> artModels) {
        setList(artModels);
        notifyDataSetChanged();
    }

    private void setList(List<ArtModel> artModels) {
        mValues = Check.checkNotNull(artModels);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_homeitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });


        holder.list_home_content.setText(mValues.get(position).getContent());

        holder.list_home_time.setText(UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,mValues.get(position).getTime()));
        holder.list_home_title.setText(mValues.get(position).getTitle());
        UserModel userModel = mValues.get(position).getUser();
        if(userModel!=null){
            Uri uri = Uri.parse(userModel.getUserimage());
            holder.list_home_image.setImageURI(uri);
            holder.list_home_name.setText(userModel.getUsername());
        }
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ArtModel mItem;

        public final TextView list_home_name;
        public final SimpleDraweeView list_home_image;
        public final TextView list_home_title;
        public final TextView list_home_content;
        public final TextView list_home_time;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            list_home_name = (TextView) view.findViewById(R.id.list_home_name);
            list_home_image = (SimpleDraweeView) view.findViewById(R.id.list_home_image);
            list_home_title = (TextView) view.findViewById(R.id.list_home_title);
            list_home_content = (TextView) view.findViewById(R.id.list_home_content);
            list_home_time = (TextView) view.findViewById(R.id.list_home_time);
        }

    }
}
