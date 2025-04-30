package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ResponseDTO;
import org.acme.service.LabseqSequenceService;

@Path("/labseq")
public class LabseqSequenceController {

    @Inject
    LabseqSequenceService service;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO getLabseqSequence(@PathParam( "n") Integer number) {
        return service.getLabseqSequence(number);
    }
}
