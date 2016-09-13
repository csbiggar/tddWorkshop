package swagger

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter
import spock.lang.Specification

import static io.restassured.RestAssured.given

/***
 * Validate swagger spec against real API call using
 *
 * https://bitbucket.org/atlassian/swagger-request-validator
 *
 * with rest-assured integration
 *
 * https://bitbucket.org/atlassian/swagger-request-validator/src/a482670612498e9c243bc720f97908975c8d31f3/swagger-request-validator-examples/src/test/java/com/atlassian/oai/validator/examples/restassured/SwaggerValidationFilterTestExample.java?at=master&fileviewer=file-view-default
 */
class SwaggerValidationSpec extends Specification {

    private final SwaggerValidationFilter validationFilter = new SwaggerValidationFilter('put link to swagger spec here - raw file on github works');

    def 'expect api call to match spec'() {
        expect:

        given()
                .relaxedHTTPSValidation()
                .filter(validationFilter)
                .header("User-Agent", "Wget/1.12 (linux-gnu)")

        .when()
        .get('put api url here including ')
        .then()
        .statusCode(200)
    }
}
