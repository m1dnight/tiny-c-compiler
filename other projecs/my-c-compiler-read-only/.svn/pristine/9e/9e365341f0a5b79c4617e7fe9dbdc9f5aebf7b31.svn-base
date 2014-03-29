/* This test returns 42, test of function with no parameters */
int test_simple(void);

/* This function returns -x */
int test_oppose(int);
/* This function returns a + b */
int test_addition(int, int);

/* This function returns a * b + c */
int test_polynome_simple(int, int, int);

/* This function tests nested function calls,
	 it returns (a + 2) + (b + c) */
int test_appel_imbrique(int, int, int);

/* This function tests logic blocks */
int test_logic(int);

/* This function is an ultimate test about stack size and recursion */
int test_somme_rec_multi_param(int,
                               int,
                               int,
                               int,
                               int,
                               int,
                               int,
                               int,
                               int,
                               int,
                               int);

/* This function tests nested IF conditions */
int test_multi_if(int, int);

/* This function tests loops */
int test_while(int);

/* This function tests recursion */
int test_fibo(int);

/* This function tests array access */
int test_array(int);

/* This function tests binary operations */
int test_binary(void);

/* This function test the call to the printf function */
int test_printing(int, int);

/* This function tests the readInt instruction */
int test_scan(int);

/* This function tests the handling of unreachable code */
int test_unreachable(int);


/***** Implémentation *****/
int test_simple(void){
	int a;
	int b;
	a = 7;
	b = 6;
	return a * b; 
}

int test_oppose(int x){
	return -x;
}

int test_addition(int a, int b){
	return a + b;
}

int test_polynome_simple(int a, int b, int c){
	return a * b + c;
}

int test_appel_imbrique(int a, int b, int c){
	return addition(a,b) + addition(b,c);
}

int test_logic(int i) {
  if(i == 10)
    i = i + 1;
  ;
  return i;
}

/* Pour le septième paramètre, le souci est qu'après le
	 movq 16(%rbp),%rax
	 le contenu de %rax est sur 12 octets au lieu de 8, il y a
	 en fait 4 octets qui viennent s'ajouter d'on ne sait pas trop où...
	 il faudrait pouvoir déterminer comment ça se fait*/
int test_somme_rec_multi_param(int a,
							   int b,
							   int c,
							   int d,
							   int e,
							   int f,
							   int g,
							   int h,
							   int i,
							   int j,
							   int s){
	int somme;
	int tmp;
	somme = s;
	if (a == 0){
		if (b == 0){
			if (c == 0){
				if (d == 0){
					if (e == 0){
						if (f == 0){
							if (g == 0){
								if (h == 0){
									if (i == 0){
										if (j == 0){
											return somme;
										};
									};
								};
							};
						};
					};
				};
			};
		};
	};
	if (a > 0){
		a = a -1;
		somme = somme + 1;
	};
	if (b > 0){
		b = b -1;
		somme = somme + 1;
	};
	if (c > 0){
		c = c -1;
		somme = somme + 1;
	};
	if (d > 0){
		d = d -1;
		somme = somme + 1;
	};
	if (e > 0){
		e = e -1;
		somme = somme + 1;
	};
	if (f > 0){
		f = f -1;
		somme = somme + 1;
	};
	if (g > 0){
		g = g -1;
		somme = somme + 1;
	};
	if (h > 0){
		h = h -1;
		somme = somme + 1;
	};
	if (i > 0){
		i = i -1;
		somme = somme + 1;
	};
	if (j > 0){
		j = j -1;
		somme = somme + 1;
	};
	tmp = test_somme_rec_multi_param(a,b,c,d,e,f,g,h,i,j,somme);
	return tmp;
}

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

int test_while(int i) {
  while(i != 10)
    i = i + 1;
  ;
  return i;
}

int test_fibo(int i){
  if(i == 0)
    return 1;
  ;
  
  if(i == 1)
    return 1;
  ;
  
  return test_fibo(i-1) + test_fibo(i-2);
}

int test_array(int i){
  int c[3];
  c[0] = 0;
  c[1] = 1;
  c[2] = 2;
  
  return c[i%3];
}

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

int test_printing(int a, int b){
	printf("%d + %d = %d:", a, b, a + b);
	return 0;
}

int test_scan(int i){
  int a;
  a = 0;
  read_int(a);
  return a + 42;
}

int test_unreachable(int i){
  int a;
  while(i >= 1){
    
    if(i == 0)
      return 0;
    ;
    i = i - 1;
  };
  return 2;
  a = 10;
}

