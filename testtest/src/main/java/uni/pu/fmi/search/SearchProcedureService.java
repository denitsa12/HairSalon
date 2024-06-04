package uni.pu.fmi.search;

import uni.pu.fmi.BeautyProcedure;
import uni.pu.fmi.BeautyProcedureRepository;

import java.util.List;

public class SearchProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository = new BeautyProcedureRepository();

    public List<BeautyProcedure> findProceduresByCategory(String category) {
        if (category == null) {
            return beautyProcedureRepository.getAllBeautyProcedures();
        } else {
            return beautyProcedureRepository.findBeautyProceduresByCategory(category);
        }
    }

    public BeautyProcedure findProcedureByName(String name) {
        return beautyProcedureRepository.findBeautyProcedureByName(name);
    }

    public List<BeautyProcedure> findProceduresByPrice(int price) {
        return beautyProcedureRepository.findBeautyProceduresByPrice(price);
    }
}
