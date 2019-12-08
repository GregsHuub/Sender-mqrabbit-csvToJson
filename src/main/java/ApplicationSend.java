
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

public class ApplicationSend {
    private static final Logger LOGGER = Logger.getLogger(ApplicationSend.class);
    private static final Config conf = ConfigFactory.load("send");
    private static final List<String> FOLDERS = conf.getStringList("path.folders");
    private static final String EXTENSION = conf.getString("csv.extension");
    private static final String ROUTING_KEY = conf.getString("mqrabbit.routing_key");
    private static final String HOST = conf.getString("mqrabbit.host");
    private static final String SEPERATOR = conf.getString("csv.separator");


    public static void main(String[] args) {
        LOGGER.info("Application started..");

        List<CsvFile> files = new ArrayList<>();
        List<String> preparedFiles = new ArrayList<>();
        SendQueue sendQueue = new SendQueue();
        CsvFolderReader folder = new CsvFolderReader();
        CsvFileReader file = new CsvFileReader(SEPERATOR);
        CsvFileToJson csvToJson = new CsvFileToJson();

        LOGGER.info("Start searching csv files in folders..\n");
        FOLDERS.forEach(fol -> folder.folderReader(fol, EXTENSION).forEach(csv -> {
            LOGGER.info(String.format("Start reading file: %s", csv));
            files.add(file.read(csv));
        }));
        LOGGER.info("Finish reading files");

        LOGGER.info("Start convering files to json..");
        files.forEach(rows -> {
            preparedFiles.addAll(csvToJson.prepare(rows));
        });
        LOGGER.info("Convering done");

        LOGGER.info(String.format("Start sending data to mqRabbit [Routing key : %s, host : %s]", ROUTING_KEY, HOST));
        preparedFiles.forEach(message -> sendQueue.send(ROUTING_KEY, HOST, message));
        LOGGER.info("Transferring done.");
        LOGGER.info("Application finished");
    }
}
