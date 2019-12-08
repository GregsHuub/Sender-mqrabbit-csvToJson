package reader.csv;

import java.util.List;

public interface MessagePreparer <T extends FileFormat> {
    List<String> prepare(T file);
}
