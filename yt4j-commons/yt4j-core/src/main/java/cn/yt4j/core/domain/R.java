package cn.yt4j.core.domain;

import cn.yt4j.core.enums.IMessageStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author gyv12345@163.com
 */
@ToString(callSuper = true)
@Getter
@Setter
@ApiModel(value = "响应信息")
public class R<T> implements Serializable {

	private static final long serialVersionUID = -6101337183914807339L;

	@ApiModelProperty("编码")
	private int status;

	@ApiModelProperty("返回信息")
	private String message;

	@ApiModelProperty("返回数据")
	private T result;

	public static <T> R<T> ok() {
		return result(null, HttpStatus.OK.value(), null);
	}

	public static <T> R<T> ok(T result) {
		return result(result, HttpStatus.OK.value(), null);
	}

	public static <T> R<T> ok(String message) {
		return result(null, HttpStatus.OK.value(), message);
	}

	public static <T> R<PageResult<T>> ok(Page<T> page){
		PageResult<T> result=new PageResult<>(page);
		return  result(result, HttpStatus.OK.value(), "查询成功");

	}

	public static <T> R<T> ok(T result, String message) {
		return result(result, HttpStatus.OK.value(), message);
	}

	public static <T> R<T> failed() {
		return result(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	public static <T> R<T> failed(String message) {
		return result(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public static <T> R<T> failed(T result) {
		return result(result, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
	}

	public static <T> R<T> failed(T result, String message) {
		return result(result, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public static <T> R<T> failed(IMessageStatus status) {
		return result(null, status);
	}

	public static <T> R<T> result(T result, IMessageStatus status) {
		return result(result, status.getStatus(), status.getMessage());
	}

	private static <T> R<T> result(T result, int status, String message) {
		R<T> apiResult = new R<>();
		apiResult.setStatus(status);
		apiResult.setResult(result);
		apiResult.setMessage(message);
		return apiResult;
	}

}
