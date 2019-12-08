import org.apache.log4j.Logger;
import reader.mqrabbit.RecvQueue;

public class ApplicationRecv {
    private static final String HOST = "localhost";
    private static final String ROUTING_KEY = "csv test";
    private static final Logger LOGGER = Logger.getLogger(ApplicationRecv.class);

    public static void main(String[] args) {

        LOGGER.info("Start receiving message from MqRabbit...");
        LOGGER.info("To stop receiving messages press CTRL + C");
        RecvQueue recvQueue = new RecvQueue();
//        recvQueue.receivingQueueTest(QUEUE_NAME, HOST);
        recvQueue.receiving(ROUTING_KEY, HOST, false);

        // TODO: 06.12.2019
        //  sprobowac wyslac jsony, nie stringi jsonowe, ustawic cfg zewnetrzny i spakowac do jara ta piekna apke hehe
    }
}
