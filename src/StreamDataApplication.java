import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDataApplication {



    public static void main(String[] args){

        String[] filesName = {"model3.csv","modelS.csv","modelX.csv"};
        String[] filesModelTitles = {"Model 3","Model X","Model S"};

        SalesRecordService salesRecordService = new SalesRecordService();
        salesRecordService.readSalesData(filesName,filesModelTitles);

        salesRecordService.printAllStatistics();


    }


}
