package com.example.yogaapp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignUpUITest {

    @Rule
    public ActivityTestRule<SignUp> mActivityTestRule = new ActivityTestRule<>(SignUp.class);

    @Test
    public void signUpUITest() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.firstname),
                        withParent(withParent(withId(R.id.textInputLayoutFirstname))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.lastname),
                        withParent(withParent(withId(R.id.textInputLayoutLastname))),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.email),
                        withParent(withParent(withId(R.id.textInputLayoutEmail))),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.username),
                        withParent(withParent(withId(R.id.textInputLayoutUsername))),
                        isDisplayed()));
        editText4.check(matches(isDisplayed()));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.password),
                        withParent(withParent(withId(R.id.textInputLayoutPassword))),
                        isDisplayed()));
        editText5.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.buttonSignUp),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));


    }
}
