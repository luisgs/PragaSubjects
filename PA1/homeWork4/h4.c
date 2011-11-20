int divideLand ( double x[], double y[], int n, double * diff )	{
	int i;
	int cu1rpo=0, cu2rpo=0;
	double d=0 , mas=0;
	double s_tie, n_tie, t_tie=0;

	/* if we have 3 or less->exit  */
	if (n<=3) {
		return 0;
	}

	for (i=0;i<n;i++) {
		if (i==n-1) {
			mas = mas +  x[i]*y[0]-x[0]*y[i];
		} else {
			mas = x[i]*y[i+1]-x[i+1]*y[i] + mas;
		}
	}
	mas = mas / 4;

	while (cu1rpo < (n-2)) {
		cu2rpo = cu1rpo+2;
		n_tie = 0;

		while (cu2rpo < n) {
			s_tie = n_tie;
			t_tie =0;
		    
			t_tie = t_tie + x[cu1rpo]*y[cu2rpo-1]-x[cu2rpo-1]*y[cu1rpo];
			t_tie = t_tie + x[cu2rpo-1]*y[cu2rpo]-x[cu2rpo]*y[cu2rpo-1];
			t_tie = t_tie + x[cu2rpo]*y[cu1rpo]-x[cu1rpo]*y[cu2rpo];

			n_tie = n_tie + t_tie/2;

			if (n_tie-mas > -1) {	/* always positive */
				if (cu2rpo==(cu1rpo+2) || n_tie-mas-(mas-s_tie)<=0) {
					if (cu1rpo==0) {
						d=(n_tie-mas)*2;
					} else if (d-(n_tie-mas)*2>0) {
						d=(n_tie-mas)*2;
					}
				} else {
					if (cu1rpo==0) {
						d=(mas-s_tie)*2;
					} else if (d-((mas-s_tie)*2)>0) {
						d=(mas-s_tie)*2;
					}
				}
				n_tie = 0;
				break;
			}
			cu2rpo++;
		}
		cu1rpo++;
	}
	*diff=d;
	return 1;
}
