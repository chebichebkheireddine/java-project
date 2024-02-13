package CalculatorOp;

import Calculator.CalculatorInterfacePOA;

public class CalculatorImpl extends CalculatorInterfacePOA {
  
  @Override
  public float add(float num1, float num2) {
    return num1 + num2;
  }

  @Override
  public float sup(float num1, float num2) {
    return num1 - num2;
  }

  @Override
  public float div(float num1, float num2) {
    if (num2 != 0) {
      return num1 / num2;
    } else {
      throw new IllegalArgumentException("Division by zero is not allowed.");
    }
  }

  @Override
  public float mul(float num1, float num2) {
    return num1 * num2;
  }
}
