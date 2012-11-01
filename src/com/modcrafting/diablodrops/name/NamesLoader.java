package com.modcrafting.diablodrops.name;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.modcrafting.diablodrops.Main;

public class NamesLoader {
	File dataFolder;
	Main plugin;
	public NamesLoader(Main instance){
		plugin=instance;
		dataFolder=instance.getDataFolder();
	}
	public void writeDefault(String name) {
		File actual = new File(dataFolder, name);
		if (!actual.exists()) {
			try {
				InputStream input = this.getClass().getResourceAsStream("/" + name);
				FileOutputStream output = new FileOutputStream(actual);
				byte[] buf = new byte[8192];
				int length = 0;
				while ((length = input.read(buf)) > 0) {
					output.write(buf, 0, length);
				}
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	public void loadFile(List<String> l,String name){
		try{
			BufferedReader list = new BufferedReader(new FileReader(new File(dataFolder,name)));
			String p;
			while ((p = list.readLine()) != null){
				if(!p.contains("#")&&p.length()>0){
					l.add(p);
				}
			}
			list.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}