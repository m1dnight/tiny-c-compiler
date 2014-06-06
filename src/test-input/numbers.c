int positivize(int x)
{
    int y;
    if(x == 0)
    {
        x = 123456789;
    }
    else
    {
        x= 45466;
    };

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
