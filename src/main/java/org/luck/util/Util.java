package org.luck.util;

import org.luck.type.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("unused")
public class Util {

    public static boolean isBetween(double i, double min, double max) { return i >= min && i <= max; }

    public static boolean mod(double y, double z) { return (y % z == 0); }

    public static boolean mod(int y, int z) { return (y % z == 0); }

    public static double round(double i) { return Math.round(i); }

    public static float round(float i) { return Math.round(i); }

    public static float random(float min, float max) {
        double inter = max - min + 1;
        return (float) ((Math.random() * inter) + min);
    }

    public static int random(int min, int max) {
        int inter = max - min + 1;
        return (int) ((Math.random() * inter) + min);
    }

    public static double lerp(double from, double to, double at) { return from * (1.0 - at) + (to * at); }

    public static float lerp(float from, float to, float at) { return (float) (from * (1.0 - at) + (to * at)); }

    public static int choose(int[] args) { return args[random(0, args.length - 1)]; }

    public static double choose(double[] args) { return args[random(0, args.length - 1)]; }

    public static String choose(String[] args) { return args[random(0, args.length - 1)]; }

    public static Object choose(Object[] args) { return args[random(0, args.length - 1)]; }

    public static Image getImageURL(String url) {
        URL str;
        Image img;
        try {
            str = new URL(url);
            img = ImageIO.read(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static Image rotateImage(Image image, double angleDegrees, int pivotX, int pivotY) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double angleRadians = Math.toRadians(angleDegrees);

        AffineTransform transform = new AffineTransform();

        transform.translate(-pivotX, -pivotY);
        transform.rotate(angleRadians);
        transform.translate(pivotX, pivotY);

        BufferedImage rotatedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    public static String getContentFromURL(String URL) {
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            con.disconnect();

            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getDistance(Location a, Location b) {
        return Math.sqrt(Math.pow(b.getY() - a.getX(), 2) + Math.pow(b.getX() - a.getY(), 2));
    }
}