package com.gypsyhost.aventuria.custom.util;

public class MathHelper {

    private MathHelper() {

    }

    public static float clamp(float a, float min, float max) {

        return a < min ? min : (a > max ? max : a);
    }
}
