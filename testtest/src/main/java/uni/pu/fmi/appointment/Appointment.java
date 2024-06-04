package uni.pu.fmi.appointment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uni.pu.fmi.BeautyProcedure;
import uni.pu.fmi.User;

@RequiredArgsConstructor
@Getter
public class Appointment {

    public final BeautyProcedure beautyProcedure;

    private final User user;

    private final String name;

    private final String contactNumber;

    private final String preferredTimeSlot;
}
