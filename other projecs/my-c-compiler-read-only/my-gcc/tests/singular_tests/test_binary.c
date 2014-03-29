int test_binary(void);

int test_binary(void){
	int a;
  int b;
  a = 1;
  b = 0;
  
  if(!a)
		return 111;
	;
	if(!(a & b))
		return 222;
	;

	return 333;
}
