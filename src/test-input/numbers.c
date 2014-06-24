int ackermann(int m, int n){
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

    intermediate = array[2];
    array[2] = array[0];
    array[0] = intermediate;
}
int main()
{
    int s;


    return s;
}