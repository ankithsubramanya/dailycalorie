package com.example.ankith.dailycalorie;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testUserResult(){
        User user = new User();
        user.setWeight(35);
        user.setHeight(176);
        user.setCaloriesLost(0.0);
        assert (user.getCaloriesLeft()==2200.0);
    }
}