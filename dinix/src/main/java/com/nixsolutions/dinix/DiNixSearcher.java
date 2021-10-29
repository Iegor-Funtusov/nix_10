package com.nixsolutions.dinix;

import org.reflections.Reflections;

public class DiNixSearcher {

    private final Reflections scanner;

    public DiNixSearcher() {
        this.scanner = new Reflections(this.getClass().getPackageName());
    }

    public Reflections getScanner() {
        return scanner;
    }
}
