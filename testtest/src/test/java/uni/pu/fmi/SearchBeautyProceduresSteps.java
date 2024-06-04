package uni.pu.fmi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uni.pu.fmi.search.SearchProcedureService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBeautyProceduresSteps {

    private final SearchProcedureService searchProcedureService = new SearchProcedureService();

    private List<BeautyProcedure> procedures;

    private BeautyProcedure procedure;

    @Then("Visualises a list with categories")
    public void visualises_a_list_with_categories() {
        procedures = searchProcedureService.findProceduresByCategory(null);
    }

    @When("The user chooses a category {string}")
    public void the_user_chooses_a_category(String category) {
        procedures = searchProcedureService.findProceduresByCategory(category);
    }

    @Then("Visualises a list with the {int} available procedures")
    public void visualises_a_list_with_available_procedures(int numberOfProcedures) {
        assertThat(procedures).hasSize(numberOfProcedures);
    }

    @When("The user searches for a {string} in the search box")
    public void the_user_searches_for_a_procedure_in_the_search_box(String procedure) {
        this.procedure = searchProcedureService.findProcedureByName(procedure);
    }

    @Then("Visualises a list with the available procedure {string} and {int}")
    public void visualises_a_list_with_available_procedure(String procedure, int price) {
        assertThat(this.procedure.getName()).isEqualTo(procedure);
        assertThat(this.procedure.getPrice()).isEqualTo(price);
    }

    @When("The user enters a price of the procedure in the search box")
    public void the_user_enters_a_price_of_the_procedure_in_the_search_box() {
        procedures = searchProcedureService.findProceduresByPrice(39);
    }
}
