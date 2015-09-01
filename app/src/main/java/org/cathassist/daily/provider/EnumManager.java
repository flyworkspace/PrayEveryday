package org.cathassist.daily.provider;

public class EnumManager {
    public final static int WEEKDAY_VALUE = 0;
    public final static int COMMEMORATION_VALUE = 1;
    public final static int OPTIONAL_VALUE = 2;
    public final static int MEMORIAL_VALUE = 3;
    public final static int FEAST_VALUE = 4;
    public final static int SUNDAY_VALUE = 5;
    public final static int LORD_VALUE = 6;
    public final static int ASHWED_VALUE = 7;
    public final static int HOLYWEEK_VALUE = 8;
    public final static int TRIDUUM_VALUE = 9;
    public final static int SOLEMNITY_VALUE = 10;

    public enum ContentType {
        LAUDES(0), HORAMEDIA(1), VESPERAE(2), COMPLETORIUM(3), MATUTINUM(4), MASS(
                5);
        private int value;

        private ContentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static ContentType getContentType(int value) {
            ContentType type = null;
            switch (value) {
                case 0:
                    type = ContentType.LAUDES;
                    break;
                case 1:
                    type = ContentType.HORAMEDIA;
                    break;
                case 2:
                    type = ContentType.VESPERAE;
                    break;
                case 3:
                    type = ContentType.COMPLETORIUM;
                    break;
                case 4:
                    type = ContentType.MATUTINUM;
                    break;
                case 5:
                    type = ContentType.MASS;
            }
            return type;

        }

        public static String getContentDataNameFromContentType(int value) {
            String string = "";
            switch (value) {
                case 0:
                    string = "lod";
                    break;
                case 1:
                    string = "med";
                    break;
                case 2:
                    string = "ves";
                    break;
                case 3:
                    string = "comp";
                    break;
                case 4:
                    string = "let";
                    break;
                case 5:
                    string = "mass";
            }
            return string;
        }
    }

    public enum rank_t {
        WEEKDAY(WEEKDAY_VALUE),
        COMMEMORATION(COMMEMORATION_VALUE),
        OPTIONAL(OPTIONAL_VALUE),
        MEMORIAL(MEMORIAL_VALUE),
        FEAST(FEAST_VALUE),
        SUNDAY(SUNDAY_VALUE),
        LORD(LORD_VALUE),
        ASHWED(ASHWED_VALUE),
        HOLYWEEK(HOLYWEEK_VALUE),
        TRIDUUM(TRIDUUM_VALUE),
        SOLEMNITY(SOLEMNITY_VALUE);
        private final int value;

        private rank_t(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static rank_t getRank_tType(int value) {
            rank_t type = null;
            switch (value) {
                case 0:
                    type = rank_t.WEEKDAY;
                    break;
                case 1:
                    type = rank_t.COMMEMORATION;
                    break;
                case 2:
                    type = rank_t.OPTIONAL;
                    break;
                case 3:
                    type = rank_t.MEMORIAL;
                    break;
                case 4:
                    type = rank_t.FEAST;
                    break;
                case 5:
                    type = rank_t.SUNDAY;

                case 6:
                    type = rank_t.LORD;
                    break;
                case 7:
                    type = rank_t.ASHWED;
                    break;
                case 8:
                    type = rank_t.HOLYWEEK;
                    break;
                case 9:
                    type = rank_t.TRIDUUM;
                    break;
                case 10:
                    type = rank_t.SOLEMNITY;
            }
            return type;

        }
    }

    public enum FontSize {
        SMALL(80),
        NORMAL(100),
        LARGE(120),
        LARGEST(150);

        FontSize(int size) {
            value = size;
        }

        int value;
        public int getValue() {
            return value;
        }
        public static FontSize getTextSizeBySize(int size) {
            switch (size) {
                case 80:
                    return SMALL;
                case 100:
                    return NORMAL;
                case 120:
                    return LARGE;
                case 150:
                    return LARGEST;
                default:
                    return NORMAL;
            }
        }
    }
}
