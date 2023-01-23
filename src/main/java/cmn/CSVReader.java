package cmn;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void main(String[] args) {

        CSVReader csvReader = new CSVReader();
        List<List<String>> lists = csvReader.readCSV("/Users/user/IdeaProjects/openApiProject/src/main/java/personnelFolder/bitstampUSD_1-min_data_2012-01-01_to_2021-03-31.csv");

        for (int i = 0; i <= 10; i++) {
            System.out.println(lists.get(i).get(0) + " " + lists.get(i).get(1) + " " + lists.get(i).get(2) + " " + lists.get(i).get(3) + " " + lists.get(i).get(4));

        }
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
