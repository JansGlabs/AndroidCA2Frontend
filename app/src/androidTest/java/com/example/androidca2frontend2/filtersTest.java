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
public class filtersTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void filtersTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.action), withText("Action"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.gameResult), withText("APB Reloaded, Action, Likes: 6\n\nGrand Theft Auto, Action, Likes: 0\n\nCyberpunk 2077, Action, Likes: 0\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.adventure), withText("Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.gameResult), withText("Elden Ring, Adventure, Likes: 4\n\nDark Souls, Adventure, Likes: 0\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.fps), withText("FPS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.gameResult), withText("CS:GO, FPS, Likes: 8\n\nOverwatch, FPS, Likes: 2\n\nCall of Duty, FPS, Likes: 0\n\nBattlefield, FPS, Likes: 0\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.story), withText("Story"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.gameResult), withText("Witcher, Story, Likes: 4\n\nFallout, Story, Likes: 0\n\nGod of War, Story, Likes: 2\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.survival), withText("Survival"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.gameResult), withText("Minecraft, Survival, Likes: 0\n\nRust, Survival, Likes: 0\n\nTerraria, Survival, Likes: 2\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.racing), withText("Racing"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialTextView6 = onView(
                allOf(withId(R.id.gameResult), withText("Assetto Corsa, Racing, Likes: 2\n\nForza, Racing, Likes: 1\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialTextView6.perform(click());
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
