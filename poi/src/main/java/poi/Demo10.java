package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 设置前景色和背景色
 *
 * @author Nathan
 */
public class Demo10 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(1);

        Cell cell = row.createCell(1);
        cell.setCellValue("XX");
        CellStyle cellStyle = wb.createCellStyle();
        // 背景色
        cellStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
        cell.setCellStyle(cellStyle);


        Cell cell2 = row.createCell(2);
        cell2.setCellValue("YYY");
        CellStyle cellStyle2 = wb.createCellStyle();
        // 前景色
        cellStyle2.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell2.setCellStyle(cellStyle2);

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
