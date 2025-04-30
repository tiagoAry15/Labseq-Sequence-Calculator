package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.response.LabseqSequenceResponse;

@ApplicationScoped
public class LabseqSequenceService {


    public LabseqSequenceResponse getLabseqSequence(Integer number){
        if(number < 0) throw new IllegalArgumentException("Number cannot be negative");
        return new LabseqSequenceResponse(calculateLabseq(number));
    };

    private int calculateLabseq(Integer number){
        return 1;
    };
}
