package com.spring.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZhouGang
 * @date 2022/1/5
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

	/**
	 * ok
	 */
	OK(0, "ok"),
	FAIL(500, "fail"),
	;
    private final int code;

	private final String msg;

}
