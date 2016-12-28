package com.mist.sciryl.app.helpers;

public class PipeBuilder {

    private PipeBuilder() {
    }

    public static <T> T piperize(T object, Pipe... pipes) {
        for (Pipe pipe : pipes) {
            object = (T) pipe.pipe(object);
        }

        return object;
    }

}
