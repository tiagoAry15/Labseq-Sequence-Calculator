package org.acme.dto.response;

public record LabseqSequenceResponse(Integer number) {
    public LabseqSequenceResponse {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");
        }
    }
}
