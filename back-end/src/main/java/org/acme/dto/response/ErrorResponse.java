package org.acme.dto.response;

public record ErrorResponse(String error, String message, String details) {
    public ErrorResponse {
        if (error == null || message == null) {
            throw new IllegalArgumentException("Error and message cannot be null");
        }
    }
}
