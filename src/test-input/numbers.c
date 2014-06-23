int sum(int a[3])
{
    return a[0] + a[1] + a[2];
}
int multiply(int x, int y)
{
    return x * y;
}
int main()
{
    int s;
    int x[3];
    x[0] = 10;
    x[1] = 11;
    x[2] = 12;

    s = sum(x);
    return s;
}