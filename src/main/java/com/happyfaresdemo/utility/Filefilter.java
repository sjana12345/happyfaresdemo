package com.happyfaresdemo.utility;

import java.io.File;
import java.io.FileFilter;

public class Filefilter {

	public static File[] getfiles() {
		File directory = new File(System.getProperty("user.dir") + "/testcases");
		File[] files = directory.listFiles();
		return files;
	}
}
