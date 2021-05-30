package me.rollingpaper.model.result;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JSONResult {
	private String result; // success, fail
    private String message; // if fail, set
    private Object data;
    
    public static JSONResult success(Object data) {
        return new JSONResult("success", null, data);
    }

    public static JSONResult success(Object data, String value) {
        return new JSONResult("success", value, data);
    }

    public static JSONResult fail(String message) {
        return new JSONResult("fail", message, null);
    }
    
    @Builder
    private JSONResult(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data; 
    }
}
