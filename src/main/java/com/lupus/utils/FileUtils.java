package com.lupus.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
	public static List<File> getAllFilesInDir(Path dir){
		List<String> result = new ArrayList<>();
		try(Stream<Path> walk = Files.walk(dir)){
			result = walk.map(Path::toString)
					.filter(f -> f.endsWith(".yml")).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<File> endResult = new ArrayList<>();
		for(int i=0;i<result.size();i++){
			endResult.add(new File(result.get(i)));
		}

		return endResult;
	}
}
