package com.escom.Creadordecasos.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Data
public class Response<T> {
    private boolean succeeded;
    private String message;
    private List<String> errors;
    private T data;
    private String sessionToken; // Atributo para el token de sesión
    private String multimediaUrl; // URL para contenido multimedia

    public Response(boolean succeeded, String message, List<String> errors, T data) {
        this.succeeded = succeeded;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    @ResponseStatus(HttpStatus.OK)
    public static <T> Response<T> success(T data, String message) {
        return new Response<>(true, message, null, data);
    }

    // Métodos para respuestas con código HTTP 400 (Bad Request)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static <T> Response<T> badRequest(String message, List<String> errors) {
        return new Response<>(false, message, errors, null);
    }

    // Métodos para respuestas con código HTTP 401 (Unauthorized)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static <T> Response<T> unauthorized(String message) {
        return new Response<>(false, message, null, null);
    }

    // Métodos para respuestas con código HTTP 403 (Forbidden)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static <T> Response<T> forbidden(String message) {
        return new Response<>(false, message, null, null);
    }

    // Métodos para respuestas con código HTTP 404 (Not Found)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static <T> Response<T> notFound(String message) {
        return new Response<>(false, message, null, null);
    }

    // Métodos para respuestas con código HTTP 500 (Internal Server Error)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static <T> Response<T> internalServerError(String message) {
        return new Response<>(false, message, null, null);
    }
}