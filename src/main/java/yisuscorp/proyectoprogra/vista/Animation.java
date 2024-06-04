/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra.vista;

/**
 *
 * @author jesus
 */
import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;

    public Animation(BufferedImage[] frames, long delay) {
        this.frames = frames;
        this.delay = delay;
        this.startTime = System.nanoTime();
        this.currentFrame = 0;
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }

        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }
}
