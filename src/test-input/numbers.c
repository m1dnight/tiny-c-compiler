int factorial(int x)
{
    int result;
    result = x * factorial(x - 1);
    return result;
}

int main()
{
    int x;
    int y;
    int z;
    int a;
    int b;
    int c;
    int d;
    int e;
    a = 2 + 2;
    b = 2 + 2;
    d = b;
    b = a;
    e = d + c;
    return factorial(e);
}
