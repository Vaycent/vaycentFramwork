package vaycent.vaycentproject.DemoPackage.RecycleViewPackage;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/11.
 */

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.recycleview_list_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
        context=view.getContext();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        List<String> mDatas = initData();

        //Setup LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),4));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        //Setup adapter
        HomeAdapter mAdapter = new HomeAdapter(this.getActivity(),mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //Setup divider
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
//                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this.getActivity()));
    }

    private List<String> initData() {
        List<String> mDatas;
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        return mDatas;
    }




}
