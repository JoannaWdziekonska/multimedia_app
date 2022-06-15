package com.example.yogaapp;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import org.junit.runner.RunWith;
import androidx.test.rule.ActivityTestRule;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<ThirdActivity2> mActivityTestRule = new ActivityTestRule<ThirdActivity2>(ThirdActivity2.class);

    public ThirdActivity2 activity;

    @Before
    public void setUp() throws Exception {
        activity = mActivityTestRule.getActivity();
    }

//    @Test
//    public void testLogin(){
//        getInstrumentation().runOnMainSync(new Runnable() {
//            @Override
//            public void run() {
//                EditText email = loginActivity.findViewById(R.id.email);
//                EditText pass = loginActivity.findViewById(R.id.password);
//                email.setText("email");
//                pass.setText("pass");
//                Button loginBtn = loginActivity.findViewById(R.id.buttonLogin);
//                loginBtn.performClick();
//                assertTrue(1 == 1);
//            }
//        });
//    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    @Test
    public void startTimerTest() {
        activity.startTimer();
        assertTrue(activity.MTimeRunning);
    }

    @Test
    public void stopTimerTest() {
        ThirdActivity2 t2 = new ThirdActivity2();
        t2.stoptimer();
        assertFalse(t2.MTimeRunning);
    }
}