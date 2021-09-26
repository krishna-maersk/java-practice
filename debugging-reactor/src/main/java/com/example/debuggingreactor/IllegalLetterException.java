package com.example.debuggingreactor;

public class IllegalLetterException  extends RuntimeException{
    public IllegalLetterException(){
        super("Can't be an F!");
    }
}
