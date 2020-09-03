package com.otserv.api.core;

public interface UseCase<I, O> {
    O execute(I input);
}
