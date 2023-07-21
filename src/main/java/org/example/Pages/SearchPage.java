package org.sExample.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@class='o-productList__item']")
    private List<WebElement> productImages;

    public SearchPage() {
        super();
    }

    public void selectRandomProduct() {
        productImages = wait.until(ExpectedConditions.visibilityOfAllElements(productImages));
        if (productImages.isEmpty()) {
            throw new IllegalStateException("No products found!");
        }
        int randomIndex = new Random().nextInt(productImages.size());
        productImages.get(randomIndex).click();
    }
}

