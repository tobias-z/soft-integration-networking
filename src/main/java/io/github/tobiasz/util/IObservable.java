package io.github.tobiasz.util;

public interface IObservable<T> {
    void broadcast(T data);
    void add(IObserver<T> observer);
    void remove(IObserver<T> observer);
}
