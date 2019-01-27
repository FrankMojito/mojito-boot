package com.mojito.mojitoboot.common.utils.other;

import com.csvreader.CsvReader;
import com.google.common.collect.Lists;
import com.mojito.mojitoboot.common.viewmodel.UserVO;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 利用javacsv2.0做导入导出csv文件工具类<br/>
 *
 *
 * @author kpchen
 *
 */
public class CSVUtil {
	

    public static List<UserVO> importCsv(InputStreamReader inputStreamReader) throws Exception {
        CsvReader reader = null;
//        List<String[]> dataList = new ArrayList<String[]>();
        ArrayList<UserVO> newArrayList = Lists.newArrayList();
        try {
            reader = new CsvReader(inputStreamReader);

            // 跳过表头
            // reader.getHeaders()
            while (reader.readRecord()) {
//                dataList.add(reader.getRawRecord().split(","));
            	
                System.out.println(reader.getRawRecord());
                // 按列名读取这条记录的值
                System.out.println(reader.get(0));
                System.out.println(reader.get(1));

//                UserVO user = new UserVO(Integer.parseInt(reader.get(0)),reader.get(1));
//                newArrayList.add(user);
                return null;
                
            }
        } catch (Exception e) {
            System.out.println("读取CSV出错..." + e);
            throw e;
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return newArrayList;
    }

}
