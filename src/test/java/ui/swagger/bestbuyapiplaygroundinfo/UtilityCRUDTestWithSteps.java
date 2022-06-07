package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.testbase.TestBase;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;


@RunWith(SerenityRunner.class)
public class UtilityCRUDTestWithSteps extends TestBase {


    @Steps
    UtilitySteps utilitySteps;

    @Title("Get the version of Application")
    @Test
    public void getVersion() {
        ValidatableResponse response = utilitySteps.getVersion();
        response.log().all().statusCode(200);
    }

    @Title("Get the Health check of Application")
    @Test
    public void getHealthCheck() {
        ValidatableResponse response = utilitySteps.getHealthCheck();
        response.log().all().statusCode(200);
        HashMap<String, ?> healthCheckMap = response.extract().path("");
        Assert.assertThat(healthCheckMap, hasKey("uptime"));
        Assert.assertThat(healthCheckMap, hasKey("readonly"));
        Assert.assertThat(healthCheckMap, hasKey("documents"));
    }
}
