package reader.csv;

public class CsvFileToJsonException  extends RuntimeException {
    private static final String MESSAGE = "Ups..Cannot prepare json message from csv file '%s' to Json";

    public CsvFileToJsonException(CsvFile file, Throwable cause) {
        super(String.format(MESSAGE, file.toString()), cause);
    }
}
