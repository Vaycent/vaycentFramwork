package vaycent.vaycentproject.DemoPackage.DataBindingPackage;

/**
 * Created by Vaycent on 2017/1/17.
 */
public class User {
    private final String firstName;
    private final String lastName;
    private int mAge;

    public User(String firstName, String lastName, int mAge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mAge = mAge;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return mAge;
    }
}