package com.kultur.egitim.pages;

import com.kultur.egitim.method.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ProductPage {
    Methods methods;

    // Trendyol'daki ürün kartlarının genel class'ı
    By firstProductBy = By.cssSelector("a.product-card");

    public ProductPage() {
        methods = new Methods();
    }

    public ProductPage verifyProductPage() {
        // Ürünler geldi mi diye kontrol ediyoruz
        Assertions.assertTrue(methods.isElementVisible(firstProductBy), "Ürünler yüklenmedi!");
        return this;
    }

    public ProductDetailPage selectFirstProduct() {
        methods.click(firstProductBy); // İlk ürüne tıkla
        methods.switchToNewTab();      // Tıkladıktan sonra açılan YENİ SEKMEYE geç
        return new ProductDetailPage(); // Bizi Ürün Detay sayfasına atar
    }
}