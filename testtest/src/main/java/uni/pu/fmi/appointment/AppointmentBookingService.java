package uni.pu.fmi.appointment;

import uni.pu.fmi.BeautyProcedure;
import uni.pu.fmi.BeautyProcedureRepository;
import uni.pu.fmi.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentBookingService {

    private final BeautyProcedureRepository beautyProcedureRepository = new BeautyProcedureRepository();

    private final List<Appointment> appointments = new ArrayList<>();

    public Appointment createAnAppointment(String procedure, User user, String name, String contactNumber, String preferredTimeSlot) {
        validate(name, contactNumber, preferredTimeSlot);
        BeautyProcedure beautyProcedure = beautyProcedureRepository.findBeautyProcedureByName(procedure);
        user.bookAProcedure(beautyProcedure);
        Appointment appointment = new Appointment(beautyProcedure, user, name, contactNumber, preferredTimeSlot);
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> createMultipleAppointments(User user, String name, String contactNumber, String preferredTimeSlot, String... procedures) {
        return Arrays.stream(procedures)
                .map(procedure -> {
                    BeautyProcedure beautyProcedure = beautyProcedureRepository.findBeautyProcedureByName(procedure);
                    user.bookAProcedure(beautyProcedure);
                    Appointment appointment = new Appointment(beautyProcedure, user, name, contactNumber, preferredTimeSlot);
                    appointments.add(appointment);
                    return appointment;
                })
                .toList();
    }

    public boolean cancelAppointment(Appointment appointment) {
        return appointments.remove(appointment);
    }

    private void validate(String name, String contactNumber, String preferredTimeSlot) {
        if (name.isBlank()) {
            throw new IllegalStateException("Invalid name");
        }
        if (!isSlotAvailable(preferredTimeSlot)) {
            throw new IllegalStateException("Time slot is taken");
        }
    }

    private boolean isSlotAvailable(String preferredTimeSlot) {
        return appointments.stream().noneMatch(appointment -> appointment.getPreferredTimeSlot().equals(preferredTimeSlot));
    }
}
