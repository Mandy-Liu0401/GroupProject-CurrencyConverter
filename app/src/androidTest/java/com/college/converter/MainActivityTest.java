package com.college.converter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * this method is testing a positive value of 10.
     * @author: Mengying Liu
     */
    @Test
    public void mainActivityTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.entryId));
        appCompatEditText.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.convertButton));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.resultId));
        textView.check(matches(withText("8.00 Euros")));
    }

    // This was done by Isaac
    @Test
    public void mainActivityTest2() {

        ViewInteraction appCompatEditText = onView(withId(R.id.entryId));
        appCompatEditText.perform(replaceText("2000"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.convertButton));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.resultId));
        textView.check(matches(withText("1600.00 Euros")));
    }

    /**
     * this method is testing a dollar value of 0.
     * @author: Mengying Liu
     */
    @Test
    public void zeroDollarTest() {

        ViewInteraction appCompatEditText = onView(withId(R.id.entryId));
        appCompatEditText.perform(replaceText("0"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.convertButton));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.resultId));
        textView.check(matches(withText("0.00 Euros")));
    }

    /**
     * Tests the app's behavior when a negative value is entered in the currency converter.
     * Done by Zimeng Wang
     */
    @Test
    public void negativeValueTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.entryId));
        appCompatEditText.perform(replaceText("-100"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.convertButton));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.resultId));
        textView.check(matches(withText("-80.00 Euros")));
    }

    @Test
    public void mainActivityTest3() {

        ViewInteraction appCompatEditText = onView(withId(R.id.entryId));
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withId(R.id.convertButton));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.resultId));
        textView.check(matches(withText("80.00 Euros")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
