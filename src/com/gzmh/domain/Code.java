package com.gzmh.domain;

/**
 * 代码（code表）
 * 
 * @author Xlxin
 * 
 */
public class Code implements java.io.Serializable {

	private static final long serialVersionUID = 7699557746312144124L;

	private String id;
	private CodeType codeType;
	private String codeNum;
	private String codeName;
	private String isDel;// 是否允许删除
	private String mark;// 标识
	private ShiyanshiInfo shiyanshiInfo;

	public ShiyanshiInfo getShiyanshiInfo() {
		return shiyanshiInfo;
	}

	public void setShiyanshiInfo(ShiyanshiInfo shiyanshiInfo) {
		this.shiyanshiInfo = shiyanshiInfo;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CodeType getCodeType() {
		return codeType;
	}

	public void setCodeType(CodeType codeType) {
		this.codeType = codeType;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
