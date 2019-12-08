package reader.csv;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileToJson implements MessagePreparer<CsvFile> {
    @Override
    public List<String> prepare(CsvFile file) {
        try {
            return file.getRows()
                    .stream()
                    .map(row -> prepareMessage(row, file.getHeaders()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CsvFileToJsonException(file, e);
        }
    }

    private String prepareMessage(CsvFileRow csvFileRow, CsvFileRow headers) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < csvFileRow.getData().size(); i++) {
            jsonObject.add(headers.getData().get(i), new JsonPrimitive(csvFileRow.getData().get(i)));
        }
        return jsonObject.toString();
    }
    public List<String> prepareMultiJson(List<CsvFile> files){
        List<String> filesList = new ArrayList<>();
        for (CsvFile file : files) {
                filesList.addAll(prepare(file));
        }return filesList;
    }
}
