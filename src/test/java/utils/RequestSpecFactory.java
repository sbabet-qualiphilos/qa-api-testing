package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RequestSpecFactory {
    private static RequestSpecification requestSpec;
    

    public static RequestSpecification getRequestSpecification() throws IOException {
        Properties properties = new Properties();
        String BASE_URL;
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
            BASE_URL = properties.getProperty("base.url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .build();
        }
        return requestSpec;
    }
}
