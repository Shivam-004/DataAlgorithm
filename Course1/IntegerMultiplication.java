import java.math.BigInteger;

public class IntegerMultiplication 
{

    public static void main(String[] args) 
    {
        int n=64;
        BigInteger x= new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y= new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        BigInteger z= multiple(x,y);
        System.out.println(z);
    }
    public static BigInteger multiple(BigInteger x,BigInteger y)
    {
        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 2000) return x.multiply(y);
        n = n / 2 + n % 2;
        BigInteger a = x.shiftRight(n);
        BigInteger b = x.subtract(a.shiftLeft(n));
        BigInteger c = y.shiftRight(n);
        BigInteger d = y.subtract(c.shiftLeft(n));
        BigInteger ac = multiple(a,c);
        BigInteger bd = multiple(b,d);
        BigInteger abcd = multiple(a.add(b), b.add(c));
        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(n)).shiftLeft(n).add(bd);
    }
}