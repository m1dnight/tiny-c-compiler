int test_multi_if(int, int);

int test_multi_if(int a, int b){
  int x;
  x = 1;
  if(a < 10){
    if(b < 10){
      return 42;
    };
    return 2;
  }
  else
    return 0;
  ;
  x = 3;
}
