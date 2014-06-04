int positivize(int x)
{
    if(x > 0)
    {
        return x;
        };
    return 0;
}

int factorial(int x)
{
    int result;
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
