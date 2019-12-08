package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.selenium.support.HomePage;
import com.dbhacademy.firefight.selenium.support.SeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SeleniumTest(baseUrl = "http://localhost:9000")
public class FrontEndControllerSeleniumTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebDriver webDriver;

    private HomePage homePage;

    @Before
    public void setUp() throws Exception {
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @Test
    public void buscarGeneraResultadosTest() {


        String textoABuscar = "constitución";
        WebElement textoABuscarInput = webDriver.findElement(By.name("textoABuscar"));
        textoABuscarInput.sendKeys(textoABuscar);
        textoABuscarInput.sendKeys(Keys.ENTER);

        String getUrl = webDriver.getCurrentUrl();

        Assert.assertEquals(getUrl, "http://localhost:"+port+"/buscar");

    }

    @Test
    public void homeTieneLinks() {
        homePage.assertThat()
                .hasActuatorLink("Elige Temas")
                .hasNoActuatorLink("shutdown");
    }

}
