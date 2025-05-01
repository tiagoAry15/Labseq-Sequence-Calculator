package org.acme.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.dto.response.ErrorResponseDTO;

import java.util.concurrent.CompletionException;

@Provider
public class RedisConnectionExceptionHandler implements ExceptionMapper<CompletionException> {

    @Override
    public Response toResponse(CompletionException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new ErrorResponseDTO("unable to connect to the cache server", exception.getMessage(),null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
