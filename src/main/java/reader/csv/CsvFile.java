package reader.csv;

import com.google.common.base.MoreObjects;

import java.util.List;

public class CsvFile implements FileFormat {
    private final String fileName;
    private final CsvFileRow headers;
    private final List<CsvFileRow> rows;

    public CsvFile(String fileName, CsvFileRow headers, List<CsvFileRow> rows) {
        this.fileName = fileName;
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public CsvFileRow getHeaders() {
        return headers;
    }

    public List<CsvFileRow> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("filename", fileName)
                .add("headers", headers)
                .add("rows", rows)
                .toString();
    }
}
