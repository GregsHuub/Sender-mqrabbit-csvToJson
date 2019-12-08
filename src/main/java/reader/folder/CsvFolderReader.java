package reader.folder;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CsvFolderReader implements FolderReader {
    private static final Logger LOGGER = Logger.getLogger(CsvFolderReader.class);

    @Override
    public List<String> folderReader(String path, String fileExtension) {
        try {
            try (Stream<Path> paths = Files.walk(Paths.get(path))) {
                List<String> files = paths
                        .filter(Files::isRegularFile)
                        .filter(filePath -> filePath.toString().endsWith("."+ fileExtension))
                        .map(Path::toString)
                        .collect(Collectors.toList());
                LOGGER.info(String.format("files: - <%s>.", files));
                return files;
            }
        } catch (Exception e) {
            throw new CsvFolderReaderException(path, e);
        }
    }


}
