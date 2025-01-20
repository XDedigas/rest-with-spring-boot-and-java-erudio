package br.com.erudio.rest_with_spring_boot_and_java_erudio.integrationtests.swagger;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.configs.TestConfigs;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body().asString();
		assertTrue(content.contains("Swagger UI"));
	}
}
