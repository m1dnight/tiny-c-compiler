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

}

int printArray(int array[5])
{
    write array[0];
    write array[1];
    write array[2];
    write array[3];
    write array[4];
}
int main()
{
    int result;
/*    int array[5];
    array[0] = 1;
    array[1] = 2;
    array[2] = 3;
    array[3] = 4;
    array[4] = 5;
        result = printArray(array);
    result = reverseArray(array);



    result = printArray(array);*/

    read result;
    result = result * 2;
    write result;
    return result;
}