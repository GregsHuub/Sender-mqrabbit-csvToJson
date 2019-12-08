package reader.mqrabbit;

public class SendQueueException extends RuntimeException {
    private static final String QUEUE_FALT = "Cannot send message: [%s] to queue: <%s>, with host: <%s>";

    public SendQueueException(String queue, String message, String host, Throwable cause) {
        super(String.format(QUEUE_FALT, message,queue, host), cause);
    }
}
