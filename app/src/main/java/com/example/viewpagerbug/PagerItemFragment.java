package com.example.viewpagerbug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PagerItemFragment extends Fragment {

    private TextView mTextView;
    private PagerFragment.PagerItem mItem;
    public static PagerItemFragment NewInstance(PagerFragment.PagerItem item)
    {
       PagerItemFragment fragment = new PagerItemFragment();
       fragment.mItem = item;
       return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pager_item, container, false);
        mTextView = v.findViewById(R.id.itemText);
        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextView.setText(mItem.text);
    }
}