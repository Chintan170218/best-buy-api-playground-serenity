package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.testbase.TestBase;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends TestBase {

    static String name = "Jensenov - 4V NiCad Battery for 900MHz Phones";
    static String type = "PrimeUser" + TestUtils.getRandomValue();
    static int price = TestUtils.getRandomPriceValue();
    static Integer shipping = 0;
    static String upc = "0" + TestUtils.getRandomValue();
    static String description = "Compatible with any body types and electronic devices; 240V size; " +
            "DURALOCK Power Preserve technology";
    static String manufacturer = "PureGym";
    static String model = "MN" + TestUtils.getSmallerRandomValue() + "DBZ";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900." +
            "p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "https://static.wixstatic.com/media/6011fb_b48414e4c7f446c4a90e02e04056f9cf~mv2.png/v1/fill" +
            "/w_1362,h_1368,al_c,q_90,enc_auto/BeHealthy.png";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This test will create a new product")
    @Test
    public void test001() {

        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);
    }


    @Title("Verify the product was added to the application")
    @Test
    public void test002() {

        HashMap<String, ?> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
        System.out.println(productMap);
    }


    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        productSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        HashMap<String, ?> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
        System.out.println(productMap);
    }


    @Title("Delete the product and verify if the product has been deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(200);

        productSteps.getProductByID(productId).statusCode(404);
    }
}
