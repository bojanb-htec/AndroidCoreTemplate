package ___PACKAGE_NAME___.presentation.test.actions;

import androidx.test.espresso.ViewAction;

public class ActionsUtils {
	
	private static final int DEFAULT_SLEEP_MILLIS = 300;
	
	public static ViewAction sleep(final long millis) {
		return new SleepAction(millis);
	}
	
	public static ViewAction sleep() {
		return new SleepAction(DEFAULT_SLEEP_MILLIS);
	}
}
