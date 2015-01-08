package com.experiment.minimalist_clock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by David on 1/7/2015.
 */
public class Renderer {
    private Environment environment;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public int SCREEN_MIDDLE_X = SCREEN_WIDTH/2;
    public int SCREEN_MIDDLE_Y = SCREEN_HEIGHT/2;
    public int CLOCK_RADIUS = 0;

    public Renderer(Environment environment) {
        this.environment = environment;
        setCamera();

        // optimal clock radius based on screen dimensions
        if(SCREEN_HEIGHT > SCREEN_WIDTH){
            CLOCK_RADIUS = (int)Math.round((SCREEN_WIDTH * 0.3));
        }else{
            CLOCK_RADIUS = (int)Math.round((SCREEN_HEIGHT * 0.3));
        }

        Gdx.app.log("Renderer Width ", String.format("%d", SCREEN_WIDTH));
        Gdx.app.log("Renderer HEIGHT ", String.format("%d", SCREEN_HEIGHT));
    }

    public void render(float delta){
        // Clear canvas
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        Gdx.gl.glEnable(GL20.GL_BLEND);

        int hours = this.environment.getHours();
        int minutes = this.environment.getMinutes();
        int seconds = this.environment.getSeconds();
        int miliseconds = this.environment.getMiliseconds();

        int hoursDeg = hours * 30;
        int minutesDeg = minutes * 6;
        int secondsDeg = seconds * 6;
        float milisecondsDeg = (float) miliseconds * 0.36f;

        Color white = new Color(255/255.0f, 255/255.0f, 255/255.0f, 1);
        Color red = new Color(255/255.0f, 0/255.0f, 0/255.0f, 1);
        Color light_grey = new Color(200/255.0f, 200/255.0f, 200/255.0f, 1);
        Color dark_grey = new Color(150/255.0f, 150/255.0f, 150/255.0f, 1);

        int startDeg = 270;
        // Hours
        drawArc(startDeg, hoursDeg, CLOCK_RADIUS, 14, 100, white);
        drawHandle(hoursDeg + 270, CLOCK_RADIUS, 70, 100, white);

        // Minutes
        drawArc(startDeg, minutesDeg, CLOCK_RADIUS-15, 8, 100, light_grey);

        // Seconds
        drawArc(startDeg, secondsDeg, CLOCK_RADIUS-36, 8, 100, dark_grey);

        // Miliseconds
        if(seconds % 2 == 0){
            drawArc(startDeg, milisecondsDeg, CLOCK_RADIUS-50, 5, 100, white);
        } else {
            drawArc(milisecondsDeg - 90, (360 - milisecondsDeg), CLOCK_RADIUS-50, 5, 100, white);
            drawArc(startDeg-3, 4, CLOCK_RADIUS-50, 5, 5, white); // Temp fix (flickering)
        }

        // Second's handle
        drawHandle(minutesDeg + 270, CLOCK_RADIUS-70, 63, 100, light_grey);

        // Font
        // Reference : https://gamedev.stackexchange.com/questions/72682/exception-in-thread-lwjgl-application
    }

    private void drawArc(float startDeg, float deg, int minRadius, int thickness, int segments, Color color){
        // Colored Arc
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.arc(SCREEN_MIDDLE_X, SCREEN_MIDDLE_Y, minRadius + thickness, startDeg, deg, segments);
        shapeRenderer.end();

        // overlay
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
        shapeRenderer.arc(SCREEN_MIDDLE_X, SCREEN_MIDDLE_Y, minRadius, startDeg-2, deg+4, segments);
        shapeRenderer.end();
    }

    private void drawHandle(float deg, int minRadius, int thickness, int segments, Color color){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Background Arc
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0/255.0f, 0/255.0f, 0/255.0f, 0.65f);
        shapeRenderer.arc(SCREEN_MIDDLE_X, SCREEN_MIDDLE_Y, minRadius + thickness, deg-5, 10, segments);
        shapeRenderer.end();

        // Foreground Arc
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.arc(SCREEN_MIDDLE_X, SCREEN_MIDDLE_Y, minRadius + thickness, deg-3, 6, segments);
        shapeRenderer.end();

        // overlay
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0/255.0f, 0/255.0f, 0/255.0f, 1.0f);
        shapeRenderer.arc(SCREEN_MIDDLE_X, SCREEN_MIDDLE_Y, minRadius, deg-4, 8, segments);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void setCamera(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
}
