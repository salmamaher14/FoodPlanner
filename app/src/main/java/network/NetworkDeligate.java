package network;
import java.util.List;
import model.Category;
public interface NetworkDeligate<T> {
    void onSuccessResult(List<T> data);
    void onFailureResult(String errorMsg);
}

