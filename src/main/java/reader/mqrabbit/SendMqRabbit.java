package reader.mqrabbit;

public interface SendMqRabbit {

    String send(String queueName, String host, String message);

}
