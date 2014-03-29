int test_sg_aux(int);
int test_static_global(int);

static int a;

int test_sg_aux(int q) {
	a = a + q;
	return a;
}

int test_static_global(int b) {
	a = b;
	a = test_sg_aux(8);
	return a;
}
