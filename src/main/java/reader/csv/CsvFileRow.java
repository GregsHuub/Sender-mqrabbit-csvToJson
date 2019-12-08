package reader.csv;

import com.google.common.base.MoreObjects;

import java.util.List;

public final class CsvFileRow {
    private final List<String> data;


    public CsvFileRow(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .toString();
    }
}
