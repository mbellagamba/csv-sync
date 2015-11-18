package com.mbellagamba.csvsync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

public class CsvSync {

	private CsvSyncConf conf;
	private Reporter reporter;
	
	public CsvSync(CsvSyncConf conf, Reporter reporter) {
		this.conf = conf;
		this.reporter = reporter;
	}
	
	public void run() {
		String line = "";
		String caption = "";
		BufferedReader br = null;
		AverageCalculator avCalc = new AverageCalculator();
		LineParser lineParser = new LineParser(conf.getSetDimension(), avCalc);
		
		try {

			br = new BufferedReader(new FileReader(conf.getCsvFile()));
			int skipped = 0;
			while (skipped < conf.getCaptionLength() && (line = br.readLine()) != null) {
				caption +=line + "\n";
				skipped++;
			}
			 reporter.reportStatus("Elaborazione iniziata...");
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] detectionsStrings = line.split(conf.getInCsvSplitBy());
				String key = "";
				if(detectionsStrings.length > 0) {
					 key = detectionsStrings[0];
					 detectionsStrings = ArrayUtil.removeKey(detectionsStrings);
					 double[] detections = ArrayUtil.strings2doubles(detectionsStrings);
						
					 lineParser.addLine(key, detections);
				}
			}

		} catch (FileNotFoundException e) {
			reporter.reportException(e);
		} catch (IOException e) {
			reporter.reportException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					reporter.reportException(e);
				}
			}
		}

		List<double[]> result = avCalc.getData();
		List<String> resultKeys = avCalc.getKeys();
		String toFile = caption;
		reporter.reportStatus("Elaborazione terminata.\nScrittura su file...");
		for (int i=0, size = result.size(); i < size; i++) {
			double[] detections = result.get(i);
			toFile +=resultKeys.get(i) + conf.getOutCsvSplitBy();
			for(int j=0; j < detections.length; j++){
				toFile+=detections[j] + conf.getOutCsvSplitBy();
			}
			toFile+="\n";
		}

		//Writing to file
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(conf.getCsvOutFile()), "utf-8"))) {
			writer.write(toFile);
		} catch (UnsupportedEncodingException e) {
			reporter.reportException(e);
		} catch (FileNotFoundException e) {
			reporter.reportException(e);
		} catch (IOException e) {
			reporter.reportException(e);
		}

		reporter.reportStatus("Scrittura terminata.");
	}
}
