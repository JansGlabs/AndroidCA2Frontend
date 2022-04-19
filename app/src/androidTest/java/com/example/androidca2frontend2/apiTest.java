package com.example.androidca2frontend2;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
public class apiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void apiTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.console), withText("Consoles"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.consolesResult), withText("1, PC\n\n2, PlayStation\n\n3, PlayStation 2\n\n4, PlayStation 3\n\n5, PlayStation 4\n\n6, PlayStation 5\n\n7, Xbox\n\n8, Xbox 360\n\n9, Xbox One\n\n10, Xbox Series X\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.games), withText("Games"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.gameResult), withText("APB Reloaded, Action, Likes: 6\n\nCS:GO, FPS, Likes: 8\n\nElden Ring, Adventure, Likes: 4\n\nGrand Theft Auto, Action, Likes: 0\n\nWitcher, Story, Likes: 4\n\nFallout, Story, Likes: 0\n\nGod of War, Story, Likes: 2\n\nMinecraft, Survival, Likes: 0\n\nAssetto Corsa, Racing, Likes: 2\n\nForza, Racing, Likes: 1\n\nFortnite, Battle Royale, Likes: 4\n\nApex Legends, Battle Royale, Likes: 0\n\nDark Souls, Adventure, Likes: 0\n\nOverwatch, FPS, Likes: 2\n\nCall of Duty, FPS, Likes: 0\n\nRust, Survival, Likes: 0\n\nTerraria, Survival, Likes: 2\n\nGarrys Mod, Multiplayer, Likes: 1\n\nBattlefield, FPS, Likes: 0\n\nCyberpunk 2077, Action, Likes: 0\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView2.perform(click());
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
