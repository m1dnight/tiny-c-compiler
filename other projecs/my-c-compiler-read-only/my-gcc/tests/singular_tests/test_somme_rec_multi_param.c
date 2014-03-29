int test_somme_rec_multi_param(int, int, int, int, int, int, int, int, int, int, int);

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
							//if (g == 0){
								if (h == 0){
									//if (i == 0){
										//if (j == 0){
											return somme;
										//};
									//};
								};
							//};
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
	/*if (g > 0){
		g = g -1;
		somme = somme + 1;
	};*/
	if (h > 0){
		h = h -1;
		somme = somme + 1;
	};
	/*if (i > 0){
		i = i -1;
		somme = somme + 1;
	};
	if (j > 0){
		j = j -1;
		somme = somme + 1;
	};*/
	tmp = test_somme_rec_multi_param(a,b,c,d,e,f,g,h,i,j,somme);
	return tmp;
}
