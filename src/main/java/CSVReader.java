import java.io.*;
import java.util.*;

public class CSVReader {

    public static void main(String[] args) {

    }

    public List<List<String>> readCSV(String filePAth) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(filePAth);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<>();
                String[] lineArr = line.split(",");
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}
