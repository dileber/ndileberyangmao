package com.zhonghua.shidawei.nhaoyang.activity.consult;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhonghua.shidawei.nhaoyang.R;

import java.util.List;

/**
 * Created by shidawei on 16/8/18.
 */
public class ConsultAdaper extends RecyclerView.Adapter<ConsultAdaper.ConsultHodler>{

    private final List<String> mValues;

    public ConsultAdaper(List<String> items) {
        mValues = items;
    }

    @Override
    public ConsultHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_consult, parent, false);
        return new ConsultHodler(view);
    }

    @Override
    public void onBindViewHolder(ConsultHodler holder, int position) {
        holder.item = mValues.get(position);
        holder.icon.setText( mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ConsultHodler extends RecyclerView.ViewHolder{
        public final View mView;

        private String item;
        private final TextView icon;

        public ConsultHodler(View itemView) {
            super(itemView);
            mView = itemView;
            icon = (TextView) itemView.findViewById(R.id.icon);
        }
    }

}
