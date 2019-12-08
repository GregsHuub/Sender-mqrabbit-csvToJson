package reader.csv;

public class CsvFileReadException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Cannot read csv file from '%s'.";

    public CsvFileReadException(String message, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, message), cause);

    }
}
