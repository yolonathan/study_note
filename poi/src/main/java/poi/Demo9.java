package poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 设置边框和颜色
 *
 * @author Nathan
 */
public class Demo9 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(1);
        // 创建一个单元格
        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        CellStyle cellStyle = wb.createCellStyle();
        // 底部边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 底部边框颜色
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        // 左边边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        // 左边边框颜色
        cellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        // 右边边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 右边边框颜色
        cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
        // 上边边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM_DASHED);
        // 上边边框颜色
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        cell.setCellStyle(cellStyle);
        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
