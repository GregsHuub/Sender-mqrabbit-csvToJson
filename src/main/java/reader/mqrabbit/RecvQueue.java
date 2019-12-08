package reader.mqrabbit;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RecvQueue implements RecvMqRabbit {
    private static final Logger LOGGER = Logger.getLogger(RecvQueue.class);


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
//                System.out.println("[x] message:  " + message);
                LOGGER.info("new message" + message);
                connection.close();
            };
//            AUTOACK - true: delete message after download, false - leave forever
            channel.basicConsume(queueName, deleteMessageAfterConsump, deliverCallback, consumerTag -> {
                System.out.println(consumerTag);
                System.out.println("ADSADASD");
//
            });
        } catch (Exception e) {
            throw new RecvQueueException(queueName, host, e);
        }
    }


    @Override
    public List<String> receivingQueueTest(String queueName, String host) {
        List<String> tempList = new ArrayList<>();
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                tempList.add(message);
                System.out.println("[x] mess:  " + message);
                connection.close();
            };
            //AUTOACK - true: delete message after download, false - leave forever
            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            throw new RecvQueueException(queueName, host, e);
        }
        return tempList;
    }

    @Override
    public String receivingTryToDownload() {
        return null;
    }
}
