package com.example.androidca2frontend2;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class languageTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void languageTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.changeLang), withText("Change Language"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                13),
                        isDisplayed()));
        materialButton.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(androidx.appcompat.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(androidx.appcompat.R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Aplikacija za igre"),
                        withParent(allOf(withId(androidx.appcompat.R.id.action_bar),
                                withParent(withId(androidx.appcompat.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("Aplikacija za igre")));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.changeLang), withText("Promijeni Jezik"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                13),
                        isDisplayed()));
        materialButton2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(androidx.appcompat.R.id.select_dialog_listview),
                        childAtPosition(
                                withId(androidx.appcompat.R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Games App"),
                        withParent(allOf(withId(androidx.appcompat.R.id.action_bar),
                                withParent(withId(androidx.appcompat.R.id.action_bar_container)))),
                        isDisplayed()));
        textView2.check(matches(withText("Games App")));
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
