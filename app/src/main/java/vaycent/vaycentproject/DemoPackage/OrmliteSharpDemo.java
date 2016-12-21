package vaycent.vaycentproject.DemoPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import DataBase.Bean.BP;
import DataBase.Bean.BPList;
import DataBase.DB_Helper;
import vaycent.vaycentproject.ApplicationContext;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/20.
 */
public class OrmliteSharpDemo extends AppCompatActivity {

    private ApplicationContext appContext;
    private DB_Helper dbHelper;

    private Button db_add_btn,db_update_btn,db_delete_btn,db_select_btn,
            db_selectall_btn,db_deleteall_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ormlitesharp_demo);

        appContext = ((ApplicationContext) this.getApplication());
        dbHelper = new DB_Helper(this, appContext);

        initLayout();

        if(getIntent().getAction()!=null&&getIntent().getAction().equals("ShortCut To OrmliteSharpDemo"))
            Toast.makeText(this,"This is ShortCut To OrmliteSharpDemo",Toast.LENGTH_SHORT);

    }

    private void initLayout(){

        db_add_btn=(Button)findViewById(R.id.db_add_btn);
        db_update_btn=(Button)findViewById(R.id.db_update_btn);
        db_delete_btn=(Button)findViewById(R.id.db_delete_btn);
        db_select_btn=(Button)findViewById(R.id.db_select_btn);
        db_selectall_btn=(Button)findViewById(R.id.db_selectall_btn);
        db_deleteall_btn=(Button)findViewById(R.id.db_deleteall_btn);

        db_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.db_bp.addBP();
            }
        });
        db_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.db_bp.updateBPById(3);
            }
        });
        db_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.db_bp.deleteBPById(3);
            }
        });
        db_select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BP bpObj=dbHelper.db_bp.selectBPById(1);
                if(bpObj!=null)
                    System.out.println("bpObj:"+bpObj.getSys()+","+bpObj.getDia()+","+bpObj.getHr()+","+bpObj.getRecordtime()+","+bpObj.getId());
            }
        });
        db_selectall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BPList bpList = (BPList) dbHelper.db_bp.selectBPAll();
                for(int i=0;i<bpList.size();i++){
                    BP bpObj=bpList.get(i);
                    if(bpObj!=null)
                        System.out.println("bpObj:"+bpObj.getSys()+","+bpObj.getDia()+","+bpObj.getHr()+","+bpObj.getRecordtime()+","+bpObj.getId());
                }
            }
        });
        db_deleteall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.db_bp.deleteBPTable();
            }
        });
    }
}
