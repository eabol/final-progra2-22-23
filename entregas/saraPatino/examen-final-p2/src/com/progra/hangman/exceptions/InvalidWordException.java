package com.progra.hangman.exceptions;

public class InvalidWordException extends Exception {
    public InvalidWordException(String text) {
        System.out.println(text);
    }
}
