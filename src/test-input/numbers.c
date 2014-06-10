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
    a = 8 / 2;
    a = 1 > 2;
    a = 1 < 2;
    a = 2 * 123;
    x = a + 1;
    y = 246 + 1;
    a = 1 == 2;
    b = 2 + 2;
    d = b;
    c = 3;
    b = a;
    e = d + c;
    return factorial(e);
}
