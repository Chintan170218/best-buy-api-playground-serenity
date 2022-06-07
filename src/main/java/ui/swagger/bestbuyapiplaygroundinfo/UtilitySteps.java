package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import ui.swagger.utilityconstants.UtilityEndPoints;


public class UtilitySteps {

    @Step("Getting Version information")
    public ValidatableResponse getVersion(){

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(UtilityEndPoints.GET_VERSION)
                .then();
    }

    @Step("Getting HealthCheck information")
    public ValidatableResponse getHealthCheck() {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(UtilityEndPoints.GET_HEALTH_CHECK)
                .then();
    }
}
