/*
Alex Lema
1913
Lab8
 */
class RunnyStack<Base>
{
    private class Run
    {
        private Base object;
        private Run next;
        private int length;

        private Run(Base object, Run next, int length)
        {
            this.object = object;
            this.next = next;
            this.length = length;
        }
    }

    private Run head;
    private int length;
    private int runs;

    public RunnyStack()
    {
        head = null;
        length = 0;
        runs = 0;
    }

    public int depth()
    {
        return length;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public Base peek()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        else
        {
            return head.object;
        }
    }

    public void pop()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        else
        {
            if (head.length > 1)
            {
                head.length--;
            }
            else
            {
                head = head.next;
                runs--;
            }
            length--;
        }
    }

    public void push(Base object)
    {
        if (head != null && isEqual(object, head.object))
        {
            head.length++;
        }
        else
        {
            head = new Run(object, head, 1);
            runs++;
        }
        length++;
    }

    public int runs()
    {
        return runs;
    }

    private boolean isEqual(Base left, Base right)
    {
        if (left == null)
        {
            return left == right;
        }
        else
        {
            return left.equals(right);
        }
    }
}

//
//  Tests for CSci 1913 Lab 8
//  James Moen
//  20 Mar 17
//
//  The TRY-CATCH statements catch exceptions thrown by RUNNY STACK's methods,
//  so that the program can continue to run even if a method fails. We still
//  haven't talked about TRY-CATCH'es in the lectures yet.
//
//  Most tests have comments that show what they should print, and how many
//  points they are worth, for a total of 40 points.
//
//  Camembert is a soft French cheese. It may be runny. It can be stacked.
//

class Camembert
{
    public static void main(String [] args)
    {
        RunnyStack<String> s = new RunnyStack<String>();

        System.out.println(s.isEmpty());         //  true       1 point
        System.out.println(s.depth());           //  0          1 point
        System.out.println(s.runs());            //  0          1 point

        try
        {
            s.pop();
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No pop");          //  No pop     1 point
        }

        try
        {
            System.out.println(s.peek());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No peek");         //  No peek    1 point
        }

        s.push("A");
        System.out.println(s.peek());            //  A          1 point
        System.out.println(s.depth());           //  1          1 point
        System.out.println(s.runs());            //  1          1 point

        System.out.println(s.isEmpty());         //  false      1 point

        s.push("B");
        System.out.println(s.peek());            //  B          1 point
        System.out.println(s.depth());           //  2          1 point
        System.out.println(s.runs());            //  2          1 point

        s.push("B");
        System.out.println(s.peek());            //  B          1 point
        System.out.println(s.depth());           //  3          1 point
        System.out.println(s.runs());            //  2          1 point

        s.push("B");
        System.out.println(s.peek());            //  B          1 point
        System.out.println(s.depth());           //  4          1 point
        System.out.println(s.runs());            //  2          1 point

        s.push("C");
        System.out.println(s.peek());            //  C          1 point
        System.out.println(s.depth());           //  5          1 point
        System.out.println(s.runs());            //  3          1 point

        s.push("C");
        System.out.println(s.peek());            //  C          1 point
        System.out.println(s.depth());           //  6          1 point
        System.out.println(s.runs());            //  3          1 point

        s.pop();
        System.out.println(s.peek());            //  C          1 point
        System.out.println(s.depth());           //  5          1 point
        System.out.println(s.runs());            //  3          1 point

        s.pop();
        System.out.println(s.peek());            //  B          1 point
        System.out.println(s.depth());           //  4          1 point
        System.out.println(s.runs());            //  2          1 point

        s.pop();
        System.out.println(s.peek());            //  B          1 point
        System.out.println(s.depth());           //  3          1 point
        System.out.println(s.runs());            //  2          1 point

        s.pop();
        s.pop();
        System.out.println(s.peek());            //  A          1 point
        System.out.println(s.depth());           //  1          1 point
        System.out.println(s.runs());            //  1          1 point

        s.pop();
        System.out.println(s.isEmpty());         //  true       1 point
        System.out.println(s.depth());           //  0          1 point
        System.out.println(s.runs());            //  0          1 point

        try
        {
            System.out.println(s.peek());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No peek");         //  No peek    1 point
        }
    }
}


/*
OUTPUT
true
0
0
No pop
No peek
A
1
1
false
B
2
2
B
3
2
B
4
2
C
5
3
C
6
3
C
5
3
B
4
2
B
3
2
A
1
1
true
0
0
No peek

Process finished with exit code 0

 */