package com.hzit.dao;

import com.hzit.entity.Reader;
import com.hzit.entity.ReaderType;

public interface IReaderDAO {
	public int add(Reader reader);
	public boolean isReaderExist(String username);
	public Reader login(String username,String password);
	public ReaderType getReaderTypeById(int typeid);
	public Reader getReaderById(int readid);
}
