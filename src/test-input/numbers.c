int multiply(int x)
{
    return x+x;
}

int main()
{
    int x;
    int y;
    x = 10;
    y = 4;
    x = y + x;
    y = 12;
    x = y + x;
    x = multiply(x);
}
