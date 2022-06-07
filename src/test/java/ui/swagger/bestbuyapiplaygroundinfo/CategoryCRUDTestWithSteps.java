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
public class CategoryCRUDTestWithSteps extends TestBase {


    static String name = "Gift Cards" + TestUtils.getRandomValue();
    static String id = "abcat0010001"+ TestUtils.getRandomValue();
    static String categoryId;

    @Steps
    CategorySteps categorySteps;

    @Title("This test will create a new category")
    @Test
    public void test001() {

        ValidatableResponse response = categorySteps.createCategory(name, id);
        response.log().all().statusCode(201);
        categoryId = response.log().all().extract().path("id");
        System.out.println(categoryId);
    }


    @Title("Verify the category was added to the application")
    @Test
    public void test002() {

        HashMap<String, ?> categoryMap = categorySteps.getCategoryInfoById(categoryId);
        Assert.assertThat(categoryMap, hasValue(categoryId));
        System.out.println(categoryMap);
    }


    @Title("Update the category information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        categorySteps.updateCategory(categoryId, id, name).log().all().statusCode(200);
        HashMap<String, ?> categoryMap = categorySteps.getCategoryInfoById(categoryId);
        Assert.assertThat(categoryMap, hasValue(categoryId));
    }


    @Title("Delete the category and verify if the category has been deleted")
    @Test
    public void test004() {
        categorySteps.deleteCategory(categoryId).statusCode(200);

        categorySteps.getCategoryByID(categoryId).statusCode(404);
    }
}
