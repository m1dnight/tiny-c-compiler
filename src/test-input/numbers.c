int func(int x[10])
{
    int y;
    y = x;
    x = 2;
    return x;
}

int main()
{
    int x[10];

    x[0] = 0;
    x[1] = 1;
    x[2] = funct(x);
}
