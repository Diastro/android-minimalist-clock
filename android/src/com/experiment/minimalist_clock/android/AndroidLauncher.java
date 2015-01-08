package com.experiment.minimalist_clock.android;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;
import com.experiment.minimalist_clock.Main;

public class AndroidLauncher extends AndroidLiveWallpaperService {
    @Override
    public void onCreateApplication () {
        super.onCreateApplication();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.numSamples = 2;
        ApplicationListener listener = new MyLiveWallpaperListener();
        initialize(listener, config);
    }

    // implement AndroidWallpaperListener additionally to ApplicationListener
    // if you want to receive callbacks specific to live wallpapers
    public static class MyLiveWallpaperListener extends Main implements AndroidWallpaperListener {

        @Override
        public void offsetChange (float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset,
                                  int yPixelOffset) {
            //Log.i("LiveWallpaper test", "offsetChange(xOffset:"+xOffset+" yOffset:"+yOffset+" xOffsetSteep:"+xOffsetStep+" yOffsetStep:"+yOffsetStep+" xPixelOffset:"+xPixelOffset+" yPixelOffset:"+yPixelOffset+")");
        }

        @Override
        public void previewStateChange (boolean isPreview) {
            //Log.i("LiveWallpaper test", "previewStateChange(isPreview:"+isPreview+")");
        }
    }
}