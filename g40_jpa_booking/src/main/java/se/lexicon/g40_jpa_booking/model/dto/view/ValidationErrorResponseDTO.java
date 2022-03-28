package se.lexicon.g40_jpa_booking.model.dto.view;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponseDTO extends ExceptionResponseDTO{

    private Map<String, String> violations;

    public ValidationErrorResponseDTO(ExceptionResponseDTO dto,Map<String, String> violations) {
        super(dto.getTimestamp(), dto.getStatus(), dto.getError(), dto.getMessage(), dto.getPath());
        this.violations = violations;
    }

    public ValidationErrorResponseDTO(LocalDateTime timestamp, Integer status, String error, String message, String path, Map<String, String> violations) {
        super(timestamp, status, error, message, path);
        this.violations = violations;
    }

    public Map<String, String> getViolations() {
        return violations;
    }

    public void setViolations(Map<String, String> violations) {
        this.violations = violations;
    }
}
