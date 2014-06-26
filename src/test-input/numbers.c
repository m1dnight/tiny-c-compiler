       
int main()
{
char arr[4,4];
int i;
int j;
arr	[0,0]='b';
arr	[0,1]='e';
arr	[0,2]='n';
arr	[0,3]=' ';
arr	[1,0]='i';
arr	[1,1]='k';
arr	[1,2]=' ';
arr	[1,3]='g';
arr	[2,0]='e';
arr	[2,1]='s';
arr	[2,2]='l';
arr	[2,3]='a';
arr	[3,0]='a';
arr	[3,1]='g';
arr	[3,2]='d';
arr	[3,3]='?' ;


i = 0;
j = 0;
while(i < 4)
{
    j = 0;
    while(j < 4)
    {
     write arr[i,j];
     j = j + 1;
    };
    i = i + 1;
};
}