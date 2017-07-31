package vaycent.service.router.router;

import android.content.Intent;

import vaycent.service.router.router.annotations.ClassName;
import vaycent.service.router.router.annotations.IntentFlags;

public class XRules {

    public interface ICSMBPActivity {
        /**
         * App 主页
         */
        @ClassName(XConst.CSMBP_ACTIVITY)
        @IntentFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
        IntentWrapper toCSMBPActivity();
    }

    /* ***********************This is vaycent's activity********************** */
    public interface VaycentDataBindingDemo{
//        @IntentFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
//        IntentWrapper toNewBookingActivity(@Key("DepPort") String depPort,
//                                           @Key("ArrPort") String arrPort,
//                                           @Key("FlightDate") String flightDate,
//                                           @Key("FlightBackDate") String flightBackDate,
//                                           @Key("AdultNum") @DefaultInt(1) Integer adultNum,
//                                           @Key("ChildNum") @DefaultInt(0) Integer childNum);
        @ClassName(XConst.VAYCENT_DATABINDING_DEMO)
        IntentWrapper toVaycentDataBindingDemo();
    }
}
