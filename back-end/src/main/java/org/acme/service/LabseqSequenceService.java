package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ResponseDTO;

@ApplicationScoped
public class LabseqSequenceService {


    public ResponseDTO getLabseqSequence(Integer number){
        if(number < 0) throw new IllegalArgumentException("Number cannot be negative");
        return new ResponseDTO(calculateLabseq(number));
    };

    private int calculateLabseq(Integer number){
        return 1;
    };
}
