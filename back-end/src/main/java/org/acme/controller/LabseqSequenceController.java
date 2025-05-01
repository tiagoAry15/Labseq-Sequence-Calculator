package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.response.LabseqSequenceResponseDTO;
import org.acme.service.LabseqSequenceService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/labseq")
public class LabseqSequenceController {

    @Inject
    LabseqSequenceService service;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns the number of the labseq sequence")
    public Response getLabseqSequence(@Parameter(description = "Non-negative index of the labseq sequence")
                                                        @Min(value = 0, message = "the index must be greater than or equal to 0")
                                                        @PathParam("n") Integer index) {
        long start = System.nanoTime();
        LabseqSequenceResponseDTO payload = service.getLabseqSequence(index);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;

        return Response.ok(payload)
                .header("X-Execution-Time-ms", elapsedMs)
                .build();
    }
}
