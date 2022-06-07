//package ui.swagger.bestbuyapiplaygroundinfo;
//
//
//import io.restassured.http.ContentType;
//import net.serenitybdd.junit.runners.SerenityRunner;
//import net.serenitybdd.rest.SerenityRest;
//import net.thucydides.core.annotations.Title;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import ui.swagger.model.ProductPojo;
//import ui.swagger.productconstants.ProductEndPoints;
//import ui.swagger.testbase.TestBase;
//import ui.swagger.utils.TestUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.hamcrest.Matchers.hasValue;
//
//
//@RunWith(SerenityRunner.class)
//public class ProductCRUDTest extends TestBase {
//
//    //static String name = "Metra - Radio Installation Dash Kit for Most 1989-2000 Ford, Lincoln & Mercury Vehicles - Black";
//    static String name = "Hello12354";
//    static String type = "PrimeUser" + TestUtils.getRandomValue();
//    static int price = TestUtils.getRandomPriceValue();
//    static Integer shipping = 0;
//    static String upc = "0" + TestUtils.getRandomValue();
//    static String description = "Compatible with any body types and electronic devices; 240V size; " +
//            "DURALOCK Power Preserve technology";
//    static String manufacturer = "PureGym";
//    static String model = "MN" + TestUtils.getSmallerRandomValue() + "DBZ";
//    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900." +
//            "p?id=1051384074145&skuId=43900&cmp=RMXCC";
//    static String image = "https://static.wixstatic.com/media/6011fb_b48414e4c7f446c4a90e02e04056f9cf~mv2.png/v1/fill" +
//            "/w_1362,h_1368,al_c,q_90,enc_auto/BeHealthy.png";
//    static int productId;
//
//
//
//    @Title("This test will create a new product")
//    @Test
//    public void test001() {
//        ProductPojo productPojo = new ProductPojo();
//        productPojo.setName(name);
//        productPojo.setType(type);
//        productPojo.setPrice(price);
//        productPojo.setShipping(shipping);
//        productPojo.setUpc(upc);
//        productPojo.setDescription(description);
//        productPojo.setManufacturer(manufacturer);
//        productPojo.setModel(model);
//        productPojo.setUrl(url);
//        productPojo.setImage(image);
//
//        SerenityRest.given().log().all()
//                .contentType(ContentType.JSON)
//                .body(productPojo)
//                .when()
//                .post(ProductEndPoints.GET_ALL_PRODUCT)
//                .then().log().all().statusCode(201);
//        productId = response.log().all().extract().path("id");
//        System.out.println(productId);
//    }
//
//    @Title("Verify the product was added to the application")
//    @Test
//    public void test002(){
//
//        HashMap<String,?> productMap = SerenityRest.given().log().all()
//                .when()
//                .pathParam("productId",productId)
//                .get(ProductEndPoints.GET_SINGLE_PRODUCT_BY_ID)
//                .then()
//                .statusCode(200)
//                .extract().path("");
//        Assert.assertThat(productMap, hasValue(name));
//        System.out.println(productMap);
//    }
//
//    @Title("Update the product information to the application and verify")
//    @Test
//    public void test003(){
//        name = name + "_updated";
//
//        ProductPojo productPojo = new ProductPojo();
//        productPojo.setName(name);
//        productPojo.setType(type);
//        productPojo.setPrice(price);
//        productPojo.setShipping(shipping);
//        productPojo.setUpc(upc);
//        productPojo.setDescription(description);
//        productPojo.setManufacturer(manufacturer);
//        productPojo.setModel(model);
//        productPojo.setUrl(url);
//        productPojo.setImage(image);
//
//        SerenityRest.given().log().all()
//                .header("Content-Type", "application/json")
//                .pathParam("productID", productId)
//                .body(productPojo)
//                .when()
//                .put(ProductEndPoints.UPDATE_PRODUCT_BY_ID)
//                .then().log().all().statusCode(200);
//
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.name";
//
//        HashMap<String,Object> productMap= SerenityRest.given().log().all()
//                .when()
//                .get(ProductEndPoints.GET_ALL_PRODUCT)
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(productMap, hasValue(name));
//        productId = (int) productMap.get("id");
//        System.out.println(productId);
//    }
//
//    @Title("Delete the product and verify if the product has been deleted")
//    @Test
//    public void test004(){
//        SerenityRest.given().log().all()
//                .pathParam("productID", productId)
//                .when()
//                .delete(ProductEndPoints.DELETE_PRODUCT_BY_ID)
//                .then().statusCode(200);
//
//        SerenityRest.given().log().all()
//                .pathParam("productID", productId)
//                .when()
//                .get(ProductEndPoints.GET_SINGLE_PRODUCT_BY_ID)
//                .then()
//                .statusCode(404);
//    }
//}
