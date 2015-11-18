package com.mbellagamba.csvsync;

public class CsvSyncConf {
	private int setDimension;
	private int captionLength;
	private String csvFile;
	private String csvOutFile;
	private String inCsvSplitBy;
	private String outCsvSplitBy;
	
	public CsvSyncConf(int setDimension, int captionLength, String csvFile, String csvOutFile, String inCsvSplitBy, String outCsvSplitBy) {
		this.setDimension = setDimension;
		this.captionLength = captionLength;
		this.csvFile = csvFile;
		this.csvOutFile = csvOutFile;
		this.inCsvSplitBy = inCsvSplitBy;
		this.outCsvSplitBy = outCsvSplitBy;
		
	}

	public int getSetDimension() {
		return setDimension;
	}

	public String getCsvFile() {
		return csvFile;
	}

	public String getCsvOutFile() {
		return csvOutFile;
	}

	public String getInCsvSplitBy() {
		return inCsvSplitBy;
	}

	public int getCaptionLength() {
		return captionLength;
	}

	public String getOutCsvSplitBy() {
		return outCsvSplitBy;
	}

	@Override
	public String toString() {
		return "CsvSyncConf [setDimension=" + setDimension + ", captionLength=" + captionLength + ", csvFile=" + csvFile
				+ ", csvOutFile=" + csvOutFile + ", inCsvSplitBy=" + inCsvSplitBy + ", outCsvSplitBy=" + outCsvSplitBy
				+ "]";
	}
	
	
	
}
