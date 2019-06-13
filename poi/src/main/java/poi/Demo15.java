package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 设置单元格格式
 *
 * @author Nathan
 */
public class Demo15 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        CellStyle style;
        DataFormat format = wb.createDataFormat();
        Row row;
        Cell cell;
        short rowNum = 0;
        short colNum = 0;

        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(111111.25);

        style = wb.createCellStyle();
        // 设置数据格式
        style.setDataFormat(format.getFormat("0.0"));
        cell.setCellStyle(style);

        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(1111111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("#,##0.000"));
        cell.setCellStyle(style);

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
