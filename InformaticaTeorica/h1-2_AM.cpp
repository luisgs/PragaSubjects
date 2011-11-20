/*#===============================================================================
#
#          FILE:  hola.sh
# 
#         USAGE:  ./hola.sh 
# 
#   DESCRIPTION:  
# 
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: LuisGS (), luis.ild@gmail.com
#       COMPANY: CVUT
#       CREATED: 27/10/11 02:07:27 CEST
#      REVISION:  ---
#===============================================================================
*/
#include <iostream>
#include <stdio.h>
#include <string>            
#include <cstring>


using namespace std;



bool checkString(const char * inputString) {
	char *ptr; 
	
	for (ptr = inputString; *ptr != '\0'; ptr++) {
		cout << *ptr;
	}

	return true;	
}


//#ifndef __PROGTEST__

int main() {
	using namespace std;
	string line;
	while (getline(cin, line)){
		cout << checkString(line.c_str()) << endl;
	}
}

//#endif
 
