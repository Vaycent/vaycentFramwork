package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/19.
 */

public class ContentFragment extends Fragment
{

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mlog.e("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mlog.e("onCreateView");
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        mlog.e("onViewCreated");
        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        mlog.e("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        mlog.e("onStart");
        super.onStart();
    }

    @Override
    public void onResume(){
        mlog.e("onResume");
        super.onResume();
    }

    @Override
    public void onPause(){
        mlog.e("onPause");
        super.onPause();
    }

    @Override
    public void onStop(){
        mlog.e("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        mlog.e("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        mlog.e("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach(){
        mlog.e("onDetach");
        super.onDetach();
    }

}