int x[3];
/*int ackermann(int m, int n){
        if (m == 0) return n + 1;
        if (n == 0) return ackermann(m - 1, 1);
        return ackermann(m - 1, ackermann(m, n - 1));
}

int reverseArray(int array[5])
{
    int intermediate;
    intermediate = array[4];
    array[4] = array[0];
    array[0] = intermediate;

    intermediate = array[3];
    array[3] = array[1];
    array[1] = intermediate;

}

int printArray(int array[5])
{
    write array[0];
    write array[1];
    write array[2];
    write array[3];
    write array[4];
}*/

int increase()
{
    x[0] = 1;
    x[1] = 2;
    x[2] = 3;
}
int main()
{
    write x[0];
    write x[1];
    write x[2];
    increase();
    write x[0];
    write x[1];
    write x[2];

}