package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 合并单元格
 *
 * @author Nathan
 */
public class Demo11 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(1);

        Cell cell = row.createCell(1);
        cell.setCellValue("单元格合并测试");

        sheet.addMergedRegion(new CellRangeAddress(
                // 起始行
                1,
                // 结束行
                2,
                // 起始列
                1,
                // 结束列
                2
        ));

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
