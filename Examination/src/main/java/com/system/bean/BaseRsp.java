package com.system.bean;

public class BaseRsp
{
	final static int SUCCESS = 0;

	private int resultCode;

	private String resultDesc;

	public BaseRsp()
	{
		this.resultCode = SUCCESS;
	}

	public BaseRsp(int resultCode, String resultDesc)
	{
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultDesc()
	{
		return resultDesc;
	}

	public void setResultDesc(String resultDesc)
	{
		this.resultDesc = resultDesc;
	}

}
