package ___PACKAGE_NAME___.presentation.test.matchers;

import androidx.annotation.IdRes;

public class MatcherUtils {
	public static RecyclerViewMatcher withRecyclerView(@IdRes int recyclerViewId) {
		return new RecyclerViewMatcher(recyclerViewId);
	}
}
