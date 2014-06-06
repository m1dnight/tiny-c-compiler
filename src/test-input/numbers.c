int positivize(int x)
{
    int y;

    y = 3;
    y = x + 1 + 2 + 3;
    if(x > 0)
        x = 123456789;

    return 0;
}

int factorial(int x)
{
    int result;
    x = 3;
    result = x * factorial(x -1);
    return result;
}

int main(int mainParam)
{
    int x;
    x = 5;
    mainParam = positivize(x);
    return mainParam;

}
