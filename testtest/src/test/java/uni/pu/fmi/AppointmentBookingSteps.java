package uni.pu.fmi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uni.pu.fmi.appointment.Appointment;
import uni.pu.fmi.appointment.AppointmentBookingService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentBookingSteps {

    private final AppointmentBookingService appointmentBookingService = new AppointmentBookingService();

    private User user;

    private String beautyProcedure;

    private Appointment appointment;

    private Exception exception;

    private boolean isDeleted;

    private String[] procedures;

    private List<Appointment> appointments;

    @Given("A logged in user")
    public void a_logged_in_user() {
        user = new User();
    }

    @When("A user selects a beauty procedure")
    public void a_user_selects_a_beauty_procedure() {
        beautyProcedure = "Кичури";
    }

    @And("Enters valid details such as name, contact number, and preferred time slot")
    public void enters_valid_details_such_as_name_contract_number_and_preferred_time_slot() {
        appointment = appointmentBookingService.createAnAppointment(beautyProcedure, user, "Dimitar", "911", "02/06/2025");
    }

    @Then("The system should successfully book the appointment")
    public void the_system_should_successfully_book_the_appointment() {
        assertThat(appointment).isNotNull();
    }

    @Given("A logged in user attempting to book an appointment")
    public void a_logged_in_user_attempting_to_book_an_appointment() {
        beautyProcedure = "Кичури";
    }

    @When("The user enters invalid details such as missing name or incorrect contact number format")
    public void the_user_enters_invalid_details_such_as_missing_name_or_incorrect_contact_number_format() {
        try {
            appointmentBookingService.createAnAppointment(beautyProcedure, user, " ", "dwada", "dwadwa");
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("The system should display an error message indicating the required fields or format issues")
    public void theSystemShouldDisplayAnErrorMessageIndicatingTheRequiredFieldsOrFormatIssues() {
        assertThat(exception).hasMessage("Invalid name");
    }

    @Given("A logged in user trying to book an appointment")
    public void aLoggedInUserTryingToBookAnAppointment() {
        beautyProcedure = "Кичури";

    }

    @When("The selected time slot is already booked")
    public void theSelectedTimeSlotIsAlreadyBooked() {
        try {
            appointmentBookingService.createAnAppointment("Кичури", user, "Dimitar", "911", "13/04/2025");
            appointmentBookingService.createAnAppointment(beautyProcedure, user, "Dimitar", "911", "13/04/2025");
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("The system should inform the user that the chosen time slot is unavailable and offer alternative slots")
    public void theSystemShouldInformTheUserThatTheChosenTimeSlotIsUnavailableAndOfferAlternativeSlots() {
        assertThat(exception).hasMessage("Time slot is taken");
    }

    @Given("A logged in user with an existing appointment")
    public void aLoggedInUserWithAnExistingAppointment() {
        appointment = appointmentBookingService.createAnAppointment("Кичури", user, "Dimitar", "911", "19/04/2025");

    }

    @When("The user decides to cancel the appointment")
    public void theUserDecidesToCancelTheAppointment() {
        isDeleted = appointmentBookingService.cancelAppointment(appointment);
    }

    @Then("The system should successfully cancel the appointment and notify both the user and the salon staff accordingly")
    public void theSystemShouldSuccessfullyCancelTheAppointmentAndNotifyBothTheUserAndTheSalonStaffAccordingly() {
        assertThat(isDeleted).isTrue();
    }

    @When("The user selects both procedures during the booking process")
    public void theUserSelectsBothProceduresDuringTheBookingProcess() {
        procedures = new String[]{"Кичури", "Официален кок"};
    }

    @And("Provides valid details such as name, contact number, and preferred time slot")
    public void providesValidDetailsSuchAsNameContactNumberAndPreferredTimeSlot() {
        appointments = appointmentBookingService.createMultipleAppointments(user, "Dimitar", "911", "26/05/2025", procedures);
    }

    @Then("The system should allow the booking of multiple procedures within a single appointment")
    public void theSystemShouldAllowTheBookingOfMultipleProceduresWithinASingleAppointment() {
        assertThat(appointments).hasSize(2);
    }
}
