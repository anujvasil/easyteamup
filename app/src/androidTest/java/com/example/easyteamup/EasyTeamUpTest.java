package com.example.easyteamup;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EasyTeamUpTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void login() {
        onView(withId(R.id.editTextTextEmailAddress))
                .perform(replaceText("jsmith@gmail.com"));
        onView(withId(R.id.editTextTextPassword))
                .perform(replaceText("password123"));
        onView(withId(R.id.button))
                .perform(click());
    }

    @Test
    public void testInvalidLogin() {
        onView(withId(R.id.editTextTextEmailAddress))
                .perform(replaceText("jsmith@gmail.com"));
        onView(withId(R.id.editTextTextPassword))
                .perform(replaceText("wrong-password"));
        onView(withId(R.id.button))
                .perform(click());
        onView(withId(R.id.textView9))
                .check(matches(withText("Invalid Login")));

    }

    @Test
    public void testValidLogin() {
        login();
        onView(withId(R.id.textView2))
                .check(matches(withText("jsmith@gmail.com")));

    }
}
