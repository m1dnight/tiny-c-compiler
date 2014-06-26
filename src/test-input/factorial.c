int factorial(int xParamOfFactorial)
{
    int result;
    result = xParamOfFactorial * factorial(xParamOfFactorial - 1);
    return sum;
}
int main(int mainPAram)
{
    int factorialInput;
    factorialInput = 5;
    write factorialInput;
    return factorial(factorialInput);
}