package ui.swagger.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import ui.swagger.categoryconstants.CategoryPath;
import ui.swagger.productconstants.ProductPath;
import ui.swagger.serviceconstants.ServicePath;
import ui.swagger.storeconstants.StorePath;
import ui.swagger.utils.PropertyReader;


public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        //RestAssured.basePath = ProductPath.PRODUCT;
    }

}
