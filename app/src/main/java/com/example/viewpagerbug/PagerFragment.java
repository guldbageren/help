package com.example.viewpagerbug;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PagerFragment extends Fragment {

    ViewPager thePager;
    PagerTitleStrip theStrip;
    ArrayList<PagerItem> mItems;
    ArrayList<PagerItem> mRemovedItems;

    CustomPagerAdaptor adaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pager, container, false);
        thePager = v.findViewById(R.id.thePager);
        theStrip = v.findViewById(R.id.theStrip);

        mItems = new ArrayList<>();
        mRemovedItems = new ArrayList<>();

        PagerItem item1 = new PagerItem();
        item1.id = 1;
        item1.text = "Section1";

        PagerItem item2 = new PagerItem();
        item2.id = 2;
        item2.text = "Section2";

        PagerItem item3 = new PagerItem();
        item3.id = 3;
        item3.text = "Section3";

        mItems.add(item1);
        mItems.add(item2);
        mItems.add(item3);
        adaptor = new CustomPagerAdaptor(getChildFragmentManager());
        thePager.setAdapter(adaptor);
        setHasOptionsMenu(true);
        return  v;
    }

    boolean mSectionTwoHidden;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings)
        {
            if (!mSectionTwoHidden)
            {
                mRemovedItems.add(mItems.remove(1));
                mSectionTwoHidden = true;
            }else
            {
                mItems.add(1,mItems.remove(0));
                mSectionTwoHidden = false;
            }
            thePager.getAdapter().notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       inflater.inflate(R.menu.pager_menu, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }


    public class CustomPagerAdaptor extends FragmentStatePagerAdapter
    {

        public CustomPagerAdaptor(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mItems.get(position).text;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PagerItemFragment.NewInstance(mItems.get(mSectionTwoHidden ? 1 : position));
        }

        @Override
        public int getCount() {
            return mItems.size();
        }
    }

    public class PagerItem
    {
        int id;
        String text;
    }
}