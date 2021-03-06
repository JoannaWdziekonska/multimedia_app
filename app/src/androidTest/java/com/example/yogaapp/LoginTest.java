package com.example.yogaapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.yogaapp.util.EspressoIdlingResource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<SignUp> mActivityTestRule = new ActivityTestRule(SignUp.class);


    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }


    @Test
    public void loginTest() {

        ViewInteraction textView = onView(
                allOf(withId(R.id.loginText),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.loginText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                8),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutUsername),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        textInputEditText2.perform(replaceText("1"), closeSoftKeyboard());


        ViewInteraction editText3 = onView(
                allOf(withId(R.id.username),
                        withParent(withParent(withId(R.id.textInputLayoutUsername))),
                        isDisplayed()));
        editText3.check(matches(withText("1")));
        onView(withId(R.id.username)).check(matches(isFocusable()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.password),
                        withParent(withParent(withId(R.id.textInputLayoutPassword))),
                        isDisplayed()));
        editText4.check(matches(withText("1")));
        onView(withId(R.id.password)).check(matches(isFocusable()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonLogin),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());
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
