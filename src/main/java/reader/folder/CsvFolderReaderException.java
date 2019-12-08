package reader.folder;

public final class CsvFolderReaderException extends RuntimeException {
    private static final String MESSAGE = "Cannot resolve files from path '%s' .";

    public CsvFolderReaderException(String path, Throwable cause) {
        super(String.format(MESSAGE, path), cause);
    }
}
