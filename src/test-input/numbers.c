int factorial(int x)
{
    int result;
    result = factorial(x - 1) * x;
    return result;
}

int main(int mainParam)
{
    mainParam = factorial(mainParam);
    return mainParam;
}
