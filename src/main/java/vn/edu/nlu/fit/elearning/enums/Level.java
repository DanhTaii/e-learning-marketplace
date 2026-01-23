package vn.edu.nlu.fit.elearning.enums;

public enum Level {
    BEGINNER("Sơ cấp"),
    INTERMEDIATE("Trung cấp"),
    ADVANCED("Cao cấp");
    private final String vietnameseName;
    Level(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }
    public String getVietnameseName() {
        return vietnameseName;
    }
}
