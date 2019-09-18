package com.tool.model;

class ModelEntityException extends Exception
{
  public ModelEntityException()
  {
    super ("EntityException: this entity is not valid");
  }

  public ModelEntityException(String message)
  {
    super (message);
  }
}