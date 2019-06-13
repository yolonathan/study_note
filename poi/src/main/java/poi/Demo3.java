package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * @author Nathan
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(0);
        // 创建一个单元格  第1列
        Cell cell = row.createCell(0);
        // 给单元格设置值
        cell.setCellValue(1);
        // 创建一个单元格 第2列 值是1.2
        row.createCell(1).setCellValue(1.2);

        // 创建一个单元格 第3列 值为一个字符串
        row.createCell(2).setCellValue("这是一个字符串类型");

        // 创建一个单元格 第4列 值为布尔类型
        row.createCell(3).setCellValue(false);

        FileOutputStream fileOut = new FileOutputStream("c:\\用Poi搞出来的Cell.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
