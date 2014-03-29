int test_array(void);
int total_sum(int[3]);


int total_sum(int tab[3]){
  int sum;
  int index;
  sum = 0;
  index = 0;
  while(index < 3){
		index = index + 1;
		sum = sum + tab[index - 1];
	};
  return sum;
}

int test_array(void){
  int t[3];
  t[0] = 8;
  t[1] = 9;
  t[2] = 10;

  return total_sum(t);
}
