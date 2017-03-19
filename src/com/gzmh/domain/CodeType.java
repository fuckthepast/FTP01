package com.gzmh.domain;

import java.io.Serializable;

/**
 * 代码类型
 * @author Xlxin
 *
 */
public class CodeType implements Serializable {

	private static final long serialVersionUID = -958940653202957913L;

	private String codeTypeNum;
	private String codeTypeName;

	public String getCodeTypeNum() {
		return codeTypeNum;
	}

	public void setCodeTypeNum(String codeTypeNum) {
		this.codeTypeNum = codeTypeNum;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}
}
