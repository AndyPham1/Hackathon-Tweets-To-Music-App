package com.hackwestern.hashtune.hashtune;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

import com.soundhelix.component.player.Player;
import com.soundhelix.misc.SongContext;
import com.soundhelix.util.SongUtils;

public class SoundHelixPlay extends Activity{
    public void play(String songTitle){

        try{
            //configure log4j
            PropertyConfigurator.configureAndWatch("log4j.properties", 60 * 1000);
            AssetManager manager = getApplicationContext().getAssets();
            InputStream inputStream = manager.open("soundhelix-0.8/generator/Generator");
            SongContext songContext = SongUtils.generateSong(inputStream, "", songTitle);

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
