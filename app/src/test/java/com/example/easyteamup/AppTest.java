package com.example.easyteamup;

import org.junit.Test;
import static org.junit.Assert.*;


public class AppTest {

    @Test
    public void singletonTest(){
        App a = new App();
        a.setEmail("test@test.com");
        a.setUsername("Mr.test");
        a.setFullname("Testa");
        App b = new App();
        b.setEmail("test1@test.com");
        b.setUsername("Mr.test1");
        b.setFullname("Testb");
        assertEquals(a.getInstance(), b.getInstance());
    }

}
