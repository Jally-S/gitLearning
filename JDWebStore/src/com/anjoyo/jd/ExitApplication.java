package com.anjoyo.jd;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();
	private List<Activity> activityList2 = new LinkedList<Activity>();

	private static ExitApplication instance;

	private ExitApplication() {
	}

	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;

	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	public void addActivity2(Activity activity) {
		activityList2.add(activity);
	}


	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);

	}
	public void exit2() {
		
		for (Activity activity : activityList2) {
			activity.finish();
		}
		
		
	}
}
