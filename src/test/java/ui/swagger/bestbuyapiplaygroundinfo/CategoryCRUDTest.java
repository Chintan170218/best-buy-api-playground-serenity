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
//import ui.swagger.categoryconstants.CategoryEndPoints;
//import ui.swagger.model.CategoriesPojo;
//import ui.swagger.testbase.TestBase;
//
//import java.util.HashMap;
//
//import static org.hamcrest.Matchers.hasValue;
//
//
//@RunWith(SerenityRunner.class)
//public class CategoryCRUDTest extends TestBase {
//
//    static String ID = "abcat0010000";
//    static String name = "Gift Ideas";
//    static int categoryId;
//
//
//    @Title("This test will create a new category")
//    @Test
//    public void test001() {
//        CategoriesPojo categoriesPojo = new CategoriesPojo();
//        categoriesPojo.setID(ID);
//        categoriesPojo.setName(name);
//
//
//        SerenityRest.given().log().all()
//                .contentType(ContentType.JSON)
//                .body(categoriesPojo)
//                .when()
//                .post()
//                .then().log().all().statusCode(201);
//    }
//
//    @Title("Verify the category was added to the application")
//    @Test
//    public void test002(){
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> categoryMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(categoryMap, hasValue(name));
//        categoryId = (int) categoryMap.get("id");
//        System.out.println(categoryId);
//    }
//
//    @Title("Update the category information to the application and verify")
//    @Test
//    public void test003(){
//        name = name + "_updated";
//
//        CategoriesPojo categoriesPojo = new CategoriesPojo();
//        categoriesPojo.setID(ID);
//        categoriesPojo.setName(name);
//
//        SerenityRest.given().log().all()
//                .header("Content-Type", "application/json")
//                .pathParam("categoryID", categoryId)
//                .body(categoriesPojo)
//                .when()
//                .put(CategoryEndPoints.UPDATE_CATEGORY_BY_ID)
//                .then().log().all().statusCode(200);
//
//        String p1 = "data.findAll{it.name=='";
//        String p2 = "'}.get(0)";
//
//        HashMap<String,Object> categoryMap = SerenityRest.given().log().all()
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + name + p2);
//        Assert.assertThat(categoryMap, hasValue(name));
//        categoryId = (int) categoryMap.get("id");
//        System.out.println(categoryId);
//    }
//
//    @Title("Delete the category and verify if the category has been deleted")
//    @Test
//    public void test004(){
//        SerenityRest.given().log().all()
//                .pathParam("categoryId", categoryId)
//                .when()
//                .delete(CategoryEndPoints.DELETE_CATEGORY_BY_ID)
//                .then().statusCode(200);
//
//        SerenityRest.given().log().all()
//                .pathParam("categoryId", categoryId)
//                .when()
//                .get(CategoryEndPoints.GET_SINGLE_CATEGORY_BY_ID)
//                .then()
//                .statusCode(404);
//    }
//}
