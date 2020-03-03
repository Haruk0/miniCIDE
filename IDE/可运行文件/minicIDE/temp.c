void delay(void)
{
    int c;c=500;
    $0xfffffc50=1;
    while(c>0){ c=c-1;}
}
void main(void)
{
    int var;
    int low;
    int high;
    var = 0;
    $0xfffffc04 = 65280;
    $0xfffffc02 = 2020;
    while(1) 
    {   
        if(var == 65535) var = 0;
        var = var + 1;
	$0xfffffc50=1;//aaaa
        $0xfffffc00 = var;
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
        low = $0xfffffc70;
        high = $0xfffffc72;
        $0xfffffc60=low;
        $0xfffffc62=high;
        delay();
    }
}