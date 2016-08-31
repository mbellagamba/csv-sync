# CsvSync

It is a Java application with a simple UI that synchronize data given a CSV
file. It assume that the first value of the line is a date value: it allows to
select the number of line to aggregate and calculate the average of the other
values in the lines.

It is useful to synchronize values retrieved from different sensors, each of
them has a different sampling frequency.

If the input file is like the following:
``` CSV
2016/08/01 10:00, 1.25, 2.4, 0.9, 0.8
2016/08/01 10:05, 1.35, 2.6, 0.7, 1.0
```
The first column of the input file is considered the key of the row. The output
file will contains less rows, because it consider the mean value of the elements
of the same group. In the example, the group has 2 rows. In this way,you can
assume the sensor has done only a single measure at the first time.
``` CSV
2016/08/01 10:00, 1.3, 2.5, 0.8, 0.9
```

The elaboration could be customized through the following parameters:

* Input file: the path of the CSV input file.
* Output file: the path where the CSV output file should be generated.
* Set dimension: it represents the size of the rows group.
* Caption lines: the number of rows that represents the caption. The elaboration jumps them.
* Input separator: the separator of the input CSV file (es. ',' or ';')
* Output separator: the separator of the output CSV file (es. ',' or ';')
