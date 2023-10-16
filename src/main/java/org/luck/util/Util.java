package org.luck.util;

import org.luck.type.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("unused")
public class Util {

    public static boolean isBetween(double i, double min, double max) {return i >= min && i <= max;}

    public static boolean mod(double y, double z) {return (y % z == 0);}

    public static boolean mod(int y, int z) {return (y % z == 0);}

    public static double round(double i) {return Math.round(i);}

    public static float round(float i) {return Math.round(i);}

    public static float random(float min, float max) {
        double inter = max - min + 1;
        return (float) ((Math.random() * inter) + min);
    }

    public static int random(int min, int max) {
        int inter = max - min + 1;
        return (int) ((Math.random() * inter) + min);
    }

    public static double lerp(double from, double to, double at) {return from * (1.0 - at) + (to * at);}

    public static float lerp(float from, float to, float at) {return (float) (from * (1.0 - at) + (to * at));}

    public static int choose(int... args) {return args[random(0, args.length - 1)];}

    public static double choose(double... args) {return args[random(0, args.length - 1)];}

    public static String choose(String... args) {return args[random(0, args.length - 1)];}

    public static Object choose(Object... args) {return args[random(0, args.length - 1)];}

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

    public static String getContentURL(String URL) {
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

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

    public static double getDistance(Vector2D a, Vector2D b) {
        double xx = a.getX() - b.getX();
        double yy = a.getY() - b.getY();
        return Math.abs(xx + yy);
    }
}