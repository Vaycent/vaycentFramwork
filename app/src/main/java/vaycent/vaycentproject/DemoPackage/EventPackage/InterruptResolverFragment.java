package vaycent.vaycentproject.DemoPackage.EventPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/4.
 */

public class InterruptResolverFragment extends Fragment {

    private ViewPager interruptViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.interrupt_resolver_fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        interruptViewPager = (ViewPager) view.findViewById(R.id.interrupt_view_pager);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}
