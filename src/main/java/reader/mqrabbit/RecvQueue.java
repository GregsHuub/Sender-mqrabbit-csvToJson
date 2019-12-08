package reader.mqrabbit;

import com.rabbitmq.client.*;
import java.nio.charset.StandardCharsets;

public class RecvQueue implements RecvMqRabbitInitializer {

    @Override
    public void receiving(String queueName, String host, boolean deleteMessageAfterConsump) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] " + message);
//                connection.close();
            };
            channel.basicConsume(queueName, deleteMessageAfterConsump, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            throw new RecvQueueException(queueName, host, e);
        }
    }

}
