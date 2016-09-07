package BaseClass;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivityStackControlUtil {
	public static List<Activity> activityList = new ArrayList<Activity>();

	public static void push(Activity activity) {
		activityList.add(activity);
	}

	public static void pop() {
		Activity activity = new Activity();
		if (activityList.size() > 0) {
			
//			Activity tempActivity=activityList.get(activityList.size() - 1);
//			boolean check=tempActivity instanceof MainActivity;
//			
//			if (activityList.size() == 1||check) { // first activity pop
//				finishProgram();
//			} else { // other activity pop
				activity = activityList.get(activityList.size() - 1);
				activityList.remove(activityList.size() - 1);
				activity.finish();
//			}
		}
	}

	public static void stackToHomePage() { // use in App Home page button
		if (activityList.size() > 1) {
			for (int i = 1; i < activityList.size(); i++) {
				Activity activity = activityList.get(i);
				activity.finish();
			}
		}
	}

	public static void finishProgram() {
		if (activityList.size() > 0) {
			System.out.println("123456activityList.size():"+activityList.size());
			for (int i = 0; i < activityList.size(); i++) {
				Activity activity = activityList.get(i);
				activity.finish();
			}
			activityList.clear();
			android.os.Process.killProcess(android.os.Process.myPid()); //kill app
		}
	}

	public static Activity getTopActivity() {
		Activity activity = null;
		if (activityList.size() > 0) {
			activity = activityList.get(activityList.size() - 1);
		}
		return activity;
	}

	public static int getTopActivityNum() {
		int num = 0;
		if (activityList.size() > 0) {
			num = activityList.size() - 1;
		}
		return num;
	}

	public static void finishSpecifyActivity(Class cls) {
		if (activityList.size() > 0) {
			for (int i = 0; i < activityList.size(); i++) {
				Activity activity = activityList.get(i);
				if (cls.isInstance(activity)) {
					activityList.remove(activity);
					activity.finish();
					return;
				}
			}
		}
	}
	
	
	public static void cleanActivityStack(){
		System.out.println("********** clean the activity stack **********");
		System.out.println("********** stack size is:"+activityList.size()+" **********");

		for(int i=0;i<activityList.size()-1;i++){
			Activity activity = activityList.get(i);
			activityList.remove(i);
			activity.finish();
//			pop();
		}
//		System.out.println("stack:"+activityList);
//
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}