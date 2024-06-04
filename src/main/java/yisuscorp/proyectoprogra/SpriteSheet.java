/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yisuscorp.proyectoprogra;

/**
 *
 * @author jesus
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    public SpriteSheet(String path, int spriteWidth, int spriteHeight) throws IOException {
        this.spriteSheet = ImageIO.read(new File(path));
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public BufferedImage getSprite(int x, int y) {
        return spriteSheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
    }
}
