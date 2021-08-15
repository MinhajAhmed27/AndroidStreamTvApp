package com.example.molytv;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {
    @Test
    public void test_isActivityInView() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

//        onView(withId(R.id.mainContainer)).check(matches(isDisplayed());
        onView(withId(R.id.mainContainer)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTabLayout_isVisible() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.tabLayoutTop)).check(matches(isDisplayed()));
    }
}