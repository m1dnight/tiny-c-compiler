int factorial(int x)
{
    int result;
    if(x == 1)
	return 1;
    return x * factorial(x - 1);
}
int fib(int x)
{
  int result;
  if(x == 0)
    return 1;
  if(x == 1)
    return 1;
  if(x == 2)
    return 1;
  result = fib(x - 1) + fib(x - 2);
  return result;
}
int main()
{
    int x;
    int y;
    x = factorial(3);
}
