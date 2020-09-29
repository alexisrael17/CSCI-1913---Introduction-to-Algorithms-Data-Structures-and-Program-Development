/*
ALEX LEMA
CSCI 1913
LAB 12
 */

class FamilyTree {

    private class Node
    {
        private String name;
        private Node father;
        private Node mother;

        private Node(String name, Node mother, Node father) {
            this.name = name;
            this.father = father;
            this.mother = mother;

        }
    }

    private Node root;
    //////////////////////////////
    public FamilyTree(String ego)
    {
        root = new Node(ego, null, null);
    }
    ////////////////////////////////
    private Node find(String name)
    {
        return find(name, root);
    }
    ////////////////////////////////////
    private Node find(String name, Node root)
    {
        if (root.name.equals(name))
        {
            return root;
        }
        if (root.mother != null)
        {
            Node result = find(name, root.mother);
            if (result != null)
                return result;
        }
        if (root.father != null)
        {
            Node result = find(name, root.father);
            if (result != null)
                return result;
        }
        return null;
    }
    /////////////////////////////////////////////////
    public void addParents(String ego, String father, String mother)
    {
        Node target = find(ego);
        if (target == null)
            throw new IllegalArgumentException("There is no such Node: " + ego);
        target.father = new Node(father, null, null);
        target.mother = new Node(mother, null, null);
    }
    //////////////////////////////////////
    public boolean isDescendant(String ego, String ancestor)
    {
        return isDescendant(find(ego), find(ancestor));
    }
    ////////////////////////////////////////
    private boolean isDescendant(Node root, Node ancestor)
    {
        if (root == null || ancestor == null)
            return false;

        Node result = find(ancestor.name, root);
        return result != null;
    }
    /////////////////////////////////////////
}





//  POTTERY. Driver class, for testing. Each comment shows a point value (for a
//  total of 40 points) and what it should print.

class Pottery
{

//  MAIN. Harry Potter and the Hairier Pottery.

    public static void main(String [] args)
    {
        FamilyTree family = new FamilyTree("Al");

        family.addParents("Al",    "Harry",  "Ginny");
        family.addParents("Harry", "James",  "Lily" );
        family.addParents("Ginny", "Arthur", "Molly");

        try
        {
            family.addParents("Joanne", "Peter", "Anne");
        }
        catch (IllegalArgumentException ignore)
        {
            System.out.println("No Joanne.");  //  2 No Joanne.
        }

        System.out.println(family.isDescendant("Joanne", "Joanne"));  //  2 false

        System.out.println(family.isDescendant("Al", "Al"));          //  2 true
        System.out.println(family.isDescendant("Al", "Harry"));       //  2 true
        System.out.println(family.isDescendant("Al", "Ginny"));       //  2 true
        System.out.println(family.isDescendant("Al", "James"));       //  2 true
        System.out.println(family.isDescendant("Al", "Lily"));        //  2 true
        System.out.println(family.isDescendant("Al", "Arthur"));      //  2 true
        System.out.println(family.isDescendant("Al", "Molly"));       //  2 true
        System.out.println(family.isDescendant("Al", "Joanne"));      //  2 false

        System.out.println(family.isDescendant("Harry", "Harry"));    //  2 true
        System.out.println(family.isDescendant("Harry", "Al"));       //  2 false
        System.out.println(family.isDescendant("Harry", "James"));    //  2 true
        System.out.println(family.isDescendant("Harry", "Lily"));     //  2 true
        System.out.println(family.isDescendant("Harry", "Ginny"));    //  2 false
        System.out.println(family.isDescendant("Harry", "Arthur"));   //  2 false
        System.out.println(family.isDescendant("Harry", "Molly"));    //  2 false
        System.out.println(family.isDescendant("Harry", "Joanne"));   //  2 false

        System.out.println(family.isDescendant("Ginny", "Arthur"));   //  2 true
        System.out.println(family.isDescendant("Arthur", "Ginny"));   //  2 false
    }
}

/*
////////OUTPUT////////////

No Joanne.
false
true
true
true
true
true
true
true
false
true
false
true
true
false
false
false
false
true
false

 */