#include "batterie_test.h"
//#include "all_tests.h"
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

void test_validity(int result, int expected_result){
	if (result != expected_result){
		printf("\033[1;31mFAILED : \033[0m");
		printf("\tUnexpected Result : %d, the expected result was : %d\n",
					 result,
					 expected_result);
	} else {
    printf("\033[1;32mOK\n\033[0m");
  }
}

int main(int argc, char ** argv){
	int result;
  printf("\n");
  
  printf("Simple test (return 42) : ");
  test_validity(test_simple(), 42);

  printf("One parameter : ");
  test_validity(test_oppose(5), -5);

  printf("Two parameters (7 + 13) : ");
  test_validity(test_addition(7,13), 20);

  printf("Three parameters and priority (5 * 4 + 3) : ");
  test_validity(test_polynome_simple(5,4,3), 23);

  printf("Multiple functions call (add(5,4) + add(4,6) : ");
  test_validity(test_appel_imbrique(5,4,6), 19);
  
  printf("IF test (if i  == 10 , i++, else i = -4) : ");
  test_validity(test_logic(0), 0);

  printf("Testing multi-if : ");
  test_validity(test_multi_if(2, 13), 2);
  
  printf("Testing stack frame and recursion * trick * : ");
  /* TODO correct this function, the 7,9,10 parameters are actually ignored,
   * the result is false, but the origin very strange, should be done later */
  test_validity(test_somme_rec_multi_param(1,2,3,4,5,6,7,8,9,10,11), 40);

  printf("WHILE test (while i != 10, i++) : ");
  test_validity(test_while(0), 10);
  
  printf("Testing fibo(5): result = 8 : ");
  test_validity(test_fibo(5), 8);
  
  printf("Testing arrays: ");
  test_validity(test_array(), 27);
  
  printf("Testing binary operations : ");
  test_validity(test_binary(), 222);
  
  printf("Testing the printf instruction (2 + 3 = 5 should be displayed) : ");
  test_validity(test_printing(2,3),0);

  printf("Testing READ_INT: return read_int(a)+42 : (enter 1) ");
  test_validity(test_scan(), 43);
  
  printf("Testing READ_INT: return read_int(a)+42 : (enter 5) ");
  test_validity(test_scan(), 47);

  printf("Testing unreachable code : ");
  test_validity(test_unreachable(5), 2);
  
  printf("Testing global variables : ");
  test_validity(test_globals(5), 20);

	printf("Testing global arrays : ");
	test_validity(test_global_arrays(5), 5);

	printf("Testing static variable : ");
	test_validity(test_static(5), 10);

	printf("Testing static globals : ");
	test_validity(test_static_global(5), 13);

  printf("\n");
  
}
