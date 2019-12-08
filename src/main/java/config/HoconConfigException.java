package config;

public class HoconConfigException extends RuntimeException {

    private static final String MESSAGE = "Cannot read config file from path [%s]";

    public HoconConfigException(String fileName, Throwable cause) {
        super(String.format(MESSAGE, fileName),cause);
    }
}
