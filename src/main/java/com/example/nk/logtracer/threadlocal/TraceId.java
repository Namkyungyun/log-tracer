package com.example.nk.logtracer.threadlocal;
import lombok.Getter;
import java.util.UUID;

@Getter
public class TraceId {

    final private String id;
    final private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(this.id, this.level+1);
    }

    public TraceId createPreviousId() {
        return new TraceId(this.id, this.level-1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
