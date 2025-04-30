package org.acme.dto.response;

import java.sql.Timestamp;

public record LabseqSequenceResponse(Integer number, Timestamp timestamp) {
    public LabseqSequenceResponse {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");
        }
    }
}
