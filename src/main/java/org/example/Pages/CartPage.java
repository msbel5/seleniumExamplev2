package org.sExample.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "m-basket__productInfoName")
    WebElement productNameInCart;

    @FindBy(how = How.XPATH, using = "//*[@class='m-productPrice__salePrice']")
    WebElement productPriceInCart;

    @FindBy(how = How.ID, using = "quantitySelect0-key-0")
    WebElement quantityDropdown;

    @FindBy(how = How.CSS, using = "#removeCartItemBtn0-key-0")
    WebElement removeItemButton;

    @FindBy(how = How.CLASS_NAME, using = "m-empty__messageTitle")
    WebElement emptyCartMessage;

    public CartPage() {
    }

    public String getProductNameInCart() {
        wait.until(ExpectedConditions.visibilityOf(productPriceInCart));
        return productNameInCart.getText();
    }

    public String getProductPriceInCart() {
        wait.until(ExpectedConditions.visibilityOf(productPriceInCart));
        return productPriceInCart.getText();
    }

    public void removeItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeItemButton)).click();
    }

    public String getProductQuantity() {
        Select select = new Select(quantityDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void setProductQuantity(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
        Select select = new Select(quantityDropdown);
        select.selectByIndex(index);
    }

    public void increaseProductQuantity() {
        int currentQuantity = Integer.parseInt(getProductQuantity());
        setProductQuantity(currentQuantity + 1);
    }

    public boolean isCartEmpty() {
        wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
        return emptyCartMessage.isDisplayed();
    }

    public void waitVisibility() {
        wait.until(ExpectedConditions.visibilityOf(productPriceInCart));
    }
}


