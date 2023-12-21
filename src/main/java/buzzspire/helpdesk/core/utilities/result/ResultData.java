package buzzspire.helpdesk.core.utilities.result;

public class ResultData<T> extends Result {
    private T data;

    public ResultData(T data, String message) {
        super(true, message);
        this.data = data;
    }

    public ResultData(T data, String message, boolean success) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}