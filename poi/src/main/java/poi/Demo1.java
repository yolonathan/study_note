package poi;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 生成一个空的工作簿
 *
 * @author Nathan
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("c:\\用Poi搞出来的工作簿.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}
