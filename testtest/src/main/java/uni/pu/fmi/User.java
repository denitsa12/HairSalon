package uni.pu.fmi;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<BeautyProcedure> bookedProcedures = new ArrayList<>();

    public void bookAProcedure(BeautyProcedure beautyProcedure) {
        bookedProcedures.add(beautyProcedure);
    }

    public void removeBookedProcedure(BeautyProcedure beautyProcedure) {
        bookedProcedures.remove(beautyProcedure);
    }

    public List<BeautyProcedure> getAllBookedProcedures() {
        return bookedProcedures;
    }
}
