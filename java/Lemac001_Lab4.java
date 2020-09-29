/*Autor: Alex Lema
  CSCI 1913
  10/11/2017
 */
class Lemac001_Lab4
{
    public static void main(String[] args)
    {
        Zillion z = new Zillion(2);
        System.out.println(z);  //  00  2 points

        z.increment();
        System.out.println(z);  //  01  2 points

        z.increment();
        System.out.println(z);  //  02  2 points

        z.increment();
        z.increment();
        z.increment();
        z.increment();
        z.increment();
        z.increment();
        z.increment();
        z.increment();

        System.out.println(z);  //  10  2 points
        z.increment();
        System.out.println(z);  //  11  2 points

        z = new Zillion(4);
        System.out.println(z);  //  0000  2 points

        for (int j = 1; j <= 999; j += 1)
        {
            z.increment();
        }
        System.out.println(z);  //  0999  2 points

        z.increment();
        System.out.println(z);  //  1000  2 points

        for (int j = 1; j <= 999; j += 1)
        {
            z.increment();
        }
        System.out.println(z);  //  1999  2 points

        z.increment();
        System.out.println(z);  //  2000  2 points

        for (int j = 1; j <= 7999; j += 1)
        {
            z.increment();
        }
        System.out.println(z);  //  9999  2 points

        z.increment();
        System.out.println(z);  //  0000  2 points

        z.increment();
        System.out.println(z);  //  0001  1 point
    }
}

class Zillion
{
    private int[] digits;
    int i;

    public Zillion(int size)
    {
        digits = new int[size];
    }
    public void increment()
    {
        i = digits.length - 1;
        while (i >= 0) {
            if (digits[i] != 9)
            {
                digits[i] += 1;
                i = -1;
            }
            else
            {
                digits[i] = 0;
                i--;
            }
        }
    }

    public String toString()
    {
        String concatenate_digit;
        concatenate_digit= "";

        for(i = 0; i<digits.length; i++)
        {
            concatenate_digit = concatenate_digit + digits[i];
        }

        return concatenate_digit;
    }
}