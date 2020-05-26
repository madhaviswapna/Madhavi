package Stepdefination;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Stepdefinations {

    @Given("^User on Landing page$")
    public void user_on_landing_page() throws Throwable {
    }

    @When("^User Login into Application with uid and pwd$")
    public void user_login_into_application_with_uid_and_pwd() throws Throwable {
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() throws Throwable {
    }

    @And("^Cards are displayed$")
    public void cards_are_displayed() throws Throwable {}

}