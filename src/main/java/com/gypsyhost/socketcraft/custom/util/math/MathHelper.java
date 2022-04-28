package com.gypsyhost.socketcraft.custom.util.math;

public final class MathHelper {

    private MathHelper() {

    }

    public static int clamp(int a, int min, int max) {

        return a < min ? min : (a > max ? max : a);
    }

    public static int round(double d) {

        return (int) (d + 0.5D);
    }
}
