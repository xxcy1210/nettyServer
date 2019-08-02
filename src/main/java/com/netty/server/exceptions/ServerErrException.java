
package com.netty.server.exceptions;

/**
 * 〈服务启动错误〉
 * 
 * @author yangbin
 *
 */
public class ServerErrException extends Exception {
	private String code;
	private String errMsg;

	public ServerErrException(String errMsg) {
		super(errMsg);
	    this.errMsg = errMsg;
	}
	
	public ServerErrException(String code,String errMsg) {
		super(errMsg);
	    this.errMsg = errMsg;
	    this.code=code;
	}

    public ServerErrException(Throwable cause) {
    	super(cause);  	
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}