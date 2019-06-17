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
import java.util.Date;

/**
 * @author Nathan
 */
public class Demo5 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(0);
        CellStyle cellStyle = wb.createCellStyle();
        // 前景色
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        row.setRowStyle(cellStyle);
        // 创建一个单元格  第1列
        Cell cell = row.createCell(0);
        // 给单元格设置值
        cell.setCellValue(new Date());

        row.createCell(1).setCellValue(1);
        row.createCell(2).setCellValue("一个字符串");
        row.createCell(3).setCellValue("aaaaaaaaa");
        row.createCell(4).setCellValue(0.0);
        row.createCell(5).setCellValue(false);

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
