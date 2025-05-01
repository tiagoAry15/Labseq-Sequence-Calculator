package org.acme.dto.response;

public record ErrorResponseDTO(String error, String message, String details) {
    public ErrorResponseDTO {
        if (error == null || message == null) {
            throw new IllegalArgumentException("Error and message cannot be null");
        }
    }
}
