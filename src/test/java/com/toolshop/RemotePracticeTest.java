package com.toolshop;

import com.toolshop.gui.pages.AbstractTest;
import com.toolshop.gui.pages.HomePage;
import org.testng.annotations.Test;

public class RemotePracticeTest extends AbstractTest {
    @Test
    public void testRemotePracticeWebsite() {
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct("Hammer");
    }
}

