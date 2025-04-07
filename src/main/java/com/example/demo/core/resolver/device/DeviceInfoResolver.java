package com.example.demo.core.resolver.device;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Lớp xử lý phân giải thông tin thiết bị từ request thành đối tượng DeviceInfo
 * <p>
 * Implement {@link HandlerMethodArgumentResolver} để hỗ trợ inject tham số trong controller
 */
@Slf4j
@Component
public class DeviceInfoResolver implements HandlerMethodArgumentResolver {
    // Các hằng số cho các giá trị lặp lại
    private static final String UNKNOWN = "unknown";
    private static final String UNKNOWN_CAPITALIZED = "Unknown";

    // Các header HTTP
    private static final String HEADER_USER_AGENT = "user-agent";
    private static final String HEADER_ACCEPT_LANGUAGE = "accept-language";
    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_X_REAL_IP = "x-real-ip";

    // Các pattern regex để phân tích user agent
    private static final Pattern BROWSER_PATTERN = Pattern.compile("(Chrome|Firefox|Safari|Edge|Opera|MSIE|Trident)/?([\\d.]+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern OS_PATTERN = Pattern.compile("(Windows|Mac|Linux|Android|iOS|iPhone|iPad)(?:\\s([\\d._]+))?", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEVICE_PATTERN = Pattern.compile("(Mobile|Tablet)", Pattern.CASE_INSENSITIVE);

    // Loại thiết bị mặc định
    private static final String DEVICE_DESKTOP = "Desktop";

    /**
     * Kiểm tra xem parameter có được hỗ trợ bởi resolver này không
     *
     * @param parameter thông tin parameter cần kiểm tra
     * @return true nếu parameter là DeviceInfo, ngược lại false
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(DeviceInfo.class)
                || parameter.hasParameterAnnotation(DeviceInfoParam.class)
                || parameter.hasMethodAnnotation(DeviceInfoParam.class)
                ;
    }

    /**
     * Phương thức chính để phân giải thông tin thiết bị từ request
     *
     * @param parameter     thông tin parameter
     * @param mavContainer  container cho ModelAndView
     * @param webRequest    request hiện tại
     * @param binderFactory factory để tạo WebDataBinder
     * @return đối tượng DeviceInfo chứa thông tin thiết bị
     */
    @Override
    public Object resolveArgument(
            @NonNull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        var request = (HttpServletRequest) webRequest.getNativeRequest();
        var ip = getClientIpAddress(request);

        // Parse User Agent
        var userAgent = getHeaderOrDefault(request, HEADER_USER_AGENT);
        var parsedUserAgent = parseUserAgent(userAgent);

        var queryParams = getQueryRequest(request);

        var deviceInfo = DeviceInfo.builder()
                .ip(ip)
                .userAgent(parsedUserAgent)
                .platform(Platform.builder()
                        .type(parsedUserAgent.getDevice())
                        .vendor(UNKNOWN_CAPITALIZED)
                        .build())
                .language(getHeaderOrDefault(request, HEADER_ACCEPT_LANGUAGE))
                .timestamp(ZonedDateTime.now().toString())
                .timezone(ZoneId.systemDefault().toString())
                .host(request.getServerName())
                .protocol(request.getScheme())
                .secure(request.isSecure())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .query(queryParams)
                .build();

        var annotation = parameter.getParameterAnnotation(DeviceInfoParam.class);
        if (annotation != null && annotation.value() != DeviceInfoField.ALL) {
            return getSpecificField(deviceInfo, annotation.value());
        }

        return deviceInfo;
    }

    /**
     * Lấy giá trị header hoặc trả về giá trị mặc định nếu không tồn tại
     *
     * @param request    đối tượng HttpServletRequest
     * @param headerName tên header cần lấy
     * @return giá trị header hoặc "unknown" nếu không tồn tại
     */
    private String getHeaderOrDefault(HttpServletRequest request, String headerName) {
        return request.getHeader(headerName) != null ? request.getHeader(headerName) : UNKNOWN;
    }

    /**
     * Lấy các tham số query từ request
     *
     * @param request đối tượng HttpServletRequest
     * @return Map chứa các tham số query
     */
    private Map<String, String> getQueryRequest(HttpServletRequest request) {
        Map<String, String> queryParams = new HashMap<>();
        request.getParameterMap().forEach((key, values) -> {
            if (values != null && values.length > 0) {
                queryParams.put(key, values[0]);
            }
        });
        return queryParams;
    }

    /**
     * Lấy giá trị cụ thể từ đối tượng DeviceInfo dựa trên field chỉ định
     *
     * @param deviceInfo đối tượng DeviceInfo
     * @param field      trường cần lấy giá trị
     * @return giá trị của trường tương ứng
     */
    private Object getSpecificField(DeviceInfo deviceInfo, DeviceInfoField field) {
        return switch (field) {
            case IP -> deviceInfo.getIp();
            case USER_AGENT -> deviceInfo.getUserAgent();
            case PLATFORM -> deviceInfo.getPlatform();
            case LANGUAGE -> deviceInfo.getLanguage();
            case TIMESTAMP -> deviceInfo.getTimestamp();
            case TIMEZONE -> deviceInfo.getTimezone();
            case HOST -> deviceInfo.getHost();
            case PROTOCOL -> deviceInfo.getProtocol();
            case SECURE -> deviceInfo.isSecure();
            case METHOD -> deviceInfo.getMethod();
            case PATH -> deviceInfo.getPath();
            case QUERY -> deviceInfo.getQuery();
            case ALL -> deviceInfo;
        };
    }

    /**
     * Lấy địa chỉ IP của client từ request
     *
     * @param request đối tượng HttpServletRequest
     * @return địa chỉ IP của client
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String ip = null;

        // Check X-Forwarded-For header
        var forwardedHeader = request.getHeader(HEADER_X_FORWARDED_FOR);
        if (forwardedHeader != null && !forwardedHeader.isEmpty()) {
            var addresses = forwardedHeader.split(",");
            if (addresses.length > 0) {
                ip = addresses[0].trim();
            }
        }

        // Check other headers
        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader(HEADER_X_REAL_IP);
        }

        // Fallback to request remote address
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        return ip != null ? ip : UNKNOWN;
    }

    /**
     * Phân tích chuỗi User-Agent để lấy thông tin trình duyệt, hệ điều hành và thiết bị
     *
     * @param ua chuỗi User-Agent
     * @return đối tượng UserAgentInfo chứa thông tin đã phân tích
     */
    private UserAgentInfo parseUserAgent(String ua) {
        // Browser detection
        var browserMatcher = BROWSER_PATTERN.matcher(ua);

        var browser = UNKNOWN_CAPITALIZED;
        var browserVersion = UNKNOWN_CAPITALIZED;
        if (browserMatcher.find()) {
            browser = browserMatcher.group(1);
            browserVersion = browserMatcher.group(2) != null ? browserMatcher.group(2) : UNKNOWN_CAPITALIZED;
        }

        // OS detection
        var osMatcher = OS_PATTERN.matcher(ua);

        var os = UNKNOWN_CAPITALIZED;
        var osVersion = UNKNOWN_CAPITALIZED;
        if (osMatcher.find()) {
            os = osMatcher.group(1);
            osVersion = osMatcher.group(2) != null ? osMatcher.group(2) : UNKNOWN_CAPITALIZED;
        }

        // Device type detection
        var deviceMatcher = DEVICE_PATTERN.matcher(ua);
        var device = deviceMatcher.find() ? deviceMatcher.group(1) : DEVICE_DESKTOP;

        return UserAgentInfo.builder()
                .browser(browser)
                .browserVersion(browserVersion)
                .os(os)
                .osVersion(osVersion)
                .device(device)
                .build();
    }
}
