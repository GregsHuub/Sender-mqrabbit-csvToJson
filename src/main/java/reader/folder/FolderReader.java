package reader.folder;

import java.util.List;

public interface FolderReader {
    List<String> folderReader(String path, String fileExtension);

}
