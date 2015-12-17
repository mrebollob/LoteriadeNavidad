package com.mrebollob.loteriadenavidad;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.mrebollob.loteriadenavidad.app.modules.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author mrebollob
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateLotteryTicketTest {

    public static final String TEST_LABEL = "Test number";
    public static final String TEST_NUMBER = "00007";
    public static final String TEST_BET = "20";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void createChristmasLotteryTicket() {

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.et_label)).perform(typeText(TEST_LABEL));
        onView(withId(R.id.et_number)).perform(typeText(TEST_NUMBER));
        onView(withId(R.id.et_bet)).perform(typeText(TEST_BET), closeSoftKeyboard());
        onView(withId(R.id.rb_christmas_lottery)).perform(click());

        onView(withId(R.id.action_save)).perform(click());

        onView(withId(R.id.spinner)).perform(click());
        onView(withText(R.string.christmas_lottery)).perform(click());

        onView(withId(R.id.list)).check(matches(hasDescendant(withText(TEST_LABEL))));
        onView(withId(R.id.list)).check(matches(hasDescendant(withText(TEST_NUMBER))));
    }

    @Test
    public void createChildLotteryTicket() {

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.et_label)).perform(typeText(TEST_LABEL));
        onView(withId(R.id.et_number)).perform(typeText(TEST_NUMBER));
        onView(withId(R.id.et_bet)).perform(typeText(TEST_BET), closeSoftKeyboard());
        onView(withId(R.id.rb_child_lottery)).perform(click());

        onView(withId(R.id.action_save)).perform(click());

        onView(withId(R.id.spinner)).perform(click());
        onView(withText(R.string.child_lottery)).perform(click());

        onView(withId(R.id.list)).check(matches(hasDescendant(withText(TEST_LABEL))));
        onView(withId(R.id.list)).check(matches(hasDescendant(withText(TEST_NUMBER))));
    }
}
