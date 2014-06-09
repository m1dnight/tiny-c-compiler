int factorial(int xParamOfFactorial)
{
    int result;
    result = xParamOfFactorial * factorial(xParamOfFactorial - 1);
}
int main(int mainPAram)
{
    int factorialInput;
    factorialInput = 5;
    return factorial(factorialInput);
}