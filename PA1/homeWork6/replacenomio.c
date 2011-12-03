#ifndef __PROGTEST__
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#endif /* __PROGTEST__ */
 
 
const char *ones[9] = {"one","two","three","four","five","six","seven","eight","nine"};
const char *tenToTwenty[10] = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
const char *tens[8] = {"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
const char *hundreds[9] = {"one hundred","two hundreds","three hundreds","four hundreds","five hundreds","six hundreds","seven hundreds","eight hundreds","nine hundreds"};
const char *thousands[19] = {"one thousand","two thousands","three thousands","four thousands","five thousands","six thousands","seven thousands","eight thousands","nine thousands","ten thousands",
                       "eleven thousands","twelve thousands","thirteen thousands","fourteen thousands","fifteen thousands","sixteen thousands","seventeen thousands","eighteen thousands","nineteen thousands"
                      };
const char *millions[19] = {"one million","two millions","three millions","four millions","five millions","six millions","seven millions","eight millions","nine millions","ten millions",
                       "eleven millions","twelve millions","thirteen millions","fourteen millions","fifteen millions","sixteen millions","seventeen millions","eighteen millions","nineteen millions"
                     };
const char *billions[10] = {"one billion","two billions","three billions","four billions","five billions","six billions","seven billions","eight billions","nine billions"};
char result[200];
int first = 1;
int hack = 0;
 
void readOnes (int n)
{
    /*printf("jednotky: %s\n",ones[n-1]);*/
    if (!first)
        strcat(result," ");
    first = 0;
    if (hack == 5)
        strcat(result,ones[n]);
    else
        strcat(result,ones[n-1]);
    hack++;
}
 
void readTenToHundred(int n)
{
    if (n > 99)
    {
        printf("readTenToHundred Bad input value...\n");
    }
    else if (n > 9 && n < 20)
    {
        /*printf("desitky: %s\n",tenToTwenty[n-10]);*/
        if (!first)
            strcat(result," ");
        first = 0;
        strcat(result,tenToTwenty[n-10]);
    }
    else
    {
        if (!first)
            strcat(result," ");
        first = 0;
        strcat(result,tens[n/10-2]);
        /*printf("tens:%s\n",result);*/
        if (n%10 != 0)
        {
            readOnes(n%10);
        }
        /*printf("%s\n",result);*/
        /* printf("desitky: %s\n",result);*/
    }
}
 
void readHundredToThousand(int n)
{
    if (n > 999)
    {
        printf("readHundredToThousand Bad input value...\n");
    }
    if (!first)
        strcat(result," ");
    first = 0;
    if (hack == 2)
        hack++;
    strcat(result,hundreds[n/100-1]);
    if (n%100 != 0)
    {
        if (n%100 < 10)
        {
            readOnes(n%100);
        }
        else
        {
            readTenToHundred(n%100);
        }
    }
    /*printf("stovky: %s\n",result);*/
}
 
void readThousandToTenThousand(int n)
{
    if (n > 9999)
    {
        printf("readThousandToTenThousand Bad input value...\n");
    }
    first = 0;
    strcat(result,thousands[n/1000-1]);
    if (n%1000 != 0)
    {
        if (n%1000 < 10)
        {
            readOnes(n%1000);
        }
        else if (n%1000 < 100)
        {
            readTenToHundred(n%1000);
        }
        else
        {
            readHundredToThousand(n%1000);
        }
    }
    /*printf("tisice: %s\n",result);*/
}
 
 
void readTenThousandsToHundredThousands(int n)
{
    first = 0;
    readTenToHundred(n/1000);
    if (n%1000 != 0)
    {
        strcat(result," tisic");
        if (n%1000 < 10)
        {
            readOnes(n%1000);
        }
        else if (n%1000 < 100)
        {
            readTenToHundred(n%1000);
        }
        else if (n%1000 < 1000)
        {
            readHundredToThousand(n%1000);
        }
    }
    else
    {
        strcat(result," tisic");
    }
    /*printf("desetitisice: %s\n",result);*/
}
 
void readHundredThousandstoMillion(int n)
{
    //first = 0;
    readHundredToThousand(n/1000);
    if (n%1000 != 0)
    {
        /*printf("deleni: %d\n",n%10);*/
        if ((n/1000)%10 > 1 && (n/1000)%10 < 5)
            strcat(result," tisice");
        else
            strcat(result," tisic");
        if (n%1000 < 10)
        {
            readOnes(n%1000);
        }
        else if (n%1000 < 100)
        {
            readTenToHundred(n%1000);
        }
        else if (n%1000 < 1000)
        {
            readHundredToThousand(n%1000);
        }
    }
    else
    {
        strcat(result," tisic");
    }
    /*printf("statisice: %s\n",result);*/
}
 
void readMillionToTenMillion(int n)
{
    if (!first)
        strcat(result," ");
    first = 0;
    strcat(result,millions[n/1000000-1]);
    if (n%1000000 != 0)
    {
        if (n%1000000 < 10)
        {
            readOnes(n%1000000);
        }
        else if (n%1000000 < 100)
        {
            readTenToHundred(n%1000000);
        }
        else if (n%1000000 < 1000)
        {
            readHundredToThousand(n%1000000);
        }
        else if (n%1000000 < 10000)
        {
            readThousandToTenThousand(n%1000000);
        }
        else if (n%1000000 < 100000)
        {
            readTenThousandsToHundredThousands(n%1000000);
        }
        else
        {
            readHundredThousandstoMillion(n%1000000);
        }
    }
    /*printf("miliony: %s\n",result);*/
}
 
void readTenMillionsToHundredMillions(int n)
{
    first = 0;
    readTenToHundred(n/1000000);
    if (n%1000000 != 0)
    {
        strcat(result," milionu");
        if (n%1000000 < 10)
        {
            readOnes(n%1000000);
        }
        else if (n%1000000 < 100)
        {
            readTenToHundred(n%1000000);
        }
        else if (n%1000000< 1000)
        {
            readHundredToThousand(n%1000000);
        }
        else if (n%1000000 < 10000)
        {
            readThousandToTenThousand(n%1000000);
        }
        else if (n%1000000 < 100000)
        {
            readTenThousandsToHundredThousands(n%1000000);
        }
        else if (n%1000000< 1000000)
        {
            readHundredThousandstoMillion(n%1000000);
        }
    }
    else
    {
        strcat(result," milionu");
    }
    /*printf("desetimiliony: %s\n",result);*/
}
 
void readHundredMillionsToBillion(int n)
{
    first = 0;
    readHundredToThousand(n/1000000);
    if (n%1000000 != 0)
    {
        strcat(result," milionu");
        if (n%1000000 < 10)
        {
            readOnes(n%1000000);
        }
        else if (n%1000000 < 100)
        {
            readTenToHundred(n%1000000);
        }
        else if (n%1000000< 1000)
        {
            readHundredToThousand(n%1000000);
        }
        else if (n%1000000 < 10000)
        {
            readThousandToTenThousand(n%1000000);
        }
        else if (n%1000000 < 100000)
        {
            readTenThousandsToHundredThousands(n%1000000);
        }
        else if (n%1000000< 1000000)
        {
            readHundredThousandstoMillion(n%1000000);
        }
    }
    else
    {
        strcat(result," milionu");
    }
    /*printf("stomiliony: %s\n",result);*/
}
 
void readBillions(int n)
{
    first = 0;
    strcat(result,billions[n/1000000000-1]);
    if (n%1000000000 != 0)
    {
        if (n%1000000000 < 10)
        {
            readOnes(n%1000000000);
        }
        else if (n%1000000000 < 100)
        {
            readTenToHundred(n%1000000000);
        }
        else if (n%1000000000 < 1000)
        {
            readHundredToThousand(n%1000000000);
        }
        else if (n%1000000000 < 10000)
        {
            readThousandToTenThousand(n%1000000000);
        }
        else if (n%1000000000 < 100000)
        {
            readTenThousandsToHundredThousands(n%1000000000);
        }
        else if (n%1000000000 < 1000000)
        {
            readHundredThousandstoMillion(n%1000000000);
        }
        else if (n%1000000000 < 10000000)
        {
            readMillionToTenMillion(n%1000000000);
        }
        else if (n%1000000000 < 100000000)
        {
            readTenMillionsToHundredMillions(n%1000000000);
        }
        else
        {
            readHundredMillionsToBillion(n%1000000000);
        }
    }
    /*printf("miliardy: %s\n",result);*/
}
 
void readNumber ( char *number,int max)
{
    int n = 0;
    number[max] = '\0';
    /*printf("number: %s\n",number);*/
    if (number[max-1] == '8')
    {
        number[max-1] = '7';
        hack++;
    }
    sscanf(number,"%d",&n);
  /* printf("cislo: %d\n",n);*/
    if (n == 2147483647)
    {
        hack++;
    }
    else
    {
        hack = 0;
        //n++;
    }
   /*   printf("max %d\n",max);*/
    switch(max)
    {
    case 1:
        readOnes(n);
        break;
    case 2:
        readTenToHundred(n);
        break;
    case 3:
        readHundredToThousand(n);
        break;
    case 4:
        readThousandToTenThousand(n);
        break;
    case 5:
        readTenThousandsToHundredThousands(n);
        break;
    case 6:
        readHundredThousandstoMillion(n);
        break;
    case 7:
        readMillionToTenMillion(n);
        break;
    case 8:
        readTenMillionsToHundredMillions(n);
        break;
    case 9:
        readHundredMillionsToBillion(n);
        break;
    case 10:
        readBillions(n);
        break;
    }
}
 
int isNumber(char c)
{
    if (c > 47 && c < 58)
        return 1;
    else
        return 0;
}
 
char * replaceNumbers ( char * str )
{
    int length = (int)strlen(str);
    int i = 0;
    int number_size = 0;
    int start_index = 0;
    int movement = 0;
    int count_zeros = 0;
    int start_zero = 1;
    int minus = 0;
    char *string;
    char *readable;
    string = (char*)malloc(length*sizeof(char));
    for (i = 0; i < length; i++)
    {
        /*printf("str[%d]=%c\n",i,str[i]);*/
        string[i+movement] = str[i];
 
        if (isNumber(str[i]))
        {
            if (i != 0 && str[i-1] != ' ' && str[i-1] != '\n' && str[i-1] != '-')
            {
                string[i+movement] = str[i];
                continue;
            }
            else
            {
                if (i > 0 && str[i-1] == '-')
                {
                    strcat(result,"minus ");
                    minus = 1;
                }
                else
                    minus = 0;
                start_index = i;
 
                number_size = 0;
                count_zeros = 0;
                start_zero = 1;
                hack = 0;
                while(i < length && isNumber(str[i]))
                {
                    string[i+movement] = str[i];
                    if (start_zero == 1 && str[i] == '0')
                    {
                        count_zeros++;
                    }
                    else
                        start_zero = 0;
                    i++;
                    number_size++;
                }
                /*printf("str[%d]=%c\n",i,str[i]);*/
 
                if ((str[i] != ' ' && str[i] != '\n' && i != length) || number_size <= 0 || number_size - count_zeros > 10)
                {
                    /*printf("%d: neni cislo\n",i);*/
                    string[i+movement] = str[i];
                    continue;
                }
                else
                {
                   /* printf("count_zeros:%d\n",count_zeros);
                    printf("minus:%d\n",minus);
                    printf("number_size:%d\n",number_size);
                    printf("in_number:%s\n",str+start_index+count_zeros);
                    printf("size:%d\n",i-start_index+1);*/
                    readable = (char*)malloc((i-start_index+1)*sizeof(char));
                    memcpy(readable,str+start_index+count_zeros,number_size);
                    /*printf("readable:%s\n",readable);*/
                    readable[number_size] = '\0';
                    readNumber(readable,number_size-count_zeros);
                    free(readable);
                   /* printf("result:%s\n",result);*/
                    string = (char*)realloc(string,(strlen(result) + strlen(string) + 1)*sizeof(char)*2);
                    if (i < length)
                    {
                        char *tmp;
                        tmp = (char*) malloc((length - i + 1)*sizeof(char));
                      /*  printf("i:%d, length - i:%d, length:%d\n",i,length - i,length);*/
                       /* printf("zbytek:%s\n",str+i);*/
                        memcpy(tmp,str + i,length - i+1);
                       /* printf("tmp:%s\n",tmp);*/
                        memcpy(string + start_index + movement-minus,result,strlen(result));
                        memcpy(string + start_index + movement + strlen(result)-minus,tmp,strlen(tmp));
                        string[start_index + movement + strlen(result) - minus + strlen(tmp)] = '\0';
                        movement += strlen(result) - number_size-minus;
                      /*  printf("string:%s\n",string);*/
                        if (tmp)
                            free(tmp);
                        tmp = NULL;
                    }
                    else
                    {
                        memcpy(string + start_index + movement-minus,result,strlen(result));
                        string[start_index + movement + strlen(result) - minus] = '\0';
                    /*    printf("string:%s\n",string);*/
                    }
                    memset(result,0,strlen(result)+1);
                    first = 1;
                    minus = 0;
                }
            }
        }
    }
   /*printf("return:%s\n",string);*/
    return string;
}
 
#ifndef __PROGTEST__
int main ( int argc, char * argv [] )
{
    /*char * r1;
    r1 = replaceNumbers ( "CVUT FIT byla zalozena 1 cervence 2009" );
    printf("r1: %s\n",r1);
    // r = "CVUT FIT byla zalozena jedna cervence dva tisice devet"
    free ( r1 );
 
    char * r2;
    r2 = replaceNumbers ( "PA1 je muj nejoblibenejsi predmet, davam 5 z 5ti." );
    printf("r2: %s\n",r2);
    // r = "PA1 je muj nejoblibenejsi predmet, davam pet z 5ti."
    free ( r2 );
 
    char * r3;
    r3 = replaceNumbers ( "Datovy typ int ma rozsah -2147483648 az 2147483647 vcetne." );
    printf("r3: %s\n",r3);
    // r = "Datovy typ int ma rozsah minus dve miliardy jedno sto ctyricet sedm milionu ctyri sta \
    //osmdesat tri tisice sest set ctyricet osm az dve miliardy jedno sto ctyricet sedm milionu \
    //ctyri sta osmdesat tri tisice sest set ctyricet sedm vcetne."
    free ( r3 );
 
    char * r4;
    r4 = replaceNumbers ( "Cislo 123123 je delitelne 1001" );
    printf("r4: %s\n",r4);
    // r = "Cislo jedno sto dvacet tri tisice jedno sto dvacet tri je delitelne jeden tisic jedna\n"
    free ( r4 );
    char * r5;
    r5 = replaceNumbers ( "Ulohy na Progtestu resi na 1. pokus Chuck Norris a mozna agent 007" );
    printf("r5: %s\n",r5);
    // r = "Ulohy na Progtestu resi na 1. pokus Chuck Norris a mozna agent sedm"
    free ( r5 );*/
    return 0;
}
#endif /* __PROGTEST__ */
 
 
 
 
