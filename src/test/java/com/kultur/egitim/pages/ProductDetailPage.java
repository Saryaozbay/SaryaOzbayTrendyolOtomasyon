package com.kultur.egitim.pages;

import com.kultur.egitim.method.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductDetailPage {
    Methods methods;

    // Trendyol'da renk/beden seçenekleri genelde bu class ile tutulur
    By colorOptionsBy = By.cssSelector("button.image-box-image-box");

    public ProductDetailPage() {
        methods = new Methods();
    }

    public ProductDetailPage selectColor() {
        if(methods.isElementVisible(colorOptionsBy)) {
            // Standart click yerine yeni yazdığımız JS click'i kullanıyoruz
            methods.clickWithJs(colorOptionsBy);
        }
        return this;
    }

    // Sadece hafıza/kapasite alanındaki seçenekleri hedefleyen bir locator
    By allAttributesBy = By.cssSelector("button.text-box-text-box");

    public ProductDetailPage selectCapacity(String capacityText) {
        // 1. Tüm seçenekleri (renk, hafıza vs.) listele
        List<WebElement> attributes = methods.findElements(allAttributesBy);

        boolean found = false;
        for (WebElement attr : attributes) {
            // 2. Eğer kutunun içindeki metin bizim istediğimiz (örn: 256 GB) ise
            if (attr.getText().contains(capacityText)) {
                // 3. Yine o gıcık katmanlara takılmamak için JS Click kullanıyoruz
                methods.clickWithJs(By.xpath("//div[contains(text(),'" + capacityText + "')]"));
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println(capacityText + " seçeneği bulunamadı, default olanla devam ediliyor.");
        }

        return this;
    }

    // ProductDetailPage.java içine locator ekle:
    By addToBasketBy = By.cssSelector("button[data-testid=\"add-to-cart-button\"]");

    public ProductDetailPage addToBasket() {
        // 1. Butonun görünür olduğundan emin olalım
        Assertions.assertTrue(methods.isElementVisible(addToBasketBy), "Sepete Ekle butonu bulunamadı!");

        // 2. Sayfayı butona doğru kaydıralım (Garanti olsun diye)
        methods.scrollElementCenter(methods.findElement(addToBasketBy));

        // 3. Yine o katman hatalarına takılmamak için JS Click ile tıklayalım
        methods.clickWithJs(addToBasketBy);

        System.out.println("Ürün başarıyla sepete eklendi!");
        return this;
    }
}