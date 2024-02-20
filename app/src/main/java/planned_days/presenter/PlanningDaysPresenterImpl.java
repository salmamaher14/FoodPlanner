package planned_days.presenter;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;
import model.FoodRepositoryImpl;
import model.PlannedMeal;
import planned_days.view.PlanningDaysView;

public class PlanningDaysPresenterImpl implements PlanningDaysPresenter {

    PlanningDaysView _view;
    FoodRepositoryImpl _repo;


    public PlanningDaysPresenterImpl(PlanningDaysView _view, FoodRepositoryImpl _repo) {
        this._view = _view;
        this._repo = _repo;
    }




//    @Override
//    public void getPlannedMeals() {
//        Flowable<List<PlannedMeal>> plannedMeals =_repo.getPlannedMeals();
//        _view.showPlannedMeals(plannedMeals);
//
//    }

    @Override
    public void getAllPlanningDayes() {
        Flowable<List<String>>plannedDates=_repo.getPlannedDays();
        _view.showPlanningDays(plannedDates);

    }


}
