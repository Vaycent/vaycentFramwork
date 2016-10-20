package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/19.
 */

public class TitleFragment extends Fragment
{

    private ImageButton mLeftMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        mLeftMenu = (ImageButton) view.findViewById(R.id.id_title_left_btn);
        mLeftMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                ContentFragment cf = (ContentFragment)getFragmentManager().findFragmentById(R.id.id_fragment_content);
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft= fm.beginTransaction();
//                ft.replace(R.id.frame_layout,cf);
//                ft.addToBackStack(null);
//                ft.commit();
                FragmentDemo activity = (FragmentDemo) getActivity();
                activity.onBackPressed();

//                Content textView = (TextView)getActivity().findViewById(R.id.id_fragment_content) ;

            }
        });
        return view;
    }
}