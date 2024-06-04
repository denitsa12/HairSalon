package uni.pu.fmi;

import java.util.ArrayList;
import java.util.List;

public class BeautyProcedureRepository {

    private final List<BeautyProcedure> beautyProcedures = new ArrayList<>();

    public BeautyProcedureRepository() {
        beautyProcedures.add(new BeautyProcedure("Официален кок", "Прическа", 50));
        beautyProcedures.add(new BeautyProcedure("Не-официален кок", "Прическа", 30));
        beautyProcedures.add(new BeautyProcedure("Кичури", "Боядисване", 15));
        beautyProcedures.add(new BeautyProcedure("Къса коса", "Подстрижка", 39));
        beautyProcedures.add(new BeautyProcedure("Корени", "Подстрижка", 39));
        beautyProcedures.add(new BeautyProcedure("Бръснене", "Подстрижка", 39));
    }

    public List<BeautyProcedure> getAllBeautyProcedures() {
        return beautyProcedures;
    }

    public BeautyProcedure findBeautyProcedureByName(String name) {
        return beautyProcedures.stream()
                .filter(procedure -> procedure.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    public List<BeautyProcedure> findBeautyProceduresByCategory(String category) {
        return beautyProcedures.stream()
                .filter(beautyProcedure -> beautyProcedure.getCategory().equals(category))
                .toList();
    }

    public List<BeautyProcedure> findBeautyProceduresByPrice(int price) {
        return beautyProcedures.stream()
                .filter(beautyProcedure -> beautyProcedure.getPrice() == price)
                .toList();
    }
}
