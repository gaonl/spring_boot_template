package com.example.demo.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gaonl on 2018/10/6.
 * 再controller抛出这个异常后，返回注解中配置的http代码和说明
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "server error too mutch")
public class DuplicateDomainException extends RuntimeException {
}
