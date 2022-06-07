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
public class StoreCRUDTestWithSteps extends TestBase {

    static String name = "Maplewood";
    static String type = "BigBox";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "";
    static String city = "Hopkins";
    static String state = "MN";
    static String zip = "55305";
    static double lat =44.969658;
    static double lng =-93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("This test will create a new store")
    @Test
    public void test001() {

        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state,
                zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId = response.log().all().extract().path("id");
        System.out.println(storeId);
    }


    @Title("Verify the store was added to the application")
    @Test
    public void test002() {

        HashMap<String, ?> storeMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
        System.out.println(storeMap);

    }


    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        storeSteps.updateStore(storeId, name, type, address, address2, city, state,
                zip, lat, lng, hours).log().all().statusCode(200);
        HashMap<String, ?> storeMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
    }


    @Title("Delete the product and verify if the product has been deleted")
    @Test
    public void test004() {
        storeSteps.deleteStore(storeId).statusCode(200);

        storeSteps.getStoreByID(storeId).statusCode(404);
    }
}
