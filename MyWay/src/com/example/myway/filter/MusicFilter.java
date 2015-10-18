package com.example.myway.filter;

import java.io.File;
import java.io.FilenameFilter;

public class MusicFilter implements FilenameFilter {

	@Override
	public boolean accept(File file, String name) {
		return (name.endsWith(".mp3"));
	}

}
