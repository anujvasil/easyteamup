package com.example.easyteamup;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.PositionAssertions.isBelow;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

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

    @Test
    public void testLogout() {
        login();
        onView(withId(R.id.button5))
                .perform(click());
        onView(withId(R.id.button))
                .check(matches(isClickable()));
    }

    @Test
    public void testCreateAccount() {
        // generate random 10 length string for email and name
        // help from: https://www.baeldung.com/java-random-string
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        onView(withId(R.id.editTextTextEmailAddress))
                .perform(replaceText(generatedString + "@gmail.com"));
        onView(withId(R.id.editTextTextPassword))
                .perform(replaceText("test-password"));
        onView(withId(R.id.button2))
                .perform(click());
        onView(withId(R.id.editTextTextPersonName))
                .perform(replaceText("Name " + generatedString));
        onView(withId(R.id.editTextTextPersonName2))
                .perform(replaceText(generatedString));
        onView(withId(R.id.button4))
                .perform(click());
        onView(withId(R.id.textView))
                .check(matches(withText("Name " + generatedString)));
    }

    @Test
    public void testEmptyPasswordCreateAccount() {
        // generate random 10 length string for email and name
        // help from: https://www.baeldung.com/java-random-string
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        onView(withId(R.id.editTextTextEmailAddress))
                .perform(replaceText(generatedString + "@gmail.com"));
        onView(withId(R.id.editTextTextPassword))
                .perform(replaceText(""));
        onView(withId(R.id.button2))
                .perform(click());
        onView(withId(R.id.button))
                .check(matches(isClickable()));
    }

    @Test
    public void testDiscoverEventsList() {
        login();
        onView(withId(R.id.button7))
                .perform(click());
        onView(withId(R.id.recycleViewer)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void testDiscoverEventsMap() {
        login();
        onView(withId(R.id.button7))
                .perform(click());
        onView(withId(R.id.button10))
                .perform(click());
        onView(withId(R.id.button15))
                .check(matches(isClickable()));
    }


    @Test
    public void testCreateEventEmpty() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.button11))
                .perform(click());
        onView(withId(R.id.button14))
                .perform(click());
        onView(withId(R.id.button14))
                .check(matches(isClickable()));
    }

    @Test
    public void testCreateEventEmptyAddress() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.button11))
                .perform(click());
        onView(withId(R.id.editTextTextPersonName4))
                .perform(replaceText("Event Description"));
        onView(withId(R.id.editTextTextPostalAddress))
                .perform(replaceText(""));
        onView(withId(R.id.button14))
                .perform(click());
        onView(withId(R.id.button14))
                .check(matches(isClickable()));
    }

    @Test
    public void testCreateEvent() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.button11))
                .perform(click());
        onView(withId(R.id.editTextTextPersonName4))
                .perform(replaceText("Event Description"));
        onView(withId(R.id.editTextTextPostalAddress))
                .perform(replaceText("124 Main St"));
        onView(withId(R.id.button14))
                .perform(click());
        onView(withId(R.id.button23))
                .check(matches(isClickable()));
    }
    @Test
    public void testMyPendingEvents() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.button11))
                .check(matches(isClickable()));
    }
    @Test
    public void testMyConfirmedEvents() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.button12))
                .perform(click());
        onView(withId(R.id.button24))
                .check(matches(isClickable()));
        onView(withId(R.id.button24))
                .check(matches(isClickable()));

    }
    @Test
    public void testMyPendingInvites() {
        login();
        onView(withId(R.id.button6))
                .perform(click());
    }
    @Test
    public void testMyConfirmedInvites() {
        login();
        onView(withId(R.id.button6))
                .perform(click());
        onView(withId(R.id.button26))
                .perform(click());

    }
    @Test
    public void testViewEvent() {
        login();
        onView(withId(R.id.button8))
                .perform(click());
        onView(withId(R.id.recycleViewer))
                .check(matches(isDisplayed()));


    }
}

