package HouseWith.hwf.Exceptions.ExceptionHandler;

import HouseWith.hwf.Exceptions.RequestExceptioons.ArticleNotFoundException;
import HouseWith.hwf.Exceptions.RequestExceptioons.IllegalJoinStatusException;
import HouseWith.hwf.Exceptions.RequestExceptioons.IllegalParamException;
import HouseWith.hwf.Exceptions.RequestExceptioons.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalJoinStatusException.class)
    public ResponseEntity<ErrorResponse> handleIllegalJoinStatus(IllegalJoinStatusException e) {
        ErrorResponse response = new ErrorResponse("JOIN_OVERFLOW", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleArticleNotFound(IllegalJoinStatusException e) {
        ErrorResponse response = new ErrorResponse("ARTICLE_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalParamException.class)
    public ResponseEntity<ErrorResponse> handleIllegalParam(IllegalJoinStatusException e) {
        ErrorResponse response = new ErrorResponse("WRONG_PARAMETER", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFound(IllegalJoinStatusException e) {
        ErrorResponse response = new ErrorResponse("MEMBER_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
