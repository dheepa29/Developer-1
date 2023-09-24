package CustomerHelper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.BBC.model.customer;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class helper {
	
	//check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();
        return true;

//        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
//            return true;
//        } else {
//            return false;
//        }
        
        

    }


    //convert excel to list of products

    public static List<customer> convertExcelToListOfProduct(InputStream is) {
        List<customer> list = new ArrayList<>();
        
        boolean isExcel = false;

        try {
            if (isExcel) {
                XSSFWorkbook workbook = new XSSFWorkbook(is);
                
                XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                customer p = new customer();
                DataFormatter formatter = new DataFormatter();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    case 0:
                        p.setCustomerid(cell.getStringCellValue());
//                    	if (cell.getCellType() == CellType.NUMERIC) {
//                            p.setCustomerid(String.valueOf(cell.getNumericCellValue()));
//                        } else {
//                            p.setCustomerid(cell.getStringCellValue());
//                        }
                        break;
                    case 1:
                       p.setName(cell.getStringCellValue());
//                    	if (cell.getCellType() == CellType.NUMERIC) {
//                            p.setName(String.valueOf(cell.getNumericCellValue()));
//                        } else {
//                            p.setName(cell.getStringCellValue());
//                        }
                        break;
                    case 2:
                        p.setUnitconsumption(cell.getNumericCellValue());
                        break;
                    case 3:
                        // Assuming that the date is stored as a valid date in the Excel cell
                    	java.util.Date utilDate = cell.getDateCellValue(); // Assuming cell contains a java.util.Date
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        p.setBillduedate(sqlDate);
                        break;
                    case 4:
                        p.setEmail(cell.getStringCellValue());
//                    	if (cell.getCellType() == CellType.NUMERIC) {
//                            p.setEmail(String.valueOf(cell.getNumericCellValue()));
//                        } else {
//                            p.setEmail(cell.getStringCellValue());
//                        }
                        break;
                    case 5:
                        p.setTelephone(cell.getStringCellValue());
//                    	if (cell.getCellType() == CellType.NUMERIC) {
//                            p.setTelephone(String.valueOf(cell.getNumericCellValue()));
//                        } else {
//                            p.setTelephone(cell.getStringCellValue());
//                        }
                        break;
                    default:
                        break;
                }

                    cid++;

                }

                list.add(p);


            }}else {
            	InputStreamReader reader = new InputStreamReader(is);
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip header row

                List<String[]> allData = csvReader.readAll();

                for (String[] row : allData) {
                    if (row.length >= 6) {
                        customer p = new customer();
                        p.setCustomerid(row[0]);
                        p.setName(row[1]);
                        //p.setUnitconsumption(Double.parseDouble(row[2]));
                        if (!row[2].isEmpty()) {
                            try {
                                p.setUnitconsumption(Double.parseDouble(row[2]));
                            } catch (NumberFormatException e) {
                                // Handle the case where the value is not a valid double
                                // You can log an error, set a default value, or take appropriate action here
                                p.setUnitconsumption(0.0); // Set a default value (e.g., 0.0)
                            }
                        } else {
                            // Set as an empty string
                            p.setUnitconsumption(0.0); // Set a default value (e.g., 0.0)
                        }
                        //java.util.Date utilDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(row[3]);
                        //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        if (!row[3].isEmpty()) {
                            try {
                                java.util.Date utilDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(row[3]);
                                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                                p.setBillduedate(sqlDate);
                            } catch (ParseException e) {
                                // Handle the case where the date format is invalid
                                // You can log an error, set a default date, or take appropriate action here
                                p.setBillduedate(new java.sql.Date(System.currentTimeMillis())); // Set a default date
                            }
                        }
                        //p.setBillduedate(sqlDate);
                        p.setEmail(row[4]);
                        p.setTelephone(row[5]);
                        list.add(p);
                    }
                }

                
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
