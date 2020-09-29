/*
Alex Lema
Csci 1913

 */
class Deque <Base>
{
    private Node head;
    private class Node
    {
        private Base object;
        private Node left;
        private Node right;
        private Node(Base object, Node prev, Node next)
        {
            this.object = object;
            this.left = prev;
            this.right = next;
        }
    }


    public Deque()
    {
        head = new Node(null, null, null);//Make a new, empty Deque.
        head.right = head;//The variable head must be set so it points to a new head node here.
        head.left = head;
    }

    public void enqueueFront(Base object)
    {
        if (isEmpty())
        {
            Node temp = new Node(object, head, head);
            head.left = temp;
            head.right = temp;

        }
        else
        {
            Node temp = new Node(object, head, head.right);
            head.right.left = temp;
            head.right = temp;
        }
    }

    public void enqueueRear(Base object)
    {
        if (isEmpty())
        {
            Node temp = new Node(object, head, head);
            head.left = temp;
            head.right = temp;
            //count++;
        }
        else
        {
            Node temp = new Node(object, head.left, head);
            head.left.right = temp;
            head.left = temp;
        }
    }

    public Base dequeueFront()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Deque is empty");
        }
        else
        {
            Base temp = head.right.object;
            head.right.right.left = head;
            head.right = head.right.right;
            return temp;
        }
    }

    public Base dequeueRear()
    {
        if(isEmpty())
        {
            throw new IllegalStateException("Deque is empty");
        }
        else
        {
            Base temp = head.left.object;
            head.left.left.right = head;
            head.left = head.left.left;
            return temp;
        }
    }

    public boolean isEmpty()
    {
        return head.right == head && head.left == head;
    }

}


//  OBSERVATION DEQUE. Test the class DEQUE. 40 points total.

class ObservationDeque
{

//  MAIN. Test the DEQUE on various example arguments.

    public static void main(String [] args)
    {
        Deque<String> deque = new Deque<String>();

        System.out.println(deque.isEmpty());       // true                2 points.

        try
        {
            System.out.println(deque.dequeueFront());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No dequeueFront.");  //  No dequeueFront.   2 points.
        }

        try
        {
            System.out.println(deque.dequeueRear());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("No dequeueRear.");   //  No dequeueRear.    2 points.
        }

//  Enqueueing to the rear and dequeueing from the rear makes the DEQUE act
//  like a stack.

        deque.enqueueRear("A");
        deque.enqueueRear("B");
        deque.enqueueRear("C");

        System.out.println(deque.isEmpty());       //  false              2 points.

        System.out.println(deque.dequeueRear());   //  C                  2 points.
        System.out.println(deque.dequeueRear());   //  B                  2 points.
        System.out.println(deque.dequeueRear());   //  A                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the rear and dequeueing from the front makes the DEQUE act
//  like a queue.

        deque.enqueueRear("A");
        deque.enqueueRear("B");
        deque.enqueueRear("C");

        System.out.println(deque.dequeueFront());  //  A                  2 points.
        System.out.println(deque.dequeueFront());  //  B                  2 points.
        System.out.println(deque.dequeueFront());  //  C                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the front makes the DEQUE act
//  like a stack.

        deque.enqueueFront("A");
        deque.enqueueFront("B");
        deque.enqueueFront("C");

        System.out.println(deque.dequeueFront());  //  C                  2 points.
        System.out.println(deque.dequeueFront());  //  B                  2 points.
        System.out.println(deque.dequeueFront());  //  A                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the rear makes the DEQUE act
//  like a queue.

        deque.enqueueFront("A");
        deque.enqueueFront("B");
        deque.enqueueFront("C");

        System.out.println(deque.dequeueRear());   //  A                  2 points.
        System.out.println(deque.dequeueRear());   //  B                  2 points.
        System.out.println(deque.dequeueRear());   //  C                  2 points.

        System.out.println(deque.isEmpty());       //  true               2 points.
    }
}


/*
***************OUTPUT***************8
true
No dequeueFront.
No dequeueRear.
false
C
B
A
true
A
B
C
true
C
B
A
true
A
B
C
true

Process finished with exit code 0
 */