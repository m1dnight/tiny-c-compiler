int returnOne()
{
  return 12;
}

int factorial(int x)
{
    int result;		
    if(x == 1)
	return 1;
    return x * factorial(x - 1);
}
int main()
{
    int x;
    int y;
    x = factorial(3);
}
