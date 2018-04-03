package com.qg.springboot.word;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author WilderGao
 * @time：
 * @Discription： 根据模版自动生成 word 文档
 */

public class WordHandler {
    private static final String dirFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";

    public static void buildWordFile(Map<String , String> studentMap) throws IOException {

        //word模版文件地址
        String srcPath = dirFilePath+"考核推荐书模板.docx";

        //word目标文件地址,这里表示放在项目static目录之下
        String destPath = dirFilePath+studentMap.get("name")+".docx";

        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));

        //获得总页数
        document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        //获得总字符数
        document.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();

        Iterator iterator = document.getTablesIterator();
        while (iterator.hasNext()){

            XWPFTable table = (XWPFTable) iterator.next();
            int count = table.getNumberOfRows();

            for (int i=0;i<count;i++){
                //获得第i行
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (Map.Entry<String,String> entry : studentMap.entrySet()){
                        //和模版的字段进行匹配
                        if (cell.getText().equals(entry.getKey())){
                            cell.removeParagraph(0);
                            cell.setText(entry.getValue());
                        }
                    }
                }
            }
        }

        //最后在指定路径生成新的.docx文件
        FileOutputStream outputStream = new FileOutputStream(destPath);
        document.write(outputStream);
        outputStream.close();
    }

}
