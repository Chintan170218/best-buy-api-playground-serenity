package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.model.ServicePojo;
import ui.swagger.serviceconstants.ServiceEndPoints;
import ui.swagger.storeconstants.StoreEndPoints;

import java.util.HashMap;


public class ServiceSteps {

    @Step("Creating service with name : {0}")
    public ValidatableResponse createService(String name) {

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicePojo)
                .when()
                .post(ServiceEndPoints.GET_ALL_SERVICES)
                .then();
    }

    @Step("Getting service information with name : {0}")
    public HashMap<String, ?> getServiceInfoById(int serviceId) {

        HashMap<String,?> serviceMap = SerenityRest.given().log().all()
                .when()
                .pathParam("serviceId", serviceId)
                .get(ServiceEndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return serviceMap;
    }

    @Step("Updating service information with serviceId: {0}, name : {1}")
    public ValidatableResponse updateService(int serviceId, String name) {

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);


        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceId", serviceId)
                .body(servicePojo)
                .when()
                .put(ServiceEndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }

    @Step("Deleting Service information with ServiceID: {0}")
    public ValidatableResponse deleteService (Integer serviceId) {

        return SerenityRest.given().log().all()
                .pathParam("serviceId", serviceId)
                .when()
                .delete(ServiceEndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting Service information with ServiceId: {0}")
    public ValidatableResponse getServiceByID (Integer serviceId) {

        return SerenityRest.given().log().all()
                .pathParam("serviceId", serviceId)
                .when()
                .get(ServiceEndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }
}
