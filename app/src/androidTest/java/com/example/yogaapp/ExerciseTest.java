package com.example.yogaapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import com.example.yogaapp.util.EspressoIdlingResource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseTest {


        @Rule
        public ActivityTestRule<SecondActivity> mActivityTestRule = new ActivityTestRule<>(SecondActivity.class);

        @Before
        public void setUp(){
            IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

            ViewInteraction linearLayout2 = onView(
                    allOf(withId(R.id.boat_pose),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")),
                                            0),
                                    0)));
            linearLayout2.perform(scrollTo(), click());
        }

        @Test
        public void startStopExerciseTest() {

            ViewInteraction button2= onView(
                    allOf(withId(R.id.startbutton),
                            isDisplayed()));
            button2.check(matches(isDisplayed()));

            ViewInteraction timeBtn= onView(
                    allOf(withId(R.id.time),
                            isDisplayed()));
            timeBtn.check(matches(isDisplayed()));

            timeBtn.check(matches(withText("01:00")));


            button2.perform(scrollTo(), click());


            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            button2.perform(scrollTo(), click());
            timeBtn.check(matches(withText("00:55")));

            button2.perform(scrollTo(), click());


            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            button2.perform(scrollTo(), click());
            timeBtn.check(matches(withText("00:50")));

            button2.perform(scrollTo(), click());

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            button2.perform(scrollTo(), click());
            timeBtn.check(matches(withText("00:41")));

        }

    @Test
    public void instructionViewTest() {

        ViewInteraction button2 = onView(
                allOf(withId(R.id.instruction),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
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
