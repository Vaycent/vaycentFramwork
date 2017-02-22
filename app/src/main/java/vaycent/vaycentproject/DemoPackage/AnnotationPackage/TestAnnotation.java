package vaycent.vaycentproject.DemoPackage.AnnotationPackage;

/**
 * Created by vaycent on 2017/2/22.
 */

public class TestAnnotation {

    @AnnotationHelper.BindAddress()
    String address;
    @AnnotationHelper.BindPort()
    private String port;

    private int number;

    public void printInfo() {
        System.out.println("info is " + address + ":" + port);
    }

}
