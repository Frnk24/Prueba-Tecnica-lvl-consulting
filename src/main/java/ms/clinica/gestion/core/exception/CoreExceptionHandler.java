package ms.clinica.gestion.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequestException(BadRequestException exception){
        log.warn(buildMessageLog(HttpStatus.BAD_REQUEST, exception.getMessage()));
        return new ExceptionResponse("404", exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException exception){
        log.warn(buildMessageLog(HttpStatus.NOT_FOUND, exception.getMessage()));
        return new ExceptionResponse("404", exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public  ExceptionResponse forbiddenException(ForbiddenException exception){
        log.warn(buildMessageLog(HttpStatus.FORBIDDEN, exception.getMessage()));
        return new ExceptionResponse("403", exception.getMessage());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse integrationException(IntegrationException exception){
        log.warn(buildMessageLog(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
        return new ExceptionResponse("500", exception.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse internalException(InternalException exception){
        log.warn(buildMessageLog(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
        return new ExceptionResponse("500", exception.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExceptionResponse noContentException(NoContentException exception){
        log.warn(buildMessageLog(HttpStatus.NO_CONTENT, exception.getMessage()));
        return new ExceptionResponse("204", exception.getMessage());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionResponse serviceUnavailableException(ServiceUnavailableException exception){
        log.warn(buildMessageLog(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage()));
        return new ExceptionResponse("503", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse exception(Exception exception){
        log.warn(buildMessageLog(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
        return new ExceptionResponse("500","Error interno", exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse unaUthorizedException(UnauthorizedException exception){
        log.warn(buildMessageLog(HttpStatus.UNAUTHORIZED, exception.getMessage()));
        return new ExceptionResponse("401", exception.getMessage());
    }

    private String buildMessageLog(HttpStatus status, String message){
        return String.format("Response Api <%d> <%s>", status.value(), message);
    }
}
