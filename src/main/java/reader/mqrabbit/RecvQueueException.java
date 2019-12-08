package reader.mqrabbit;

public class RecvQueueException extends RuntimeException {

    private static final String QUEUE_FAULT = "Cannot receiving messages from queue: <%s>, with host: <%s>";

    public RecvQueueException(String queue,String host, Throwable cause) {
        super(String.format(QUEUE_FAULT, queue,host), cause);
    }
}
