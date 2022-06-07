package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.model.StorePojo;
import ui.swagger.productconstants.ProductEndPoints;
import ui.swagger.storeconstants.StoreEndPoints;

import java.util.HashMap;


public class StoreSteps {

    @Step("Creating Store with name : {0}, type: {1}, address: {2}, address2: {3}, city: {4}," +
            " state: {5}, zip: {6}, lat: {7}, lng: {8} and hours: {9}")
    public ValidatableResponse createStore
            (String name, String type, String address, String address2, String city, String state,
             String zip, double lat, double lng, String hours) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(StoreEndPoints.GET_ALL_STORES)
                .then();
    }

    @Step("Getting store information with Id : {0}")
    public HashMap<String, ?> getStoreInfoById(int storeId) {

        HashMap<String,?> storeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("storeId",storeId)
                .get(StoreEndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return storeMap;
    }

    @Step("Updating store information with storeId: {0}, name : {1}, type: {2}, address: {3}, address2: {4}, city: {5}," +
            "state: {6}, zip: {7}, lat: {8}, lng: {9} and hours: {10}")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2, String city, String state,
                                             String zip, double lat, double lng, String hours) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeId", storeId)
                .body(storePojo)
                .when()
                .put(StoreEndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("Deleting Store information with StoreID: {0}")
    public ValidatableResponse deleteStore (Integer storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .delete(StoreEndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting Store information with StoreId: {0}")
    public ValidatableResponse getStoreByID (Integer storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(StoreEndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
}
