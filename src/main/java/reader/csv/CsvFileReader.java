package reader.csv;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class CsvFileReader {
    private static final CsvFileRow EMPTY_ROW = new CsvFileRow(Collections.emptyList());

    private final String separator;

    public CsvFileReader(String separator) {
        this.separator = separator;
    }

    public CsvFile read(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            if (lines.size() == 0) return new CsvFile(fileName, EMPTY_ROW, Collections.emptyList());
            if (lines.size() == 1) return new CsvFile(fileName, readHeaders(lines), Collections.emptyList());
            return new CsvFile(fileName, readHeaders(lines), readRows(lines));
        } catch (Exception e) {
            throw new CsvFileReadException(fileName, e);
        }

    }
    public List<CsvFile> multifileReader(List<String> fileNames){
        List<CsvFile> mutli = new ArrayList<>();
        for(String file : fileNames){
            mutli.add(read(file));
        }
        return mutli;
    }

    private CsvFileRow readHeaders(List<String> lines) {
        return readRow(lines.get(0));
    }

    private List<CsvFileRow> readRows(List<String> lines) {
        return lines.subList(1, lines.size())
                .stream()
                .map(line -> readRow(line))
                .collect(Collectors.toList());
    }

    private CsvFileRow readRow(String line) {
        return new CsvFileRow(Arrays.stream(line.split(separator)).collect(Collectors.toList()));
    }
}
