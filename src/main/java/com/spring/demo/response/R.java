package com.spring.demo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一的返回数据
 * @author ZhouGang
 * @date 2022/1/3
 */
@Data
public class R<T> implements Serializable {

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private T data;

	public R() {}

	public R(T data, int code, String msg) {
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return Objects.equals(ResponseEnum.OK.getCode(), this.code);
	}

	public static <T> R<T> success(T data) {
		R<T> r = new R<>();
		r.setData(data);
		r.setCode(ResponseEnum.OK.getCode());
		r.setMsg(ResponseEnum.OK.getMsg());
		return r;
	}

	public static <T> R<T> success() {
		R<T> r = new R<>();
		r.setCode(ResponseEnum.OK.getCode());
		r.setMsg(ResponseEnum.OK.getMsg());
		return r;
	}

	/**
	 * 前端显示失败消息
	 * @param msg 失败消息
	 * @return
	 */
	public static <T> R<T> fail(String msg) {
		R<T> r = new R<>();
		r.setMsg(msg);
		r.setCode(ResponseEnum.FAIL.getCode());
		return r;
	}

	public static <T> R<T> fail(ResponseEnum responseEnum) {
		R<T> r = new R<>();
		r.setCode(responseEnum.getCode());
		r.setMsg(responseEnum.getMsg());
		return r;
	}

}
