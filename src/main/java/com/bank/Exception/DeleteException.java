package com.bank.Exception;

public class DeleteException extends Exception{
    public DeleteException(){
        super("*****   AUCUNNNE SUPPRESSION EST AFFECTE   *****");
    }
    public DeleteException(String message){
        super(String.format("*****   %s   *****", message));
    }
}
