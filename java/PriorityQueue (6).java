/*
Alex Lema
Csci 1913
Lab13
 */

class PriorityQueue<Base> {

    private class Node {
        private Base object;
        private int rank;
        private Node left;
        private Node right;

        private Node(Base object, int rank) {
            this.object = object;
            this.rank = rank;
            left = null;
            right = null;
        }
    }


    private Node root;  //  Root node of the BST.

    /////////////////////////////////////////////

    public PriorityQueue()
    {
        root = new Node(null, Integer.MAX_VALUE);
    }

    /////////////////////////////////////////
    public Base dequeue()
    {
        if (isEmpty())
            throw new IllegalStateException("Priority Queue is empty");

        Node parent = root;
        Node lowest = root.left;

        while (lowest.left != null) {
            parent = lowest;
            lowest = lowest.left;
        }

        parent.left = lowest.right;
        return lowest.object;
    }

    ///////////////////////////////////////////

    public void enqueue(Base object, int rank)
    {
        if (rank < 0)
            throw new IllegalArgumentException("Rank is negative");

        Node subtree = root;

        while (true) {
            if (rank <= subtree.rank) {
                if (subtree.left == null) {
                    subtree.left = new Node(object, rank);
                    return;
                } else
                    subtree = subtree.left;
            } else {
                if (subtree.right == null) {
                    subtree.right = new Node(object, rank);
                    return;
                } else
                    subtree = subtree.right;
            }
        }
    }

    /////////////////////////////////////////
    public boolean isEmpty() {
        return (root.left == null);
    }
    //////////////////////////////////////////
}

//  SNOBBERY. How the aristocracy behaves in a queue. 20 points.

class Snobbery
{

//  MAIN. Queue them up.

    public static void main(String[] args)
    {
        PriorityQueue<String> queue = new PriorityQueue<String>();

        System.out.println(queue.isEmpty());  //  true        2 points

        try
        {
            System.out.println(queue.dequeue());
        }
        catch (IllegalStateException ignore)
        {
            System.out.println("Blimey!");      //  Blimey!     2 points
        }

        queue.enqueue("Lancelot",  5);
        queue.enqueue("Fawlty",    7);
        queue.enqueue("Elizabeth", 0);
        queue.enqueue("Charles",   1);
        queue.enqueue("Turing",    7);

        try
        {
            queue.enqueue("Zeus", -100);
        }
        catch (IllegalArgumentException ignore)
        {
            System.out.println("No gods!");     //  No gods!    2 points
        }

        System.out.println(queue.isEmpty());  //  false       2 points

        System.out.println(queue.dequeue());  //  Elizabeth   2 points
        System.out.println(queue.dequeue());  //  Charles     2 points
        System.out.println(queue.dequeue());  //  Lancelot    2 points
        System.out.println(queue.dequeue());  //  Turing      2 points
        System.out.println(queue.dequeue());  //  Fawlty      2 points

//  It's OK if Fawlty comes out before Turing, but both must come out last.

        System.out.println(queue.isEmpty());  //  true        2 points.
    }

}

/*
   ***********OUTPUT***************

true
Blimey!
No gods!
false
Elizabeth
Charles
Lancelot
Turing
Fawlty
true

 */