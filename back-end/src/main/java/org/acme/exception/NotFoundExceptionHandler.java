package org.acme.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErrorResponse;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Erro interno", exception.getMessage(),null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
