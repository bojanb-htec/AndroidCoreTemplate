package ___PACKAGE_NAME___.presentation.test.actions;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class SleepAction implements ViewAction {
	
	private final long sleepMillis;
	
	public SleepAction(long sleepMillis) {
		this.sleepMillis = sleepMillis;
	}
	
	@Override
	public Matcher<View> getConstraints() {
		return isRoot();
	}
	
	@Override
	public String getDescription() {
		return "Wait for at least " + sleepMillis + " millis";
	}
	
	@Override
	public void perform(final UiController uiController, final View view) {
		uiController.loopMainThreadUntilIdle();
		uiController.loopMainThreadForAtLeast(sleepMillis);
	}
}
