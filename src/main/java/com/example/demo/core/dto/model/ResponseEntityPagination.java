package com.example.demo.core.dto.model;

import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.constants.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * Lớp bao bọc (wrapper) cho ResponseEntity với hỗ trợ phân trang
 *
 * @param <T> Kiểu dữ liệu của items trong phân trang
 */
public class ResponseEntityPagination<T> extends ResponseEntity<ResponsePagination<T>> {

    /**
     * Constructor private - chỉ được sử dụng nội bộ trong class
     *
     * @param body    Nội dung response
     * @param headers Các HTTP headers
     * @param status  HTTP status code
     */
    private ResponseEntityPagination(ResponsePagination<T> body, HttpHeaders headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    /**
     * Tạo response thành công với dữ liệu phân trang
     *
     * @param data Dữ liệu phân trang
     * @return ResponseEntityPagination với status 200 OK
     */
    public static <T> ResponseEntityPagination<T> success(DataWithPagination<T> data) {
        var response = getTResponsePagination(data);
        return new ResponseEntityPagination<>(response, null, HttpStatus.OK);
    }

    /**
     * Tạo response thành công với dữ liệu phân trang và tham số bổ sung
     *
     * @param data  Dữ liệu phân trang
     * @param param Tham số bổ sung cho message
     * @return ResponseEntityPagination với status 200 OK
     */
    public static <T> ResponseEntityPagination<T> success(DataWithPagination<T> data, Object param) {
        var response = getTResponsePagination(data);
        response.setMessParam(param);
        return new ResponseEntityPagination<>(response, null, HttpStatus.OK);
    }

    /**
     * Phương thức helper để tạo đối tượng ResponsePagination
     *
     * @param data Dữ liệu phân trang
     * @return Đối tượng ResponsePagination được cấu hình sẵn
     */
    private static <T> ResponsePagination<T> getTResponsePagination(DataWithPagination<T> data) {
        return ResponsePagination.<T>of()
                .status(ResponseStatus.SUCCESS)
                .element(data)                     // Thiết lập dữ liệu
                .statusCode(HttpStatus.OK.value()) // Mã HTTP 200
                .message(HttpStatus.OK.name())     // Message mặc định
                .messSeq(ResponseCode.OK.messageCode) // Sequence message
                .messCode(ResponseCode.OK.messageCode) // Code message
                .build();
    }
}