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

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class ServicesCRUDTestWithSteps extends TestBase {

    static String name = "Geek Squad Services";
    static int serviceId;

    @Steps
    ServiceSteps serviceSteps;

    @Title("This test will create a new service")
    @Test
    public void test001() {

        ValidatableResponse response = serviceSteps.createService(name);
        response.log().all().statusCode(201);
        serviceId = response.log().all().extract().path("id");
        System.out.println(serviceId);
    }


    @Title("Verify the service was added to the application")
    @Test
    public void test002() {

        HashMap<String, ?> serviceMap = serviceSteps.getServiceInfoById(serviceId);
        Assert.assertThat(serviceMap, hasValue(serviceId));
        System.out.println(serviceMap);
    }


    @Title("Update the service information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        serviceSteps.updateService(serviceId, name).log().all().statusCode(200);
        HashMap<String, ?> serviceMap = serviceSteps.getServiceInfoById(serviceId);
        Assert.assertThat(serviceMap, hasValue(serviceId));
    }


    @Title("Delete the service and verify if the service has been deleted")
    @Test
    public void test004() {
        serviceSteps.deleteService(serviceId).statusCode(200);

        serviceSteps.getServiceByID(serviceId).statusCode(404);
    }
}
