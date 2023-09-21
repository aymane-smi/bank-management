package com.bank.Exception;

public class InsertionException extends Exception{
    public InsertionException(){
        super("*****   ERREUR D'INSERTION LORS D'EXECUTION DE LA REQUETTE   *****");
    }

    public InsertionException(String message){
        super(String.format("*****   %s   *****", message));
    }
}
