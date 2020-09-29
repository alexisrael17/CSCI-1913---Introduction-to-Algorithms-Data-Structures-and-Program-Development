/**
 * Alex Lema
 * Csci 1913
 */

class AssociationList <Key, Value>
{
    private class Node
    {
        private Key key;
        private Value value;
        private Node next;

        private Node(Key key, Value value)
        {
            this.key = key;
            this.value = value;
        }
    }
    private Node head;
    private Node first;
    private Node temp;
    /////////////////////////////////////////////////////
    public AssociationList()
    {
        head=null;
        first = null;
        temp = null;
    }
    ////////////////////////////
    public void delete(Key key) {
        if (first != null) {
            if (isEqual(key, first.key))
            {
                first = null;
            }
            else {
                for (head = first; head.next != null; head = head.next) {
                    if (isEqual(key, head.next.key))
                    {
                        if (head.next.next != null)
                        {
                            head.next = head.next.next;
                        }
                        else
                        {
                            head.next = null;
                        }
                        return;
                    }
                }
            }
        }
    }
    ////////////////////////////////////

    public Value get (Key key)
    {
        temp = first;
        while (temp != null)
        {
            if (isEqual(key,temp.key))
            {
                return temp.value;
            }
            else
            {
                temp = temp.next;
            }
        }
        throw new IllegalArgumentException("not in list");

    }
    ////////////////////////////////////////
    private boolean isEqual (Key leftKey, Key rightKey)
    {
        if (leftKey == null || rightKey == null)
        {
            if (leftKey == rightKey)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return leftKey == rightKey;
        }
    }
    //////////////////////////////////////////////


    public boolean isIn(Key key)
    {
        temp = first;
        while (temp != null)
        {
            if (isEqual(key, temp.key))
            {
                return true;
            }
            else
            {
                temp = temp.next;
            }
        }
        return false;
    }
    /////////////////////////////////////////////


    public void put (Key key, Value value)
    {
        if (first == null)
        {
            first = new Node(key, value);
        }
        else if (isIn(key))
        {
            temp.value = value;
        }
        else
        {
            temp = first;
            first = new Node(key, value);
            first.next = temp;
        }
    }
    /////////////////////////////////////////////////
}
class Hogwarts
{

//  MAIN. Make an instance of ASSOCIATION LIST and test it.

    public static void main(String[] args)
    {
        AssociationList<String,String> list = new AssociationList<String,String>();

        System.out.println(list.isIn(null));         //  false         2 points.

        try
        {
            System.out.println(list.get(null));
        }
        catch (IllegalArgumentException ignore)
        {
            System.out.println("No null");             //  No null       2 points.
        }

        list.put(null,        "Wormtail");
        list.put("Ron",       "Lavender");
        list.put("Voldemort", null);
        list.put("Dean",      "Ginny");

        System.out.println(list.isIn("Dean"));       //  true          2 points.
        System.out.println(list.isIn("Ginny"));      //  false         2 points.
        System.out.println(list.isIn("Ron"));        //  true          2 points.
        System.out.println(list.isIn("Voldemort"));  //  true          2 points.
        System.out.println(list.isIn(null));         //  true          2 points.
        System.out.println(list.isIn("Joanne"));     //  false         2 points.

        System.out.println(list.get("Ron"));         //  Lavender      2 points.
        System.out.println(list.get("Dean"));        //  Ginny         2 points.
        System.out.println(list.get("Voldemort"));   //  null          2 points.
        System.out.println(list.get(null));          //  Wormtail      2 points.

        try
        {
            System.out.println(list.get("Joanne"));
        }
        catch (IllegalArgumentException ignore)
        {
            System.out.println("No Joanne");           //  No Joanne     2 points.
        }

        list.delete(null);

        System.out.println(list.isIn(null));         //  false         2 points.

        list.put(null,    null);
        list.put("Harry", "Ginny");
        list.put("Ron",   "Hermione");

        System.out.println(list.isIn(null));         //  true          2 points.
        System.out.println(list.get(null));          //  null          2 points.
        System.out.println(list.get("Harry"));       //  Ginny         2 points.
        System.out.println(list.get("Dean"));        //  Ginny         2 points.
        System.out.println(list.get("Ron"));         //  Hermione      2 points.

        list.delete("Dean");

        try
        {
            System.out.println(list.get("Dean"));
        }
        catch (IllegalArgumentException ignore)
        {
            System.out.println("No Dean");             //  No Dean       2 points.
        }
    }
}



/*
***********OUTPUT*****************
false
No null
true
false
true
true
true
false
Lavender
Ginny
null
Wormtail
No Joanne
false
true
null
Ginny
Ginny
Hermione
No Dean

Process finished with exit code 0
 */