int sum(int param[2])
{
    int sum;
    int arr[5];
    sum = 0;
    sum = sum + param[0];
    sum = sum + param[1];

    return sum;
}
int main()
{
    int x[2];
    int y;

    y = sum(x);
}
