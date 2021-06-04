package com.ernie.mock_instagram_server.service.note.sort;

public enum SortRule {
    BY_NOTHING("nothing"),
    BY_EARLIEST("earliest"),
    BY_LATEST("latest"),
    BY_PRIORITY_HIGH("priority_high"),
    BY_PRIORITY_LOW("priority_low"),
    ;

    private final String alia;

    SortRule(String s) {
        this.alia = s;
    }

    @Override
    public String toString() {
        return this.alia;
    }
}
