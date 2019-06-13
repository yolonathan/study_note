package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 调整文字换行， 设置行高
 *
 * @author Nathan
 */
public class Demo14 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(2);
        Cell cell = row.createCell(2);
        cell.setCellValue("我要换行 \n 成功了吗？");

        CellStyle cs = wb.createCellStyle();
        // 设置可以换行
        cs.setWrapText(true);
        cell.setCellStyle(cs);

        // 调整行的高度
        row.setHeightInPoints(2 * sheet.getDefaultRowHeightInPoints());

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
