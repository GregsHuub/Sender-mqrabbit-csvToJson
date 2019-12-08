package reader.mqrabbit;

public interface RecvMqRabbitInitializer {

    void receiving(String queueName, String host, boolean deleteMessageAfterConsump);

}
