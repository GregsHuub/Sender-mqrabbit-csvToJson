package reader.mqrabbit;

import java.util.List;

public interface RecvMqRabbit {

    void receiving(String queueName, String host, boolean deleteMessageAfterConsump);
    List <String> receivingQueueTest(String queueName, String host);
    String receivingTryToDownload();

}
