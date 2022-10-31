import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SalesRecordService {

    ArrayList<SalesRecord> salesRecord;

    public SalesRecord createSalesRecord(LocalDate localDate, String model, int quantitySold){

        return new SalesRecord(localDate,model,quantitySold);
    }

    public ArrayList<SalesRecord> readSalesData(String[] filesName, String[] filesModelTitles){
        ArrayList<SalesRecord> salesRecord = new ArrayList<>();
        for(int i =0;i<filesName.length;i++){
            try {

                BufferedReader fileReader = new BufferedReader(new FileReader(filesName[i]));

                fileReader = new BufferedReader(new FileReader(filesName[i]));

                int index = 0;
                fileReader.readLine();//Skip frist line

                String line;
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MMM-yy", Locale.US);
                while ((line = fileReader.readLine()) != null) {
                    String[] data = line.split(",");
                    YearMonth yearMonth = YearMonth.parse(data[0],dtf2);
                    LocalDate ld = yearMonth.atEndOfMonth();
                    salesRecord.add(new SalesRecord(ld,filesModelTitles[i],Integer.valueOf(data[1])));
                }

                fileReader.close();

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        }
        this.salesRecord = salesRecord;
        return salesRecord;

    }


    public int getQuantitySold(String model, int year){

        int sumStats = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .filter(e -> e.getLocalDate().getYear() == year)
                .mapToInt(SalesRecord::getQuantitySold).sum();

        return sumStats;
    }

    public void getBestMonth(String model){

        IntSummaryStatistics sumStats = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .mapToInt(SalesRecord::getQuantitySold).summaryStatistics();

        List<SalesRecord> date = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .filter(e -> e.getQuantitySold() == sumStats.getMax())
                .peek(e -> System.out.println("The best month for " + model + ": " + e.getLocalDate().toString().substring(0,e.getLocalDate().toString().length()-3)))
                .collect(Collectors.toList());
    }

    public void getWorstMonth(String model){

        IntSummaryStatistics sumStats = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .mapToInt(SalesRecord::getQuantitySold).summaryStatistics();

        List<SalesRecord> date = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .filter(e -> e.getQuantitySold() == sumStats.getMin())
                .peek(e -> System.out.println("The worst month for " + model + ": " + e.getLocalDate().toString().substring(0,e.getLocalDate().toString().length()-3)))
                .collect(Collectors.toList());

    }

    public List<Integer> getYearsSold(String model){

        List<Integer> yearsSold = salesRecord.stream()
                .filter(e -> e.getModel().equals(model))
                .map(e -> e.getLocalDate().getYear())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return yearsSold;
    }

    public List<String> getModelsSold(){

        List<String> modelsSold = salesRecord.stream()
                .map(e -> e.getModel())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return modelsSold;
    }

    public void printDataForModel(String modelName){
        List<Integer> yearsSold = getYearsSold(modelName);
        for(Integer yearSold: yearsSold){
            System.out.println(yearSold +" -> " +getQuantitySold(modelName,yearSold));
        }

    }

    public void printAllStatistics(){
        List<String> getModelsSold = getModelsSold();
        for(String modelSold: getModelsSold){
            System.out.println(modelSold +" Yearly Sales Report");
            System.out.println("---------------------------");
            printDataForModel(modelSold);
            System.out.println(" ");
            getBestMonth(modelSold);
            getWorstMonth(modelSold);
            System.out.println(" ");
        }

    }

}
