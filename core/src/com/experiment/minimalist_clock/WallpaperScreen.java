package com.experiment.minimalist_clock;

import com.badlogic.gdx.Screen;

/**
 * Created by David on 1/7/2015.
 */
public class WallpaperScreen implements Screen {
    private Environment environment;
    private Renderer renderer;

    public WallpaperScreen(){
        this.environment = new Environment();
        this.renderer = new Renderer(environment);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.environment.update();
        this.renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        this.renderer.setCamera();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
