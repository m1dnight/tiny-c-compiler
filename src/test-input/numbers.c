char array[10];
int printChars(char min, int max)
{
    int x;
 write min;
 x = min;
 write x;

 if(min < max + 1)
    printChars(min + 1, max);
}

int main()
{
printChars(48, 127);
}