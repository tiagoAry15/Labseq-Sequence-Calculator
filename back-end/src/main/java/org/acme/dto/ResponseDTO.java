package org.acme.dto;

public record ResponseDTO(Integer number) {
    public ResponseDTO {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");
        }
    }
}
