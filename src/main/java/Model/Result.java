package Model;

public class Result<T> {
    private T res = null;
    private ResultStatus status = ResultStatus.success;
    private String message = null;

    public Result(ResultStatus status, T res, String message) {
        this.res = res;
        this.status = status;
        this.message = message;
    }

    public Result(ResultStatus status, T res) {
        this.status = status;
        this.res = res;
    }

    public Result(ResultStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
