package BaseClass;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BaseApplicationContext extends Application {
	private static SharedPreferences myPrefs;
	public static BaseActivityStackControlUtil baseStack;
	private String loginid = "";
	private String sessionid = "";

	@Override
	public void onCreate() {
		super.onCreate();
		myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		baseStack = new BaseActivityStackControlUtil();
		// mHealthDB = new MhealthDB(this);

	}

	public void setLoginID(String loginid) {
		myPrefs.edit().putString("LOGIN_ID", loginid).commit();
	}

	public String getLoginID() {
		return myPrefs.getString("LOGIN_ID", "");
	}

	public void setSessionID(String sessionid) {
		myPrefs.edit().putString("SESSION_ID", sessionid).commit();
	}

	public String getSessionID() {
		return myPrefs.getString("SESSION_ID", "");
	}

	public void setLastLoginID(String loginid) {
		myPrefs.edit().putString("LAST_LOGIN_ID", loginid).commit();
	}

	public String getLastLoginID() {
		return myPrefs.getString("LAST_LOGIN_ID", "");
	}

	public void setLanguageValue(String language) {
		myPrefs.edit().putString("APP_LANGUAGE", language).commit();
	}

	public String getLanguageValue() {
		return myPrefs.getString("APP_LANGUAGE", "");
	}


	//********* Screen value*********
	public float getScreenWidthDP() {
		return myPrefs.getFloat("SCREENWIDTHDP", 1.0f);
	}
	public void setScreenWidthDP(Float widthVal) {
		myPrefs.edit().putFloat("SCREENWIDTHDP", widthVal).commit();
	}

	public float getScreenDensity() {
		return myPrefs.getFloat("SCREENDENSITY", 1.0f);
	}
	public void setScreenDensity(Float density) {
		myPrefs.edit().putFloat("SCREENDENSITY", density).commit();
	}

	public float getScreenScale() {
		return myPrefs.getFloat("SCREENSCALE", 1.0f);
	}
	public void setScreenScale(Float scale) {
		myPrefs.edit().putFloat("SCREENSCALE", scale).commit();
	}
	//************************************






}
