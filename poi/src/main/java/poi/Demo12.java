package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 字体处理
 *
 * @author Nathan
 */
public class Demo12 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(1);

        // 创建一个字体处理类
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 24);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setStrikeout(true);

        CellStyle style = wb.createCellStyle();
        style.setFont(font);

        Cell cell = row.createCell((short) 1);
        cell.setCellValue("This is test of fonts");
        cell.setCellStyle(style);

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
