package org.acme.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErrorResponseDTO;

@Provider
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {


    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponseDTO("Invalid value", e.getMessage(),null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
