package com.tunegenerator.hashtune;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

import com.soundhelix.component.player.Player;
import com.soundhelix.misc.SongContext;
import com.soundhelix.util.SongUtils;

public class SoundHelixPlay {
	public static void play(String songTitle){
		
		try{
			//configure log4j
			PropertyConfigurator.configureAndWatch("log4j.properties", 60 * 1000);
			
			SongContext songContext = SongUtils.generateSong(new URL("file:soundhelix-0.8/generator/Generator.xml"), songTitle);
			
			//use this instead to generate a specific song based on the given song name
			// SongContext songContext = SongUtils.generateSong(new URL("http://www.soundhelix.com/applet/examples/SoundHelix-Piano.xml"),
            //                                                  "My song name");
			
			Player player = songContext.getPlayer();
			
			player.open();
			player.play(songContext);
			player.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
