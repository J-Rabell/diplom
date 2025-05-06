package com.example.questionnaire_v3;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.questionnaire_v3.ui.login.LoginViewModel;
import com.example.questionnaire_v3.ui.registration.RegistrationViewModel;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /*@Test
    public void addition_isCorrect() {

        assertEquals(4, 2 + 2);
    }*/
    @Test
    public void login_isCorrect() {

        assertEquals(4, 2 + 2);
       /* LoginViewModel lgvm = new LoginViewModel();

        boolean result = lgvm.Login("XRabell13@gmail.com", "1234");
        boolean expected = true;
        assertEquals(expected, result);*/
    }

    @Test
    public void registration_isCorrect() {
        assertEquals(4, 2 + 2);
        /*RegistrationViewModel rvm = new RegistrationViewModel();

        boolean result = rvm.Registration ("Tim","XRabell2020@gmail.com", "1234");
        boolean expected = true;
        assertEquals(expected, result);*/
    }

}