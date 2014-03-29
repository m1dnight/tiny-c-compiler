int aux(int);
int test_static(int);

int aux(int c) {
 	static int b;	
	b = b + c;
	return b;
}

int test_static(int a) {
	int d;
	d = aux(a);
	d = aux(a);
	return d;
}
