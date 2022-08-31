package io.github.tobiasz.util;

public interface IObserver<T> {
    void update(T data);
}
