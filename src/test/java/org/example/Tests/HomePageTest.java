package org.sExample.Test;

import org.openqa.selenium.support.PageFactory;
import org.sExample.Pages.CartPage;
import org.sExample.Pages.HomePage;
import org.sExample.Pages.ProductPage;
import org.sExample.Pages.SearchPage;
import org.sExample.Util.FileManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    CartPage cartPage;

    @Test
    public void testProductPurchase() {

        FileManager fileManager = new FileManager();

        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setDriver(driver);

        homePage.acceptCookies();
        homePage.chooseGender();
        String firstSearchItem = fileManager.getExcelData(0, 0);
        homePage.enterSearch(firstSearchItem);
        homePage.clearSearch();

        String secondSearchItem = fileManager.getExcelData(0, 1);
        homePage.searchFor(secondSearchItem);

        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchPage.setDriver(driver);
        searchPage.selectRandomProduct();

        productPage = PageFactory.initElements(driver, ProductPage.class);
        productPage.setDriver(driver);
        String productInfo = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        fileManager.writeTextData(productInfo, productPrice);
        productPage.addToCart();
        productPage.goToCart();

        cartPage = PageFactory.initElements(driver, CartPage.class);
        cartPage.setDriver(driver);
        cartPage.waitVisibility();
        String cartProductPrice = cartPage.getProductPriceInCart();
        Assert.assertEquals(productPrice, cartProductPrice);
        String productQuantity1 = cartPage.getProductQuantity();
        Assert.assertEquals("1 adet", productQuantity1);
        cartPage.setProductQuantity(1);
        String productQuantity = cartPage.getProductQuantity();
        Assert.assertEquals("2 adet", productQuantity);

        cartPage.removeItemFromCart();
        Assert.assertTrue(cartPage.isCartEmpty());
    }

}
