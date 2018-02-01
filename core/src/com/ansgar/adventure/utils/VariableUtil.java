package com.ansgar.adventure.utils;

/**
 * Created by kirill on 1.11.17.
 */

public class VariableUtil {

    public static class strings {
        public static String TITLE = "RPG Adventure v.0.0.0.1";
        public static String[] SPRITES_RESOURCES_LIST = new String[]{
                "images/sprites/player/player1.png"
        };
    }

    public static class numbers {
        public static final int GAME_WIDTH = 320;
        public static final int GAME_HEIGHT = 240;

        public static final int SCALE = 2;
        public static final float PPM = 100;

        public static final float STEP = 1 / 60f;

        public static final int FPS = 60;
    }

    public static class bits {
        public static final short PLAYER_BIT = 2;
        public static final short PLAYER_FOOT_BIT = 4;
        public static final short BORDERS_BIT = 8;
        public static final short WATER_BIT = 16;
        public static final short ROAD_BIT = 32;
        public static final short ATTACK_BIT = 64;
    }

    public enum BitEnum {
        DEFAULT("default", 0),
        PLAYER_BIT("player", 2),
        PLAYER_FOOT_BIT("foot", 4),
        BORDERS_BIT("borders", 8),
        WATER_BIT("water", 16),
        WATER_FALL_1_BIT("water_fall1", 32),
        WATER_FALL_2_BIT("water_fall2", 64),
        ROAD_BIT("road", 128);

        private String name;
        private int bit;

        BitEnum(String name, int bit) {
            this.name = name;
            this.bit = bit;
        }

        public static BitEnum getBit(String name) {
            for (BitEnum bitEnum : BitEnum.values())
                if (bitEnum.name.trim().equalsIgnoreCase(name)) return bitEnum;
            return DEFAULT;
        }

        public String getName() {
            return name;
        }

        public short getBit() {
            return (short) bit;
        }
    }

    public static class data {
        public static final String PLAYER_FOOT = "FOOT_DATA";
    }

}
