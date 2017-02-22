package vaycent.vaycentproject.DemoPackage.AnnotationPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import DataBase.Bean.BP;
import vaycent.vaycentproject.R;

/**
 * Created by vaycent on 2017/2/22.
 */

public class AnnotationDemo extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_demo);


        try {
            Class c = Class.forName("DataBase.Bean.BP");
            StringBuffer sb = new StringBuffer();
            sb.append("{\n");

            //实例化一个TestClass对象
            BP bpC= (BP) c.newInstance();


            Field[] fields = c.getDeclaredFields();
            for(Field mField : fields){
                if(mField.isAnnotationPresent(AnnotationHelper.BindPort.class)){
                    AnnotationHelper.BindPort port = mField.getAnnotation(AnnotationHelper.BindPort.class);
                    mField.setAccessible(true);
                    mField.set(bpC,port.value());
                }
                if (mField.isAnnotationPresent(AnnotationHelper.BindAddress.class)) {
                    AnnotationHelper.BindAddress address = mField.getAnnotation(AnnotationHelper.BindAddress.class);
                    mField.setAccessible(true);
                    mField.set(bpC,address.value());
                }


                sb.append("\t");// 空格
                sb.append(Modifier.toString(mField.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
                sb.append(mField.getType().getSimpleName() + " ");// 属性的类型的名字
                sb.append(mField.getName() + ";\n");// 属性的名字+回车
            }

            Constructor[] constructors = c.getConstructors();
            for(Constructor constructor : constructors){
                sb.append("\t");
                sb.append(Modifier.toString(constructor.getModifiers())+" ");
                sb.append(constructor.getName()+"\n");
            }
            sb.append("\n\n");


            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                sb.append("\t");
                sb.append(Modifier.toString(method.getModifiers())+" ");
                sb.append(method.getTypeParameters()+" ");

                sb.append(method.getName()+"(");
                sb.append(method.getParameterTypes()+")\n");



            }


            sb.append("}\n");
            System.out.println(sb);



            bpC.printInfo();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
