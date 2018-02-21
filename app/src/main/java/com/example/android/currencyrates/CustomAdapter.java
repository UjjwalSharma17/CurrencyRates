package com.example.android.currencyrates;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujjwal on 20-02-2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private int mNumberItems;
    private ArrayList<String> mCurrencyNameList;
    private ArrayList<String> mValueList;

    public CustomAdapter(int numItems, ArrayList<String> currencyNameList, ArrayList<String> valueList){
        mNumberItems = numItems;
        mCurrencyNameList = currencyNameList;
        mValueList = valueList;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView mLeftTextView;
        TextView mRightTextView;

        public CustomViewHolder(View view){
            super(view);
            mLeftTextView = (TextView) view.findViewById(R.id.left_tv);
            mRightTextView = (TextView) view.findViewById(R.id.right_tv);
        }

        void bind(int position){

            String str1 = mCurrencyNameList.get(position);
            String str2 = mValueList.get(position);

            mLeftTextView.setText(str1);
            mRightTextView.setText(str2);

        }

    }

}
