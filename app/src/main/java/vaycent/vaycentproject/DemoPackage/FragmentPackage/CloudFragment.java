package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/20.
 */


public class CloudFragment extends Fragment
{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);



        return view;
    }


}