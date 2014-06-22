int sum(int param[3])
{
    int sum;
    sum = 0;
    sum = sum + param[0];
    sum = sum + param[1];
    sum = sum + param[2];

    return sum;
}
int main()
{
    int a;
    int x[3];
    int y[2];
    x[0] = 100;
    x[1] = 20;
    x[2] = 20;

    y = sum(x);
}
