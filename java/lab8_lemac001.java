
class RunnyStack<Base> {

    private class Run {
        private Base object;
        private int length;
        private Run next;   // Points to the next run or null if the end of the stack.

        private Run(Base object, Run next) {
            this.object = object;
            this.length = 1;
            this.next = next;
        }
    }



    private Run top;
    private int totalRuns;
    private int totalDepth;



    public RunnyStack() {
        top = null;
        totalDepth = 0;
        totalRuns = 0;
    }



    // Return the depths of the stack (sum of the lengths of all the runs that it holds).
    public int depth() {
        return totalDepth;
    }



    // Tests if the stack holds any objects.
    public boolean isEmpty(){
        return top == null;
    }



    // Throws IllegalStateException if stack is empty, otherwise returns the object
    // at the top of the stack without removing it.
    public Base peek(){
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");

        return top.object;
    }



    // Throws IllegalStateException if stack is empty, otherwise decrement the length
    // of the run on top of the stack. If this makes the length of the run zero, remove
    // that run.
    public void pop(){
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");

        --top.length;
        --totalDepth;

        if (top.length <= 0) {
            top = top.next;
            --totalRuns;
        }
    }



    // Add object to the stack, if matches the object of the current run, increment the
    // length of that run, otherwise add a new run on top of the stack.
    public void push(Base object){
        if (isEmpty()) {
            top = new Run(object, null);
            ++totalRuns;
        } else if (testEquivalent(object, top.object)) {
            ++top.length;
        } else {
            top = new Run(object, top);
            ++totalRuns;
        }

        ++totalDepth;
    }



    // Return the number of runs in the stack.
    public int runs() {
        return totalRuns;
    }



    // Tests if the given base objects are equivalent.
    private boolean testEquivalent(Base b1, Base b2) {
        if (b1 == null || b2 == null)
            return b1 == null && b2 == null;
        else
            return b1.equals(b2);
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