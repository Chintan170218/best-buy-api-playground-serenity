package ui.swagger.bestbuyapiplaygroundinfo;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.model.ProductPojo;
import ui.swagger.productconstants.ProductEndPoints;

import java.util.HashMap;


public class ProductSteps {

    @Step("Creating product with name : {0}, type: {1}, price: {2}, shipping: {3}, upc: {4}," +
            " description: {5}, manufacturer: {6}, model: {7}, url: {8} and image: {9}")
    public ValidatableResponse createProduct
            (String name, String type, int price, Integer shipping, String upc,
             String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(ProductEndPoints.GET_ALL_PRODUCT)
                .then();
    }

    @Step("Getting product information with Id : {0}")
    public HashMap<String, ?> getProductInfoById(int productId) {

        HashMap<String,?> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("productId",productId)
                .get(ProductEndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return productMap;
    }

    @Step("Updating product information with productId: {0}, name : {1}, type: {2}, price: {3}, shipping: {4}, upc: {5}," +
            "description: {6}, manufacturer: {7}, model: {8}, url: {9} and image: {10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, int price, Integer shipping, String upc,
                                             String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .pathParam("productId",productId)
                .when()
                .patch(ProductEndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct (int productId) {

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(ProductEndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information with productId: {0}")
    public ValidatableResponse getProductByID (int productId) {

        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(ProductEndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }
}
