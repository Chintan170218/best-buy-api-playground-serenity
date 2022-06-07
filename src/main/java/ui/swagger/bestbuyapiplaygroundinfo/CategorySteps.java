package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.categoryconstants.CategoryEndPoints;
import ui.swagger.model.CategoriesPojo;


import java.util.HashMap;


public class CategorySteps {

    @Step("Creating category with ID : {0} and name : {1}")
    public ValidatableResponse createCategory(String id ,String name) {
        CategoriesPojo categoriesPoJo = new CategoriesPojo();
        categoriesPoJo.setName(name);
        categoriesPoJo.setId(id);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPoJo)
                .when()
                .post(CategoryEndPoints.GET_ALL_CATEGORIES)
                .then();
    }

    @Step("Getting category information with ID : {0} and name : {1}")
    public HashMap<String,?> getCategoryInfoById(String categoryId) {

        HashMap<String,?> categoryMap = SerenityRest.given().log().all()
                .when()
                .pathParam("categoryId",categoryId)
                .get(CategoryEndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return categoryMap;
    }

    @Step("Updating category information with categoryId: {0}, ID : {1} and name : {2}")
    public ValidatableResponse updateCategory(String categoryId, String name, String id){

        CategoriesPojo categoriesPoJo = new CategoriesPojo();
        categoriesPoJo.setName(name);
        categoriesPoJo.setId(id);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPoJo)
                .pathParam("categoryId",categoryId)
                .when()
                .patch(CategoryEndPoints.UPDATE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Deleting category information with categoryID: {0}")
    public ValidatableResponse deleteCategory (String categoryId) {

        return SerenityRest.given().log().all()
                .pathParam("categoryId", categoryId)
                .when()
                .delete(CategoryEndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting category information with categoryId: {0}")
    public ValidatableResponse getCategoryByID (String categoryId) {

        return SerenityRest.given().log().all()
                .pathParam("categoryId", categoryId)
                .when()
                .get(CategoryEndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();
    }
}
