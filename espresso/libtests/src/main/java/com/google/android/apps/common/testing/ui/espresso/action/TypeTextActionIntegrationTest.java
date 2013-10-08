package com.google.android.apps.common.testing.ui.espresso.action;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.pressImeActionButton;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.scrollTo;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withParent;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import com.google.android.apps.common.testing.ui.testapp.R;
import com.google.android.apps.common.testing.ui.testapp.SendActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * {@link TypeTextAction} integration tests.
 */
@LargeTest
public class TypeTextActionIntegrationTest extends ActivityInstrumentationTestCase2<SendActivity> {
  @SuppressWarnings("deprecation")
  public TypeTextActionIntegrationTest() {
    // Supporting froyo.
    super("com.google.android.apps.common.testing.ui.testapp", SendActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  public void testTypeTextActionPerform() {
    onView(withId(is(R.id.sendDataToCallEditText))).perform(typeText("Hello!"));
  }

  @SuppressWarnings("unchecked")
  public void testTypeTextActionPerformWithEnter() {
    onView(withId(R.id.enterDataEditText)).perform(typeText("Hello World!\n"));
    onView(allOf(withId(R.id.enterDataResponseText), withText("Hello World!")))
        .check(matches(isDisplayed()));
  }

  @SuppressWarnings("unchecked")
  public void testTypeTextInDelegatedEditText() {
    String toType = "honeybadger doesn't care";
    onView(allOf(withParent(withId(R.id.delegatingEditText)), withId(R.id.DelegateEditText)))
        .perform(scrollTo(), typeText(toType), pressImeActionButton());
    onView(withId(R.id.EditTextMessage))
      .perform(scrollTo())
      .check(matches(withText(containsString(toType))));
  }
}
