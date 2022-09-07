package com.excelr.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class DataUtils {

    /**
     * his method can be used by any @Test method because sheetname will be taken as current test
     * method name
     * @param method
     * @return Object[][]
     * @throws IOException
     */
    @DataProvider
    public Object[][] commonDataProvider(Method method) throws IOException {

        //@Test method name is the sheetname
         String sheetName=method.getName();
         Object[][] data= ExcelUtils.getSheetIntoTwoDimensionalArray("test-data/openemr_data.xlsx",sheetName);
         return data;
    }


//    @DataProvider
//    public Object[][] validCredentialData()
//    {
//        Object[][] data=new Object[2][4];
//
//        data[0][0]="admin";
//        data[0][1]="pass";
//        data[0][2]="English (Indian)";
//        data[0][3]="OpenEMR";
//
//        data[1][0]="physician";
//        data[1][1]="physician";
//        data[1][2]="English (Indian)";
//        data[1][3]="OpenEMR";
//
//        return data;
//    }
}
