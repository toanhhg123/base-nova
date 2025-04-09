package com.example.demo.core.dto.model;

import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.constants.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * Lớp ResponseEntityData là lớp bao bọc (wrapper) cho {@link ResponseEntity}
 * giúp chuẩn hóa cấu trúc phản hồi của API với dữ liệu trả về.
 *
 * @param <T> Kiểu dữ liệu của đối tượng trả về (element)
 */
public class ResponseEntityData<T> extends ResponseEntity<ResponseData<T>> {

    /**
     * Hàm dựng private, chỉ sử dụng nội bộ trong class thông qua các phương thức factory.
     *
     * @param body    Đối tượng chứa dữ liệu và metadata của phản hồi
     * @param headers Các HTTP header đi kèm phản hồi (có thể null)
     * @param status  Mã trạng thái HTTP của phản hồi
     */
    private ResponseEntityData(ResponseData<T> body, HttpHeaders headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    /**
     * Tạo đối tượng phản hồi thành công với dữ liệu đầu vào.
     *
     * @param data Dữ liệu cần trả về trong phản hồi
     * @return Đối tượng {@link ResponseEntityData} với status 200 OK
     */
    public static <T> ResponseEntityData<T> success(T data) {
        var response = toResponse(data);
        return new ResponseEntityData<>(response, null, HttpStatus.OK);
    }

    /**
     * Tạo phản hồi thành công, kèm thêm tham số mô tả thông điệp (message param).
     *
     * @param data  Dữ liệu trả về
     * @param param Tham số bổ sung cho thông điệp phản hồi (message param)
     * @return Đối tượng {@link ResponseEntityData} với status 200 OK
     */
    public static <T> ResponseEntityData<T> success(T data, Object param) {
        var response = toResponse(data);
        response.setMessParam(param);
        return new ResponseEntityData<>(response, null, HttpStatus.OK);
    }

    /**
     * Chuyển dữ liệu thành định dạng {@link ResponseData} tiêu chuẩn.
     *
     * @param data Dữ liệu đầu vào
     * @return Đối tượng {@link ResponseData} với thông tin trạng thái mặc định 200 OK
     */
    public static <T> ResponseData<T> toResponse(T data) {
        return ResponseData.<T>of()
                .status(ResponseStatus.SUCCESS)
                .element(data)
                .statusCode(HttpStatus.OK.value())                  // Mã trạng thái HTTP 200
                .message(HttpStatus.OK.name())                      // Tên trạng thái ("OK")
                .messSeq(ResponseCode.OK.messageCode)          // Mã thông điệp định nghĩa trước
                .messCode(ResponseCode.OK.messageCode)         // Trùng với messSeq, dùng cho client xử lý
                .build();
    }

}
