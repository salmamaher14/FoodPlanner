package planned_days.view;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;

public interface PlanningDaysView {
    public void showPlanningDays(Flowable<List<String> >days);
    public void showErrMsg(String error);
}
