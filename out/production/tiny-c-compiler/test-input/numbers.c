int factorial(int x)
{
    int a;
    int b;
    int c;
    a = 5;
    b = 2;
    c = a + b;
    return c;
}

int main(int mainPAram)
{
    int x;
    int a;
    x = 5;
    if(x > 2)
    {
        x = 5;
    }
    else
    {
        x = 7;
    };
    a = factorial(1 + 2, factorial(2));

}
/*
x = 5
_var1 = x > 2
ifFalse _var1 GOTO _label1
x = 5
GOTO _label3
_label1
x = 7
_label3
a = 2
*/
