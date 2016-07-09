package com.mrebollob.loteriadenavidad.domain.exception;

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
