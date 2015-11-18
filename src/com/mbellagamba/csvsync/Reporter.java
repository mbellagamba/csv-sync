package com.mbellagamba.csvsync;

public interface Reporter {
	
	public void reportStatus(String status);
	
	public void reportException(Exception e);

}
