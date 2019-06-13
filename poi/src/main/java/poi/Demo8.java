package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.FileOutputStream;

/**
 * 设置水平和垂直样式
 *
 * @author Nathan
 */
public class Demo8 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        Sheet sheet = wb.createSheet("第一个Sheet页");
        // 创建一个行
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);

        createCell(wb, row, (short) 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, (short) 1, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, (short) 2, HorizontalAlignment.CENTER, VerticalAlignment.TOP);
        createCell(wb, row, (short) 3, HorizontalAlignment.CENTER, VerticalAlignment.TOP);

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 创建一个单元格并为其设定指定的对其方式
     *
     * @param wb         工作簿
     * @param row        行
     * @param column     列
     * @param horizontal 水平方向对其方式
     * @param vertical   垂直方向对其方式
     */
    private static void createCell(Workbook wb, Row row, short column, HorizontalAlignment horizontal, VerticalAlignment vertical) {
        // 创建单元格
        Cell cell = row.createCell(column);
        // 设置值
        cell.setCellValue(new XSSFRichTextString("Align It"));
        // 创建单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        // 设置单元格水平方向对其方式
        cellStyle.setAlignment(horizontal);
        // 设置单元格垂直方向对其方式
        cellStyle.setVerticalAlignment(vertical);
        // 设置单元格样式
        cell.setCellStyle(cellStyle);
    }
}
