package vaycent.vaycentproject.DemoPackage.IPC_Package;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vaycent on 2016/10/28.
 */

public class User implements Parcelable {

    private int id;
    private String name;
    private int age;

//    private Book book;

    public User(int id, String name, int age){
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





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeInt(age);
//        out.writeParcelable(book,0);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in){
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
    }
}
