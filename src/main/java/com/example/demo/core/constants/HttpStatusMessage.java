package com.example.demo.core.constants;

import org.springframework.http.HttpStatus;

/**
 * Enum đại diện cho các mã trạng thái HTTP cùng với mã thông báo tương ứng.
 * Giữ nguyên mã thông báo từ phiên bản TypeScript trong khi cung cấp chức năng enum trong Java.
 */
public enum HttpStatusMessage {

    // 1xx Phản hồi thông tin
    /**
     * 100 Continue - Máy chủ đã nhận được tiêu đề yêu cầu.
     */
    CONTINUE(HttpStatus.CONTINUE, "HTTP.INFO_CONTINUE"),

    /**
     * 101 Switching Protocols - Máy khách yêu cầu máy chủ chuyển đổi giao thức.
     */
    SWITCHING_PROTOCOLS(HttpStatus.SWITCHING_PROTOCOLS, "HTTP.INFO_SWITCH_PROTOCOL"),

    /**
     * 102 Processing - Máy chủ đang xử lý yêu cầu nhưng chưa có phản hồi.
     */
    PROCESSING(HttpStatus.PROCESSING, "HTTP.INFO_PROCESSING"),


    // 2xx Phản hồi thành công
    /**
     * 200 OK - Yêu cầu đã được thực hiện thành công.
     */
    OK(HttpStatus.OK, "HTTP.SUCCESS_OK"),

    /**
     * 201 Created - Yêu cầu đã được thực hiện và tài nguyên mới đã được tạo.
     */
    CREATED(HttpStatus.CREATED, "HTTP.SUCCESS_CREATED"),

    /**
     * 202 Accepted - Yêu cầu đã được chấp nhận nhưng chưa xử lý xong.
     */
    ACCEPTED(HttpStatus.ACCEPTED, "HTTP.SUCCESS_ACCEPTED"),

    /**
     * 204 No Content - Máy chủ xử lý yêu cầu thành công nhưng không trả về nội dung.
     */
    NO_CONTENT(HttpStatus.NO_CONTENT, "HTTP.SUCCESS_NO_CONTENT"),


    // 3xx Phản hồi điều hướng
    /**
     * 301 Moved Permanently - Tài nguyên đã được chuyển vĩnh viễn đến một địa chỉ mới.
     */
    MOVED_PERMANENTLY(HttpStatus.MOVED_PERMANENTLY, "HTTP.REDIRECT_MOVED_PERM"),

    /**
     * 302 Found - Tài nguyên đã được di chuyển tạm thời đến một địa chỉ khác.
     */
    FOUND(HttpStatus.FOUND, "HTTP.REDIRECT_FOUND"),

    /**
     * 304 Not Modified - Tài nguyên không thay đổi kể từ lần yêu cầu cuối cùng.
     */
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "HTTP.REDIRECT_NOT_MODIFIED"),

    /**
     * 307 Temporary Redirect - Tài nguyên tạm thời có sẵn tại một vị trí khác.
     */
    TEMPORARY_REDIRECT(HttpStatus.TEMPORARY_REDIRECT, "HTTP.REDIRECT_TEMP"),


    // 4xx Phản hồi lỗi từ phía khách hàng
    /**
     * 400 Bad Request - Yêu cầu không hợp lệ do lỗi từ phía máy khách.
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "HTTP.BAD_REQUEST"),

    /**
     * 401 Unauthorized - Yêu cầu cần xác thực nhưng không được cung cấp hoặc thất bại.
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "HTTP.UNAUTHORIZED"),

    /**
     * 403 Forbidden - Máy chủ hiểu yêu cầu nhưng từ chối xử lý.
     */
    FORBIDDEN(HttpStatus.FORBIDDEN, "HTTP.FORBIDDEN"),

    /**
     * 404 Not Found - Không tìm thấy tài nguyên được yêu cầu.
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "HTTP.NOT_FOUND"),

    /**
     * 405 Method Not Allowed - Phương thức yêu cầu không được hỗ trợ cho tài nguyên này.
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "HTTP.METHOD_NOT_ALLOWED"),

    /**
     * 408 Request Timeout - Máy chủ chờ quá lâu nhưng không nhận được yêu cầu.
     */
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "HTTP.REQUEST_TIMEOUT"),

    /**
     * 409 Conflict - Yêu cầu xung đột với trạng thái hiện tại của máy chủ.
     */
    CONFLICT(HttpStatus.CONFLICT, "HTTP.CONFLICT"),

    /**
     * 429 Too Many Requests - Máy khách đã gửi quá nhiều yêu cầu trong một khoảng thời gian.
     */
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "HTTP.TOO_MANY_REQUESTS"),


    // 5xx Phản hồi lỗi từ phía máy chủ
    /**
     * 500 Internal Server Error - Lỗi chung của máy chủ.
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "HTTP.INTERNAL_SERVER_ERROR"),

    /**
     * 501 Not Implemented - Máy chủ không hỗ trợ tính năng cần thiết.
     */
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "HTTP.NOT_IMPLEMENTED"),

    /**
     * 502 Bad Gateway - Máy chủ nhận phản hồi không hợp lệ từ máy chủ trung gian.
     */
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "HTTP.BAD_GATEWAY"),

    /**
     * 503 Service Unavailable - Máy chủ hiện không thể xử lý yêu cầu.
     */
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "HTTP.SERVICE_UNAVAILABLE"),

    /**
     * 504 Gateway Timeout - Máy chủ không nhận được phản hồi kịp thời từ máy chủ trung gian.
     */
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "HTTP.GATEWAY_TIMEOUT"),

    /**
     * 400 Không tìm thấy dữ liệu trong database
     */
    DATA_NOT_FOUND(HttpStatus.BAD_REQUEST, "DATA.DATA_NOT_FOUND"),


    ;



    /**
     * -- GETTER --
     * Lấy mã trạng thái HTTP.
     */
    public final HttpStatus statusCode;

    /**
     * -- GETTER --
     * Lấy mã thông báo (tương ứng với phiên bản TypeScript).
     */
    public final String messageCode;

    /**
     * Khởi tạo một hằng số Enum HttpStatusMessage.
     *
     * @param statusCode        mã trạng thái HTTP
     * @param messageCode mã thông báo (tương ứng với phiên bản TypeScript)
     */
    HttpStatusMessage(HttpStatus statusCode, String messageCode) {
        this.statusCode = statusCode;
        this.messageCode = messageCode;
    }

    /**
     * Tìm kiếm một HttpStatusMessage theo mã trạng thái.
     *
     * @param code mã trạng thái HTTP cần tìm
     * @return Enum HttpStatusMessage tương ứng
     * @throws IllegalArgumentException nếu không tìm thấy mã trạng thái phù hợp
     */
    public static HttpStatusMessage fromCode(HttpStatus code) {
        for (HttpStatusMessage status : values()) {
            if (status.statusCode.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy HttpStatusMessage cho mã: " + code);
    }
}
