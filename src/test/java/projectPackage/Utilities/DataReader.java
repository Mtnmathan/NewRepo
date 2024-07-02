package projectPackage.Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

	public static Map<String, Map<String, String>> readExcelData(String filePath) {
		Map<String, Map<String, String>> testData = new HashMap<String, Map<String, String>>();
		DataFormatter formatter = new DataFormatter();

		try (FileInputStream fis = new FileInputStream(filePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheetAt(0);
			int numRows = sheet.getPhysicalNumberOfRows();
			int numCols = sheet.getRow(0).getPhysicalNumberOfCells();

			for (int i = 1; i < numRows; i++) {
				String testCaseName = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				Map<String, String> dataMap = new HashMap<>();

				for (int j = 1; j < numCols; j++) {
					String columnName = formatter.formatCellValue(sheet.getRow(0).getCell(j));
					String cellValue = formatter.formatCellValue(sheet.getRow(i).getCell(j));
					dataMap.put(columnName, cellValue);
				}

				testData.put(testCaseName, dataMap);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return testData;
	}
}
