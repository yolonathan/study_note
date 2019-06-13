package poi;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 建立sheet页
 *
 * @author Nathan
 */
public class Demo2 {


    public static void main(String[] args) throws Exception {

        // 定义一个新的工作簿
        Workbook wb = new SXSSFWorkbook();
        // 创建第一个Sheet页
        wb.createSheet("第一个Sheet页");
        // 创建第二个Sheet页
        wb.createSheet("第二个Sheet页");
        FileOutputStream fileOut = new FileOutputStream("c:\\用Poi搞出来的Sheet页.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

}
