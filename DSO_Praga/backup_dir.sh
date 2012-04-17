#!/bin/bash  
#===============================================================================
#
#          FILE:  backup_dir.sh
# 
#         USAGE:  ./backup_dir.sh 
# 
#   DESCRIPTION:  
# 
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: LuisGS (), luis.ild@gmail.com
#       COMPANY: CVUT
#       CREATED: 07/03/12 20:44:29 CET
#      REVISION:  ---
#===============================================================================

set -o nounset                              # Treat unset variables as an error

# Variables
NAME=;		# tar filename
COUNT=1;	# Highest number
DIREC=$PWD;	# directory destiny
# Fin Variables

function ayuda {
echo "Use: $0 [DIR] SOURCE_DIRECTORY
Examples:
	$0 . /tmp/foo/			# Save foo like foo.tar.gz in actual workdirectory
	If you continue:
	$0 . /tmp/foo/			# Save incremental backup: fooNUMBER.tar.gz

	$0 /tmp/ /home/user/nil/	# Save nil like nil.tar.gz in \"tmp\" directory.
	If you continue:
	$0 /tmp/ /home/user/nil/	# You will save another incremental backup nilNUMBER.tar.gz

Help: 	$0 will save incremental backups if there are some copy before;
	$0 will write numbered names." >&2
}

function errordirectory () {
	echo "You wrote a directory path wrong.">&2;
	echo "'"$1"' is not a directory.">&2;
	echo "Try '$0 --help' o '$0 --usage' for your information.">&2;
}

function compress () {
	COUNT=1;	# Highest number
	# Are there more filenames with this name?
	if [ -e "$2$1.tar.gz" ] 
	then
		# what is it the next one number?
		while [ -e "$2$1$COUNT.tar.gz" ]
		do
			COUNT=`echo "$COUNT + 1" | bc`;	# Increase $count
		done
		tar czfP "$2$1$COUNT.tar.gz" "$3"  2> /dev/null 
		VALUE=$?;
		echo "Directory \"$3\" compressed in \"$2$1$COUNT.tar.gz\"."
		exit $VALUE
	else
		# echo "doesnt exists"
		tar czfP "$2$1.tar.gz" "$3" 2> /dev/null #> /dev/stderr
		VALUE=$?;
		echo "Directory \"$3\" compressed in \"$2$1.tar.gz\"."
		exit $VALUE
	fi
}

if [ $# = 0 ] # We have not arguments.
then
	echo "$0: It has not options.">&2
	echo "Try '$0 --help' o '$0 --usage' for your information.">&2
	exit 1;
elif [ $# = 1 ]
then
	if [ "$1" = "--help" ] || [ "$1" = "--usage" ]
	then
		ayuda;
		exit 0;
	elif [ -d "$1" ] # Is it a directory?
	then
		# Extract filename from $1 
		NAME=`basename "$1"`;
		DIREC="$PWD/";
		if [ "$1" = "." ];
		then
			NAME=`basename $PWD`
		fi
		compress "$NAME" "$DIREC" "$1"
	else
		errordirectory "$1";
		exit 1;
	fi
else
	# Case for two arguments
	# COMMAND directory directory_source
	if [ -d "$1" ] # is it a directory?
	then
		if [ -d "$2" ]
		then
			# Extract filename from $1 
			NAME=`basename "$2"`
			DIREC=`dirname "$1"`
			DIREC2="$2"
			if [ "$1" = "." ];
			then
				DIREC="$PWD/";
			fi
			if [ "$2" = "." ];
			then
				DIREC2=$PWD
				NAME=`basename $PWD`
			fi
			compress "$NAME" "$DIREC" "$DIREC2";
		else
			errordirectory "$2";
			exit 1;
		fi

	else
		errordirectory "$1";
		exit 1;
	fi
fi
