reset
set encoding utf8
set multiplot
  set origin 0,0
  set size 1,1
  set title "Revenue of a resturant in 2010"
  set ylabel "Revenue (thousands CZK)"
  set xlabel "Month"
  set grid

  set key left top

  plot "data.txt" using 1:2 axes x1y1 with lines linecolor rgbcolor "blue", \
	"" using 1:2 axes x1y2 with linespoints linewidth 2 linecolor rgbcolor "red"




  set title "Item Element Count\n(Počet elementů)"
  set origin 0.14,0.4
  set size 0.31,0.4



  unset ylabel

  unset xlabel

  set boxwidth 0.4

  set style fill pattern 6



  clear

  plot [-1:5.7] "data.txt" using 5:xtic(1) with boxes notitle

unset multiplot
