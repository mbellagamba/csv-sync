package com.mbellagamba.csvsync;

import java.util.List;

public interface ResultBuilder {
	
	public void aggregate(String key, List<double[]> values);

}
