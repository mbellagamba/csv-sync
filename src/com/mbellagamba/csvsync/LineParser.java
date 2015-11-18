package com.mbellagamba.csvsync;

import java.util.ArrayList;

public class LineParser {
	
	private int interval;
	private int lineCount;
	private ArrayList<double[]> temps;
	private String tempKey;
	private ResultBuilder resultBuilder;

	public LineParser(int interval, ResultBuilder resultBuilder) {
		this.interval = interval;
		this.resultBuilder = resultBuilder;
		temps = new ArrayList<double[]>();
		lineCount = 0;
		tempKey = "";
	}
	
	public void addLine(String key, double[] line) {
		if(tempKey.equals("")) {
			tempKey = key;
		}
		temps.add(line);
		lineCount++;

		if(lineCount == interval){
			resultBuilder.aggregate(tempKey, temps);
			temps.clear();
			lineCount = 0;
			tempKey = "";
		}
	}

}
