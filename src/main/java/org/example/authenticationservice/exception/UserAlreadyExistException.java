package org.example.authenticationservice.exception;

public class UserAlreadyExistException extends Exception
{
    public UserAlreadyExistException(String msg)
    {
        super(msg);
    }
}
