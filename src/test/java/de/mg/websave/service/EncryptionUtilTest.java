package de.mg.websave.service;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptionUtilTest {

    @Test
    public void test() {
        EncryptionUtil sut = new EncryptionUtil();
        String txt = "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String encrypt = sut.encrypt("12345", txt);
        System.out.println(encrypt);
        String decrypt = sut.decrypt("12345", encrypt);
        assertEquals(txt, decrypt);
    }
}
