import com.typesafe.config.Config;
import config.HoconConfigReader;
import org.apache.log4j.Logger;
import reader.mqrabbit.RecvQueue;

public class RecvJar {

    private static final Logger LOGGER = Logger.getLogger(ApplicationRecv.class);

    public static void main(String[] args) {

        LOGGER.info("Application start");
        HoconConfigReader configReader = new HoconConfigReader();
        Config read = configReader.read(args[0]);

        LOGGER.info("Start receiving message from MqRabbit...");
        LOGGER.info("To stop receiving messages press CTRL + C");
        RecvQueue recvQueue = new RecvQueue();
        recvQueue.receiving(read.getString("mqrabbit.routing_key"), read.getString("mqrabbit.host"),read.getBoolean("mqrabbit.deleteMessages"));

    }
}
