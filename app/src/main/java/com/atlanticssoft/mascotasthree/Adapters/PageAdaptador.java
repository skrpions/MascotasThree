package com.atlanticssoft.mascotasthree.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdaptador extends FragmentPagerAdapter {


    private ArrayList<Fragment> fragments;

    public PageAdaptador(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Retorno la posición de uno de los fragments que esten incrustados en los tabs
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Retorno el tamaño de la lista de fragments
        return fragments.size();
    }
}
