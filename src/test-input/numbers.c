int positivize(int x)
{
    if(x > 0)
        return x;
    return 0;
}

int main(int mainParam)
{
    int x;
    x = 5;
    mainParam = positivize(x);
    return mainParam;

}
