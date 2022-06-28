package com.happyfaresdemo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.happyfaresdemo.entity.Testentity;
import com.monitorjbl.xlsx.StreamingReader;

public class Reader {
	
	public String caseId;
	public String scenario;
	public String description;
	public String keyword;
	public String locator;
	public String additionallocator;
	public String testdata;
	public String platform;
	
	public List<Testentity> testlist(String testcasefile) throws IOException {
		
		List<Testentity> test=new ArrayList<>();
		
		InputStream is=new FileInputStream(new File(System.getProperty("user.dir")+"/testcases/"+testcasefile));
		Workbook wb=StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		
		for (Sheet sht : wb) {
			for (Row r : sht) {
				for (Cell c : r) {
					switch (c.getColumnIndex()) {
					case 0:
						caseId=c.getStringCellValue();
						break;
					case 1:
						scenario=c.getStringCellValue();
						break;
					case 2:
						description=c.getStringCellValue();
						break;
					case 3:
						keyword=c.getStringCellValue();
						break;
					case 4:
						locator=c.getStringCellValue();
						break;
					case 5:
						additionallocator=c.getStringCellValue();
						break;
					case 6:
						testdata=c.getStringCellValue();
						break;
					case 7:
						platform=c.getStringCellValue();
						break;
					}
				}
				Testentity tst=new Testentity(caseId, scenario, description, keyword, locator, additionallocator, testdata, platform);
				test.add(tst);
			}
		}
		
		return test;
		
	}

}
