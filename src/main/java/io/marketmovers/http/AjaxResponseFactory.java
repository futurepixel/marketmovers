package io.marketmovers.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by mikechabot on 10/28/15.
 */
public class AjaxResponseFactory {

    /**
     * This represents the status of the business request:
     *    Success: The HTTP and business request were successful
     *       Fail: The HTTP request was successful, but the business request encountered a failure condition (e.g. failed validation)
     *      Error: Either the HTTP or business request encountered an error condition (e.g. MongoClientException, 401 Bad Request)
     */
    private enum ResponseStatus {
        SUCCESS, FAIL, ERROR
    }

    public static ResponseEntity success() {
        return getResponseEntity(HttpStatus.OK, ResponseStatus.SUCCESS, null, null, null);
    }

    public static ResponseEntity success (String message) {
        return getResponseEntity(HttpStatus.OK, ResponseStatus.SUCCESS, message, null, null);
    }

    public static ResponseEntity success (String message, Object data) {
        return getResponseEntity(HttpStatus.OK, ResponseStatus.SUCCESS, message, data, null);
    }

    public static ResponseEntity success (String message, Object data, HttpHeaders headers) {
        return getResponseEntity(HttpStatus.OK, ResponseStatus.SUCCESS, message, data, headers);
    }

    public static ResponseEntity fail (String message) {
        return getResponseEntity(HttpStatus.OK, ResponseStatus.FAIL, message, null, null);
    }

    public static ResponseEntity error (String message) {
        return getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ResponseStatus.ERROR, message, null, null);
    }

    /**
     * Wrap a JsonResponse object in a ResponseEntity object; the latter is unpacked
     * by the DataAccessService in the browser, at which point the former is provided
     * to the specific service that generated the request (i.e. StockService, StatisticsService)
     *
     * @param httpStatus
     * @param responseStatus
     * @param message
     * @param data
     * @param headers
     * @return
     */
    private static ResponseEntity getResponseEntity(HttpStatus httpStatus, ResponseStatus responseStatus, String message, Object data, HttpHeaders headers) {

        if (httpStatus == null) throw new IllegalArgumentException("HttpStatus cannot be null");
        if (responseStatus == null) throw new IllegalArgumentException("ResponseStatus cannot be null");

        JsonResponse jsonResponse = new JsonResponse(responseStatus, message, data);
        return new ResponseEntity(jsonResponse, headers, httpStatus);
    }

    /**
     * This is the AJAX response sent back to the browser
     */
    private static class JsonResponse {

        private ResponseStatus status;
        private String message;
        private Object data;

        public JsonResponse(ResponseStatus status, String message, Object data) {
            if (status == null) throw new IllegalArgumentException("ResponseStatus cannot be null");
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public ResponseStatus getStatus() {
            return status;
        }

        public void setStatus(ResponseStatus status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

    }

}
