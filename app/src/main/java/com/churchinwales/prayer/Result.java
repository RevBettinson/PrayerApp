package com.churchinwales.prayer;

/**
 * A Barebones class to hold the result of the HTML request
 *
 * https://developer.android.com/guide/background/threading
 * @param <T>
 */
public abstract class Result<T> {
    private Result() {}

    public static final class Success<T> extends Result<T> {
        public T data;
        public T type;

        public Success(T type, T data) {
            this.type= type;
            this.data = data;
        }
    }

    public static final class Error<T> extends Result<T> {
        public Exception exception;

        public Error(Exception exception) {
            this.exception = exception;
        }
    }
}
