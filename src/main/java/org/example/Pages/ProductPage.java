package org.sExample.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    @FindBy(how = How.ID, using = "priceNew")
    private WebElement productPrice;
    @FindBy(how = How.XPATH, using = "//*[@class='o-productDetail__description']")
    private WebElement productName;
    @FindBy(how = How.CLASS_NAME, using = "m-variation__item")
    List<WebElement> sizes;
    @FindBy(how = How.CSS, using = "#addBasket")
    private WebElement addToCartButton;
    @FindBy(how = How.XPATH, using = "//*[@class='icon icon-cart']")
    private WebElement goToCartButton;
    @FindBy(how = How.XPATH, using = "//*[@class='m-notification__button btn']")
    private WebElement goToCartNotificationButton;


    public ProductPage() {
        super();
    }

    public String getProductName() {
        productName = wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public String getProductPrice() {
        productPrice = wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.getText();
    }

    public void selectRandomSize() {
        List<WebElement> enabledSizes = sizes.stream()
                .filter(size -> !size.getAttribute("class").contains("-disabled"))
                .collect(Collectors.toList());
        if (enabledSizes.isEmpty()) {
            throw new IllegalStateException("No enabled sizes found!");
        }
        int randomSizeIndex = new Random().nextInt(enabledSizes.size());
        WebElement randomSize = enabledSizes.get(randomSizeIndex);
        wait.until(ExpectedConditions.elementToBeClickable(randomSize)).click();
    }

    public void addToCart() {
        addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        selectRandomSize();
        addToCartButton.click();
    }

    public void goToCart() {
        goToCartButton = wait.until(ExpectedConditions.elementToBeClickable(goToCartNotificationButton));
        goToCartButton.click();
    }
}
