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
//import ui.swagger.model.StorePojo;
//import ui.swagger.storeconstants.StoreEndPoints;
//import ui.swagger.testbase.TestBase;
//
//import java.util.HashMap;
//
//import static org.hamcrest.Matchers.hasValue;
//
//
//@RunWith(SerenityRunner.class)
//public class StoreCRUDTest extends TestBase {
//
//    static String name = "Maplewood";
//    static String type = "BigBox";
//    static String address = "13513 Ridgedale Dr";
//    static String address2 = "";
//    static String city = "Hopkins";
//    static String state = "MN";
//    static String zip = "55305";
//    static double lat =44.969658;
//    static double lng =-93.449539;
//    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
//    static int storeId;
//
//
//
//    @Title("This test will create a new store")
//    @Test
//    public void test001() {
//        StorePojo storePojo = new StorePojo();
//        storePojo.setName(name);
//        storePojo.setType(type);
//        storePojo.setAddress(address);
//        storePojo.setAddress2(address2);
//        storePojo.setCity(city);
//        storePojo.setState(state);
//        storePojo.setZip(zip);
//        storePojo.setLat(lat);
//        storePojo.setLng(lng);
//        storePojo.setHours(hours);
//
//
//        SerenityRest.given().log().all()
//                .contentType(ContentType.JSON)
//                .body(storePojo)
//                .when()
//                .post()
//                .then().log().all().statusCode(201);
//    }
//
//    @Title("Verify the store was added to the application")
//    @Test
//    public void test002(){
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> storeMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(storeMap, hasValue(name));
//        storeId = (int) storeMap.get("id");
//        System.out.println(storeId);
//    }
//
//    @Title("Update the store information to the application and verify")
//    @Test
//    public void test003(){
//        name = name + "_updated";
//
//        StorePojo storePojo = new StorePojo();
//        storePojo.setName(name);
//        storePojo.setType(type);
//        storePojo.setAddress(address);
//        storePojo.setAddress2(address2);
//        storePojo.setCity(city);
//        storePojo.setState(state);
//        storePojo.setZip(zip);
//        storePojo.setLat(lat);
//        storePojo.setLng(lng);
//        storePojo.setHours(hours);
//
//        SerenityRest.given().log().all()
//                .header("Content-Type", "application/json")
//                .pathParam("storeID", storeId)
//                .body(storePojo)
//                .when()
//                .put(StoreEndPoints.UPDATE_STORE_BY_ID)
//                .then().log().all().statusCode(200);
//
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> storeMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(storeMap, hasValue(name));
//        storeId = (int) storeMap.get("id");
//        System.out.println(storeId);
//    }
//
//    @Title("Delete the store and verify if the store has been deleted")
//    @Test
//    public void test004(){
//        SerenityRest.given().log().all()
//                .pathParam("storeID", storeId)
//                .when()
//                .delete(StoreEndPoints.DELETE_STORE_BY_ID)
//                .then().statusCode(200);
//
//        SerenityRest.given().log().all()
//                .pathParam("storeID", storeId)
//                .when()
//                .get(StoreEndPoints.GET_SINGLE_STORE_BY_ID)
//                .then()
//                .statusCode(404);
//    }
//}
