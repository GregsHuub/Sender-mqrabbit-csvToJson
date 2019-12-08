import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.Logger;
import reader.csv.CsvFile;
import reader.csv.CsvFileReader;
import reader.csv.CsvFileToJson;
import reader.folder.CsvFolderReader;
import reader.mqrabbit.SendQueue;

import java.util.ArrayList;
import java.util.List;

public class SendJar {

    private static final Logger LOGGER = Logger.getLogger(ApplicationRecv.class);
    private static final List<CsvFile> FILES = new ArrayList<>();
    private static final List<String> PREPARED_FILES = new ArrayList<>();
    private static final SendQueue SEND_QUEUE = new SendQueue();
    private static final CsvFolderReader FOLDER = new CsvFolderReader();
    private static final CsvFileToJson CSV_TO_JSON = new CsvFileToJson();

    public static void main(String[] args) {

        LOGGER.info("Application started..");

        Config conf = ConfigFactory.load(args[0]);
        String routingKey = conf.getString("mqrabbit.routing_key");
        String host = conf.getString("mqrabbit.host");
        String separator = conf.getString("csv.separator");
        String extension = conf.getString("csv.extension");
        List<String> folders = conf.getStringList("path.folders");

        CsvFileReader file = new CsvFileReader(separator);

        LOGGER.info("Start searching csv files in folders..\n");
        folders.forEach(fol -> FOLDER.folderReader(fol, extension).forEach(csv -> {
            LOGGER.info(String.format("Start reading file: %s", csv));
            FILES.add(file.read(csv));
        }));
        LOGGER.info("Finish reading FILES");

        LOGGER.info("Start convering FILES to json..");
        FILES.forEach(rows -> {
            PREPARED_FILES.addAll(CSV_TO_JSON.prepare(rows));
        });
        LOGGER.info("Convering done");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Start sending data to mqRabbit [Routing key : %s, host : %s]", routingKey, host));
        PREPARED_FILES.forEach(message -> SEND_QUEUE.send(routingKey, host, message));
        LOGGER.info("Transferring done.");
        LOGGER.info("Application finished");
    }
}
