package com.example.yogaapp;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

import static java.util.regex.Pattern.matches;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.TestCase;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UnitTests {

    @Rule
    public ActivityTestRule mThirdActivity2Rule = new ActivityTestRule<>(
            ThirdActivity2.class);

    @Test
    public void editTextTest() {
        onView(withId(R.id.startyoga1)).check((ViewAssertion)withText(("Get started")));
    }

    @Test
    public void does_not_break() {
        onView(withId(R.id.startyoga1)).perform(click());
        onView(withText("ThirdActivity")).check(ViewAssertions.matches(isDisplayed()));
    }


}