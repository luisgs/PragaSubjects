/*===============================================================================
#
#          FILE:  automaton
# 
#         USAGE:  ./automaton.c pp
# 
#   DESCRIPTION:  
# 
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: LuisGS (), luis.ild@gmail.com
#       COMPANY: CVUT
#       CREATED: 15/11/11 16:27:11 CET
#      REVISION:  ---
#===============================================================================
*/
#include <stdio.h>
#include <iostream>

/* it is necessary  */
bool checkString(const char* inputString);


/* big case!  */
int take_step (int input_state, char input_symbol) {
	int state = 16;	// error state
	switch (input_state) {
		case 0:	if (input_symbol =='a') {
				state =1;
			} else if (input_symbol =='b') {
				state =2;
			} else if (input_symbol =='c') {
				state =3;
			}
 			break;
		case 1:	if (input_symbol =='b') {
				state =4;
			} 
			break;
		case 2:	if (input_symbol =='c') {
				state =5;
			} 
			break;
		case 3:	if (input_symbol =='a') {
				state =1;
			} else if (input_symbol =='b') {
				state =2;
			} else if (input_symbol =='c') {
				state =3;
			} else if (input_symbol=='d') {
				state =6;
			}
			break;
		case 4:	if (input_symbol =='b') {
				state =7;
			}
 			break;
		case 5:	if (input_symbol =='a') {
				state =9;
			} else if (input_symbol =='d') {
				state =8;
			}
			break;
		case 6:	if (input_symbol =='d') {
				state =10;
			} 
			break;
		case 7:	if (input_symbol =='c') {
				state =11;
			} 
			break;
		case 8:	if (input_symbol =='c') {
				state =12;
			} else if (input_symbol =='d') {
				state =8;
			} 
			break;
		case 9:	if (input_symbol =='d') {
				state =13;
			} 
			break;
		case 10:if (input_symbol =='d') {
				state =11;
			}
			break; 
		case 11:if (input_symbol =='d') {
				state =14;
			}
			break;
		case 12:if (input_symbol =='d') {
				state =15;
			}
			break;
		case 13:if (input_symbol =='a') {
				state =1;
			} else if (input_symbol =='b') {
				state =2;
			} else if (input_symbol =='c') {
				state =3;
			} else if (input_symbol == 'd'){
				state =14;
			}
			break;
		case 14:
			break;
		case 15:if (input_symbol =='d') {
				state =8;
			}
			break;
		case 16:
			break; // it is neccesary for breaking my function!
	}
	return state;
}

/* Final function. tell me which state is final or not  */
bool is_final (int state) {
	bool solution=false;
	switch (state) {
		case 8:
		case 9:
		case 10:
		case 14:
		case 15: solution = true;
	}
	return solution;
/*	return ((state ==8)? true : ((state==9)? true: ((state==10)? true : ((state==14)? true : ((state==15)? true : false)))));*/
}

bool checkString (const char* inputString) {
	int state=0, i=0;

	/* Ctrl+D or EOF = \0  */
	while (inputString[i]!='\0') {
		state = take_step(state, inputString[i]);
		i++;
	}

	return is_final(state);
}

#ifndef __PROGTEST__
int main() {
        using namespace std;
        string line;
        while (getline(cin, line)){
                cout << checkString(line.c_str()) << endl;
        }
}
#endif
