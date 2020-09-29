//  ARRAY QUEUE. A fixed length queue implemented as a circular array.
class ArrayQueue<Base> {
    private int     front;     //  Index of front object in OBJECTS.
    private int     rear;      //  Index of rear object in OBJECTS.
    private Base [] objects;   //  The objects in the queue.


    //  Constructor. Make a new empty queue that can hold SIZE - 1 elements.
    public ArrayQueue(int size) {
        if (size >= 1) {
            front = 0;
            rear = 0;
            objects = (Base []) new Object[size];
        } else {
            throw new IllegalArgumentException("Size must be at least one.");
        }
    }


    //  IS EMPTY. Test if the queue is empty.
    public boolean isEmpty() {
        return front == rear;
    }


    //  IS FULL. Test if the queue can hold no more elements.
    public boolean isFull() {
        return front == (rear + 1) % objects.length;
    }


    //  ENQUEUE. Add OBJECT to the rear of the queue.
    public void enqueue(Base object) {
        int nextRear = (rear + 1) % objects.length;
        if (front == nextRear) {
            throw new IllegalStateException("Queue is full.");
        } else {
            rear = nextRear;
            objects[rear] = object;
        }
    }

    //  DEQUEUE. Remove an object from the front of the queue and return it.
    public Base dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        } else {
            front = (front + 1) % objects.length;
            Base temp = objects[front];
            objects[front] = null;
            return temp;
        }
    }




    public class Iterator {
        private int next;   // Points to the index of the element to be returned next.

        private Iterator(int startIndex) {
            this.next = startIndex;
        }

        public boolean hasNext() {
            return next != (rear + 1) % objects.length;
        }

        public Base next() {
            if (!hasNext())
                throw new IllegalStateException("No more elements to visit.");

            Base obj = objects[next];
            next = (next + 1) % objects.length;
            return obj;
        }
    }


    public Iterator iterator() {
        return new Iterator((front + 1) % objects.length);
    }
}





//  QUETERATOR. Test ARRAY QUEUE's ITERATOR class. It's worth 20 points.

class Queterator
{

//  MAIN. Start execution here.

    public static void main(String [] args)
    {

//  Make an ARRAY QUEUE and enqueue some STRINGs.

        ArrayQueue<String> queue = new ArrayQueue<String>(4);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

//  Make a FIRST ITERATOR for QUEUE and use it to visit QUEUE's elements.

        ArrayQueue<String>.Iterator first = queue.iterator();
        while (first.hasNext())
        {
            System.out.println(first.next());    //  A B C one per line    5 points
        }

//  The iterator hasn't changed QUEUE.

        System.out.println(queue.isEmpty());   //  false                 1 point
        System.out.println(queue.dequeue());   //  A                     1 point
        System.out.println(queue.dequeue());   //  B                     1 point
        System.out.println(queue.dequeue());   //  C                     1 point
        System.out.println(queue.isEmpty());   //  true                  1 point

//  Let's enqueue more things to QUEUE.

        queue.enqueue("X");
        queue.enqueue("Y");
        queue.enqueue("Z");

//  Now make a SECOND ITERATOR for QUEUE. The FIRST one does not work any more,
//  because QUEUE has changed. Use SECOND to visit QUEUE's new elements.

        ArrayQueue<String>.Iterator second = queue.iterator();
        while (second.hasNext())
        {
            System.out.println(second.next());   //  X Y Z one per line    5 points
        }

//  The new iterator hasn't changed QUEUE either.

        System.out.println(queue.isEmpty());   //  false                 1 point
        System.out.println(queue.dequeue());   //  X                     1 point
        System.out.println(queue.dequeue());   //  Y                     1 point
        System.out.println(queue.dequeue());   //  Z                     1 point
        System.out.println(queue.isEmpty());   //  true                  1 point
    }
}