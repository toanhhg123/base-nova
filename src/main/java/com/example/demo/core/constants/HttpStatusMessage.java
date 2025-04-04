package com.example.demo.core.constants;

/**
 * Enum đại diện cho các mã trạng thái HTTP cùng với mã thông báo tương ứng.
 * Giữ nguyên mã thông báo từ phiên bản TypeScript trong khi cung cấp chức năng enum trong Java.
 */
public enum HttpStatusMessage {

    // 1xx Phản hồi thông tin
    /**
     * 100 Continue - Máy chủ đã nhận được tiêu đề yêu cầu.
     */
    CONTINUE(100, "HTTP.INFO_CONTINUE"),

    /**
     * 101 Switching Protocols - Máy khách yêu cầu máy chủ chuyển đổi giao thức.
     */
    SWITCHING_PROTOCOLS(101, "HTTP.INFO_SWITCH_PROTOCOL"),

    /**
     * 102 Processing - Máy chủ đang xử lý yêu cầu nhưng chưa có phản hồi.
     */
    PROCESSING(102, "HTTP.INFO_PROCESSING"),


    // 2xx Phản hồi thành công
    /**
     * 200 OK - Yêu cầu đã được thực hiện thành công.
     */
    OK(200, "HTTP.SUCCESS_OK"),

    /**
     * 201 Created - Yêu cầu đã được thực hiện và tài nguyên mới đã được tạo.
     */
    CREATED(201, "HTTP.SUCCESS_CREATED"),

    /**
     * 202 Accepted - Yêu cầu đã được chấp nhận nhưng chưa xử lý xong.
     */
    ACCEPTED(202, "HTTP.SUCCESS_ACCEPTED"),

    /**
     * 204 No Content - Máy chủ xử lý yêu cầu thành công nhưng không trả về nội dung.
     */
    NO_CONTENT(204, "HTTP.SUCCESS_NO_CONTENT"),


    // 3xx Phản hồi điều hướng
    /**
     * 301 Moved Permanently - Tài nguyên đã được chuyển vĩnh viễn đến một địa chỉ mới.
     */
    MOVED_PERMANENTLY(301, "HTTP.REDIRECT_MOVED_PERM"),

    /**
     * 302 Found - Tài nguyên đã được di chuyển tạm thời đến một địa chỉ khác.
     */
    FOUND(302, "HTTP.REDIRECT_FOUND"),

    /**
     * 304 Not Modified - Tài nguyên không thay đổi kể từ lần yêu cầu cuối cùng.
     */
    NOT_MODIFIED(304, "HTTP.REDIRECT_NOT_MODIFIED"),

    /**
     * 307 Temporary Redirect - Tài nguyên tạm thời có sẵn tại một vị trí khác.
     */
    TEMPORARY_REDIRECT(307, "HTTP.REDIRECT_TEMP"),


    // 4xx Phản hồi lỗi từ phía khách hàng
    /**
     * 400 Bad Request - Yêu cầu không hợp lệ do lỗi từ phía máy khách.
     */
    BAD_REQUEST(400, "HTTP.BAD_REQUEST"),

    /**
     * 401 Unauthorized - Yêu cầu cần xác thực nhưng không được cung cấp hoặc thất bại.
     */
    UNAUTHORIZED(401, "HTTP.UNAUTHORIZED"),

    /**
     * 403 Forbidden - Máy chủ hiểu yêu cầu nhưng từ chối xử lý.
     */
    FORBIDDEN(403, "HTTP.FORBIDDEN"),

    /**
     * 404 Not Found - Không tìm thấy tài nguyên được yêu cầu.
     */
    NOT_FOUND(404, "HTTP.NOT_FOUND"),

    /**
     * 405 Method Not Allowed - Phương thức yêu cầu không được hỗ trợ cho tài nguyên này.
     */
    METHOD_NOT_ALLOWED(405, "HTTP.METHOD_NOT_ALLOWED"),

    /**
     * 408 Request Timeout - Máy chủ chờ quá lâu nhưng không nhận được yêu cầu.
     */
    REQUEST_TIMEOUT(408, "HTTP.REQUEST_TIMEOUT"),

    /**
     * 409 Conflict - Yêu cầu xung đột với trạng thái hiện tại của máy chủ.
     */
    CONFLICT(409, "HTTP.CONFLICT"),

    /**
     * 429 Too Many Requests - Máy khách đã gửi quá nhiều yêu cầu trong một khoảng thời gian.
     */
    TOO_MANY_REQUESTS(429, "HTTP.TOO_MANY_REQUESTS"),


    // 5xx Phản hồi lỗi từ phía máy chủ
    /**
     * 500 Internal Server Error - Lỗi chung của máy chủ.
     */
    INTERNAL_SERVER_ERROR(500, "HTTP.INTERNAL_SERVER_ERROR"),

    /**
     * 501 Not Implemented - Máy chủ không hỗ trợ tính năng cần thiết.
     */
    NOT_IMPLEMENTED(501, "HTTP.NOT_IMPLEMENTED"),

    /**
     * 502 Bad Gateway - Máy chủ nhận phản hồi không hợp lệ từ máy chủ trung gian.
     */
    BAD_GATEWAY(502, "HTTP.BAD_GATEWAY"),

    /**
     * 503 Service Unavailable - Máy chủ hiện không thể xử lý yêu cầu.
     */
    SERVICE_UNAVAILABLE(503, "HTTP.SERVICE_UNAVAILABLE"),

    /**
     * 504 Gateway Timeout - Máy chủ không nhận được phản hồi kịp thời từ máy chủ trung gian.
     */
    GATEWAY_TIMEOUT(504, "HTTP.GATEWAY_TIMEOUT");

    /**
     * -- GETTER --
     * Lấy mã trạng thái HTTP.
     */
    public final int code;

    /**
     * -- GETTER --
     * Lấy mã thông báo (tương ứng với phiên bản TypeScript).
     */
    public final String messageCode;

    /**
     * Khởi tạo một hằng số Enum HttpStatusMessage.
     *
     * @param code        mã trạng thái HTTP
     * @param messageCode mã thông báo (tương ứng với phiên bản TypeScript)
     */
    HttpStatusMessage(int code, String messageCode) {
        this.code = code;
        this.messageCode = messageCode;
    }

    /**
     * Tìm kiếm một HttpStatusMessage theo mã trạng thái.
     *
     * @param code mã trạng thái HTTP cần tìm
     * @return Enum HttpStatusMessage tương ứng
     * @throws IllegalArgumentException nếu không tìm thấy mã trạng thái phù hợp
     */
    public static HttpStatusMessage fromCode(int code) {
        for (HttpStatusMessage status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy HttpStatusMessage cho mã: " + code);
    }
}
