#!/usr/bin/env python3
"""
Simple calculator to multiply two numbers
"""


def multiply(num1, num2):
    """
    Multiply two numbers and return the result.

    Args:
        num1: First number
        num2: Second number

    Returns:
        The product of num1 and num2
    """
    return num1 * num2


def main():
    """
    Main function to get user input and calculate multiplication.
    """
    print("Multiplication Calculator")
    print("-" * 25)

    try:
        num1 = float(input("Enter the first number: "))
        num2 = float(input("Enter the second number: "))

        result = multiply(num1, num2)

        print(f"\nResult: {num1} × {num2} = {result}")

    except ValueError:
        print("Error: Please enter valid numbers.")
    except KeyboardInterrupt:
        print("\n\nCalculation cancelled.")


if __name__ == "__main__":
    main()
