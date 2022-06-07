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
//import ui.swagger.model.ServicePojo;
//import ui.swagger.serviceconstants.ServiceEndPoints;
//import ui.swagger.testbase.TestBase;
//
//import java.util.HashMap;
//
//import static org.hamcrest.Matchers.hasValue;
//
//
//@RunWith(SerenityRunner.class)
//public class ServicesCRUDTest extends TestBase {
//
//    static String name = "Geek Squad Services";
//    static int serviceId;
//
//
//    @Title("This test will create a new service")
//    @Test
//    public void test001() {
//        ServicePojo servicePojo = new ServicePojo();
//        servicePojo.setName(name);
//
//
//        SerenityRest.given().log().all()
//                .contentType(ContentType.JSON)
//                .body(servicePojo)
//                .when()
//                .post()
//                .then().log().all().statusCode(201);
//    }
//
//    @Title("Verify the service was added to the application")
//    @Test
//    public void test002(){
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> serviceMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(serviceMap, hasValue(name));
//        serviceId = (int) serviceMap.get("id");
//        System.out.println(serviceId);
//    }
//
//    @Title("Update the service information to the application and verify")
//    @Test
//    public void test003(){
//        name = name + "_updated";
//
//        ServicePojo servicePojo = new ServicePojo();
//        servicePojo.setName(name);
//
//        SerenityRest.given().log().all()
//                .header("Content-Type", "application/json")
//                .pathParam("serviceID", serviceId)
//                .body(servicePojo)
//                .when()
//                .put(ServiceEndPoints.UPDATE_SERVICE_BY_ID)
//                .then().log().all().statusCode(200);
//
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> serviceMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(serviceMap, hasValue(name));
//        serviceId = (int) serviceMap.get("id");
//        System.out.println(serviceId);
//    }
//
//    @Title("Delete the service and verify if the service has been deleted")
//    @Test
//    public void test004(){
//        SerenityRest.given().log().all()
//                .pathParam("serviceId", serviceId)
//                .when()
//                .delete(ServiceEndPoints.DELETE_SERVICE_BY_ID)
//                .then().statusCode(200);
//
//        SerenityRest.given().log().all()
//                .pathParam("serviceId", serviceId)
//                .when()
//                .get(ServiceEndPoints.GET_SINGLE_SERVICE_BY_ID)
//                .then()
//                .statusCode(404);
//    }
//}
