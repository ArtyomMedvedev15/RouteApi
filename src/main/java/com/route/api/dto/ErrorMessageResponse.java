package com.route.api.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class ErrorMessageResponse {
    private int error_statusCode;
    private Date timestamp;
    private String error_message;
    private String error_description;
}
