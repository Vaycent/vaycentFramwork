package vaycent.vaycentproject.DemoPackage.IPC_Package;

import java.io.Serializable;

/**
 * Created by Vaycent on 2016/10/27.
 */

public class Person implements Serializable{

    private int id;
    private String name;
    private int age;

    private static final long serialVersionUID=01L;

    public Person(int id, String name, int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return this.age;
    }
}
