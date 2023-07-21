package org.sExample.Pages;

public class HomePage extends BasePage {

    public HomePage() {
    }

    public void searchFor(String keyword) {
        enterSearch(keyword);
        search();
    }


}
