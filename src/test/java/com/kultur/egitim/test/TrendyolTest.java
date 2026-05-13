package com.kultur.egitim.test;

import com.kultur.egitim.driver.Driver;
import com.kultur.egitim.method.Methods;
import com.kultur.egitim.pages.HomePage;
import com.kultur.egitim.pages.ProductPage;
import com.kultur.egitim.pages.ProductDetailPage;
import org.junit.jupiter.api.Test;

public class TrendyolTest extends Driver {

    HomePage homePage;

    @Test
    public void test1(){
        homePage = new HomePage();
        homePage.verifyHomePage()
        .verifyCookie();
        homePage.verifyGender();
        homePage.clickAcceptCookieButton();
        homePage.clickFemaleGenderButton();
        homePage.clickSearchButton();
        homePage.inputSearch("iphone 17");
        // Arama yapıyoruz ve bu işlem bizi ProductPage'e fırlatıyor
        ProductPage productPage = homePage.doSearch("");

        // İlk Ürüne Tıklama ve Yeni Sekmeye Geçiş
        ProductDetailPage detailPage = productPage.verifyProductPage()
                .selectFirstProduct();


        detailPage.selectColor();

        Methods.wait(2);


        detailPage.selectCapacity("512 GB");

        Methods.wait(2); // Sonucu görmek için bekletiyoruz

        detailPage.addToBasket();

        Methods.wait(5); // Sonucu görmek için bekletiyoruz





    }
}
