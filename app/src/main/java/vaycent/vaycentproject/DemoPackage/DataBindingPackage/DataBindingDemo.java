package vaycent.vaycentproject.DemoPackage.DataBindingPackage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vaycent.vaycentproject.R;
import vaycent.vaycentproject.databinding.ActivityDataBindingDemoBinding;

public class DataBindingDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitDataBinding();
    }

    private void InitDataBinding(){
        ActivityDataBindingDemoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_demo);
        User user = new User("John","Smith",22);
        EventHandler handler = new EventHandler(this);
        binding.setUser(user);
        binding.setHandler(handler);
    }
}
