package poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 修改单元格
 *
 * @author Nathan
 */
public class Demo13 {

    public static void main(String[] args) throws Exception {
        InputStream inp = new FileInputStream("c:\\工作簿.xls");
        POIFSFileSystem fs = new POIFSFileSystem(inp);
        Workbook wb = new HSSFWorkbook(fs);
        // 获取第一个Sheet页
        Sheet sheet = wb.getSheetAt(0);
        // 获取第一行
        Row row = sheet.getRow(0);
        // 获取单元格
        Cell cell = row.getCell(0);
        if (cell == null) {
            cell = row.createCell(3);
        }
        cell.setCellType(CellType.STRING);
        cell.setCellValue("测试单元格");

        FileOutputStream fileOut = new FileOutputStream("c:\\工作簿.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}
