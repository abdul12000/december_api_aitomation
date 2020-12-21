package Step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import utilities.RequestBodyServices;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocialNetworkingStepDef extends BaseSteps{
    Response responseForGetServiceCall, responseForGetPostRequest, responseForPostCall;
    RequestBodyServices requestBodyServices;
    DocumentContext requestBody;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        responseForGetServiceCall = getResponse();
        //Missing in the last class
        assertThat(responseForGetServiceCall.statusCode(), Is.is(200));
    }

    @When("i search for {string} of a post with a GET method")
    public void i_search_for_of_a_post_with_a_get_method(String id) {
       setHeadersWithContentType();
       setEndpointPath(makeAPostEndpoint + id);
       getCall();
        responseForGetPostRequest = getResponse();
    }
    @Then("i should get the correct {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String title, String body, Integer statusCode) {
     assertThat(responseForGetPostRequest.statusCode(), is(statusCode));
     assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString(), is(id));
        assertThat(responseForGetPostRequest.body().jsonPath().get("title").toString(), is(title));
        assertThat(responseForGetPostRequest.body().jsonPath().get("body").toString(), is(body));
    }

    @When("I create a new post with the following details {string},{string} and {string},")
    public void iCreateANewPostWithTheFollowingDetailsAnd(String uId, String title, String body) {
        requestBodyServices = new RequestBodyServices();
        setHeadersWithContentType();
        requestBody = loadJsonTemplate(MakeAPostPayload);
        requestBodyServices.setRequestBodyForNewPost(requestBody,uId,title,body);
//        setEndpointPath(MakeACommentPayload);  Mistake from the last class.
        setEndpointPath(makeAPostEndpoint);
        getPostCall();
        responseForPostCall = getResponse();
    }

    @Then("i should get the correct {string},{string} and {string} returned and with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOf(String uId, String title, String body, int statusCode) {
assertThat(responseForPostCall.statusCode(), is (statusCode));
        assertThat(responseForPostCall.body().jsonPath().get("userId"), is (uId));
        assertThat(responseForPostCall.body().jsonPath().get("title"), is (title));
        assertThat(responseForPostCall.body().jsonPath().get("body"), is (body));
    }

    @When("I create a new comment with the following details {string},{string}, {string} and {string},")
    public void iCreateANewCommentWithTheFollowingDetailsAnd(String arg0, String arg1, String arg2, String arg3) {
    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOf(String arg0, String arg1, String arg2, String arg3, int arg4) {
    }
}
