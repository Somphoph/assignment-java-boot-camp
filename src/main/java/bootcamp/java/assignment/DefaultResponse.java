package bootcamp.java.assignment;

import lombok.Data;

@Data
public class DefaultResponse {
    private String message;

    public DefaultResponse(String message) {
        this.message = message;
    }
}
