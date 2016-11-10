package vaycent.vaycentproject.DemoPackage.EventPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/4.
 */

public class TestEventFragment extends Fragment {

    private RelativeLayout holeLayout;
    private ParentLayout parentLayout;
    private ChildButton childBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.test_event_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        holeLayout = (RelativeLayout)view.findViewById(R.id.hole_layout);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        EventDemo context = (EventDemo)getActivity();
        parentLayout = new ParentLayout(context);
        childBtn = new ChildButton(context);

        parentLayout.addView(childBtn);
        holeLayout.addView(parentLayout);

        childBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mlog.w("This is childBtn onClick");
            }});

        childBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mlog.w("This is childBtn ACTION_DOWN");
                }else if(event.getAction() == MotionEvent.ACTION_HOVER_MOVE){
                    mlog.w("This is childBtn ACTION_HOVER_MOVE");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                    mlog.w("This is childBtn ACTION_UP");
                }
                return false;
            }});
    }

    @Override
    public void onDestroyView(){
        holeLayout.removeAllViews();
        super.onDestroyView();
    }
}
