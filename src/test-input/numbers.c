int multiply(int x)
{
    int y;
    y = x;  //27
    x = y + 2;  //29
    y = x + 3; // 32
    x = y; //32
    return x+x;
}

int main()
{
    int x;
    int y;
    x = 10;
    y = 4;
    x = y + x; // 14
    y = 13;
    x = y + x; // 27
    x = multiply(x);
}
