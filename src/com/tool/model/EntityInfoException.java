package com.tool.model;


class EntityInfoException extends Exception
{

    public EntityInfoException()
    {
        super("this entityInfo is not valid");
    }

    public EntityInfoException(String s)
    {
        super(s);
    }
}