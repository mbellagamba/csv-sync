package com.mbellagamba.csvsync;

import java.util.ArrayList;
import java.util.List;

public class AverageCalculator implements ResultBuilder {
	
	private ArrayList<double[]> data;
	private ArrayList<String> keys;
	
	public AverageCalculator() {
		data = new ArrayList<double[]>();
		keys = new ArrayList<String>();
	}

	public void aggregate(String key, List<double[]> values) {
		data.add(average(values));
		
		keys.add(key);
	}
	
	private double[] average(List<double[]> lines) {
		int length = getMaxLength(lines);
		double[] doubles = new double[length];
		int size = lines.size();
		int[] availables = new int[length];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < length; j++){
				try {
					doubles[j] += lines.get(i)[j];
					availables[j]++;
				} catch(IndexOutOfBoundsException e){
					doubles[j] += 0;
				}
			}
		}
		for(int j = 0; j < doubles.length; j++){
			doubles[j] /= availables[j];
		}
		return doubles;
	}

	public ArrayList<double[]> getData() {
		return data;
	}
	
	public ArrayList<String> getKeys() {
		return keys;
	}
	
	private int getMaxLength(List<double[]> lines) {
		int length = 0;
		for(int i = 0, size = lines.size(); i < size; i++){
			int newLength = lines.get(i).length;
			if(newLength > length){
				length = newLength;
			}
		}
		return length;
	}
}
