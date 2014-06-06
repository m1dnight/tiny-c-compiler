int factorial(int x)
{
    int result;
    x = 3;
    result = x * factorial(x -1);
    return result;
}

int positivize(int x)
{
    int y;
    if(x == 0)
    {
        y = x * factorial(x - 1);
    }
    else
    {
        y = 45466;
    };
    return 0;
}



int main(int mainParam)
{
    int x;
    x = 5;
    mainParam = positivize(x);
    return mainParam;
}
