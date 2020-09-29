/*
ALEX LEMA -lemac001
CSCI 1913
PROJECT 3
*/
///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
///
//  NOMENCLATOR. Read names from a Java source file.
//
//    James Moen
//    04 Dec 17
//
//  The version of 01 Dec 17 could not handle /*.../ comments that extend over
//  multiple lines, but this one can.
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//  NOMENCLATOR. Read names from a Java source file.  It acts like an ITERATOR,
//  but it has two NEXT methods: one for names, and one for the line numbers of
//  those names.

class Nomenclator
{
    private char              ch;                 //  Current CHAR from READER.
    private static final char eof = (char) 0x00;  //  End of file sentinel.
    private static final char eol = (char) 0x0A;  //  End of line sentinel.
    private int               index;              //  Index into LINE.
    private String            line;               //  Current LINE from READER.
    private boolean           listing;            //  Are we listing the file?
    private String            name;               //  Current name.
    private int               number;             //  Current line number.
    private String            path;               //  Pathname to READER's file.
    private BufferedReader    reader;             //  Read CHARs from here.

//  Constructor. Initialize a new NOMENCLATOR that reads from a text file whose
//  pathname is PATH. If we can't open it then throw an exception. LISTING says
//  whether we should copy the file to standard output as we read it.

    public Nomenclator(String path, boolean listing)
    {
        try
        {
            index = 0;
            line = "";
            this.listing = listing;
            number = 0;
            this.path = path;
            reader = new BufferedReader(new FileReader(path));
            skipChar();
        }
        catch (IOException ignore)
        {
            throw new IllegalArgumentException("Can't open '" + path + "'.");
        }
    }

//  HAS NEXT. Test if there's another name waiting to be read. If so, then read
//  it, so NEXT NAME and NEXT NUMBER can return it and its line number later.

    public boolean hasNext()
    {
        while (true)
        {
            if (Character.isJavaIdentifierStart(ch))
            {
                skipName();
                return true;
            }
            else if (Character.isDigit(ch))
            {
                skipNumber();
            }
            else
            {
                switch (ch)
                {
                    case eof:
                    {
                        return false;
                    }
                    case '"':
                    case '\'':
                    {
                        skipDelimited();
                        break;
                    }
                    case '/':
                    {
                        skipComment();
                        break;
                    }
                    default:
                    {
                        skipChar();
                        break;
                    }
                }
            }
        }
    }

//  NEXT NAME. If HAS NEXT was true, then return the next name. If HAS NEXT was
//  false, then return an undefined string.

    public String nextName()
    {
        return name;
    }

//  NEXT NUMBER. If HAS NEXT was true, then return the line number on which the
//  next name appears. If HAS NEXT was false, then return an undefined INT.

    public int nextNumber()
    {
        return number;
    }

//  SKIP CHAR. If no more CHARs remain unread in LINE, then read the next line,
//  adding an EOL at the end. If no lines can be read, then read a line with an
//  EOF char in it. Otherwise just read the next char from LINE and return it.

    private void skipChar()
    {
        if (index >= line.length())
        {
            index = 0;
            number += 1;
            try
            {
                line = reader.readLine();
                if (line == null)
                {
                    line = "" + eof;
                }
                else
                {
                    if (listing)
                    {
                        System.out.format("%05d ", number);
                        System.out.println(line);
                    }
                    line += eol;
                }
            }
            catch (IOException ignore)
            {
                line = "" + eof;
            }
        }
        ch = line.charAt(index);
        index += 1;
    }

//  SKIP COMMENT. We end up here if we read a '/'. If it is followed by another
//  '/', or by a '*', then we skip a comment. We must skip comments so that any
//  names that appear in them will be ignored.

    private void skipComment()
    {
        skipChar();
        if (ch == '*')
        {
            skipChar();
            while (true)
            {
                if (ch == '*')
                {
                    skipChar();
                    if (ch == '/')
                    {
                        skipChar();
                        return;
                    }
                }
                else if (ch == eof)
                {
                    return;
                }
                else
                {
                    skipChar();
                }
            }
        }
        else if (ch == '/')
        {
            skipChar();
            while (true)
            {
                if (ch == eof)
                {
                    return;
                }
                else if (ch == eol)
                {
                    skipChar();
                    return;
                }
                else
                {
                    skipChar();
                }
            }
        }
    }

//  SKIP DELIMITED. Skip a string constant or a character constant, so that any
//  names that appear inside them will be ignored.  Throw an exception if there
//  is a missing delimiter at the end.

    private void skipDelimited()
    {
        char delimiter = ch;
        skipChar();
        while (true)
        {
            if (ch == delimiter)
            {
                skipChar();
                return;
            }
            else
            {
                switch (ch)
                {
                    case eof:
                    case eol:
                    {
                        throw new IllegalStateException("Bad string in '" + path + "'.");
                    }
                    case '\\':
                    {
                        skipChar();
                        if (ch == eol || ch == eof)
                        {
                            throw new IllegalStateException("Bad string in '" + path + "'.");
                        }
                        else
                        {
                            skipChar();
                        }
                        break;
                    }
                    default:
                    {
                        skipChar();
                        break;
                    }
                }
            }
        }
    }

//  SKIP NAME. Skip a name, but convert it to a STRING, stored in NAME.

    private void skipName()
    {
        StringBuilder builder = new StringBuilder();
        while (Character.isJavaIdentifierPart(ch))
        {
            builder.append(ch);
            skipChar();
        }
        name = builder.toString();
    }

//  SKIP NUMBER. Skip something that might be a number. It starts with a digit,
//  followed by zero or more letters and digits. We must do this so the letters
//  aren't treated as names.

    private void skipNumber()
    {
        skipChar();
        while (Character.isJavaIdentifierPart(ch))
        {
            skipChar();
        }
    }

//  MAIN. Get a file pathname from the command line. Read a series of names and
//  their line numbers from the file, and write them one per line. For example,
//  the command "java Nomenclator Nomenclator.java" reads names from the source
//  file you are now looking at. This method is only for debugging!
}


//////////////////////////////////////////////////////////////
////////////////BinarySearchTree//////////////////////////////////////////////
//////////////////////////////////////////////////////////////

class BinarySearchTree
{

    public Node root;
    class Node
    {
        public String name;
        public AssociationList<Object> lines;
        public Node left, right;

        public Node(String name, AssociationList<Object> lines) {
            this.name = name;
            this.lines = lines;
        }

        public void put(String name, AssociationList<Object> lines) {
            if (name.compareTo(this.name) < 0) {
                if (left != null) {
                    left.put(name, lines);
                } else {
                    left = new Node(name, lines);
                }
            } else if (name.compareTo(this.name) > 0) {
                if (right != null) {
                    right.put(name, lines);
                } else {
                    right = new Node(name, lines);
                }
            } else if (this.lines.getFirst() != lines.getFirst()) {
                this.lines.put(lines.getFirst());
            }
        }
    }
    //
    class AssociationList<Value> {

        private class Node {
            private Value value;
            private Node next;

            private Node(Value value) {
                this.value = value;
            }
        }

        private Node first;
        private Node temp;

        public AssociationList()
        {
            first = null;
            temp = null;
        }

        public void put(Value value) {
            if (first == null) {
                first = new Node(value);
            } else {
                temp = first;
                first = new Node(value);
                first.next = temp;
            }
        }

        public Value getFirst() {
            return first.value;
        }

        public String linesString() {
            String cadena = "";
            Node tmp = first;
            while (tmp != null) {
                cadena = String.format("%05d ", tmp.value) + "\t" + cadena;
                tmp = tmp.next;
            }
            return cadena;
        }
    }


    public void add(Object line, String name) {
        AssociationList<Object> lines = new AssociationList();
        lines.put(line);
        if (root == null) {
            root = new Node(name, lines);
        } else {
            root.put(name, lines);
        }
    }

    public void traverse(Node root)
    {
        if (root.left != null)
        {
            traverse(root.left);
        }
        System.out.println(root.name + "\t\t" + root.lines.linesString());
        if (root.right != null) {
            traverse(root.right);
        }
    }


    public static void main(String[] args)
    {
        //String path = System.getProperty("user.dir")+"\\src\\Project3.java";
        //To use the same code Project3

        String path = System.getProperty("user.dir")+"\\src\\Factorials.java";// For any File inside of the directory and inside of the src folder

        BinarySearchTree tree = new BinarySearchTree();
        Nomenclator nomenclator = new Nomenclator(path, true);
        //Nomenclator nomenclator = new Nomenclator(path, false);
       // Nomenclator nomenclator = new Nomenclator(args[0], true);
        while (nomenclator.hasNext())
             {
        tree.add(nomenclator.nextNumber(), nomenclator.nextName());
             }
        tree.traverse(tree.root);
    }
}

///////////////////////////////////////////////////////
/////////////////EXAMPLE//////Factorials.java//////////
///////////////////////////////////////////////////////
/*
//  FACTORIALS. Print some factorials.

class Factorials
{

//  FACTORIAL. Return the factorial of N.

    private static int factorial(int n)
    {
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

//  MAIN. Write the factorials of 0 through 10.

    public static void main(String [] args)
    {
        for (int k = 0; k <= 10; k += 1)
        {
            System.out.println(k + "! = " + factorial(k));
        }
    }
}
*/




////////////////////////////////////////////////
////////////////OUTPUT//////////////////////////
////////////////////////////////////////////////
/*
00001 //  FACTORIALS. Print some factorials.
00002
00003 class Factorials
00004 {
00005
00006 //  FACTORIAL. Return the factorial of N.
00007
00008 private static int factorial(int n)
00009 {
00010 if (n == 0)
00011 {
00012 return 1;
00013 }
00014 else
00015 {
00016 return n * factorial(n - 1);
00017 }
00018 }
00019
00020 //  MAIN. Write the factorials of 0 through 10.
00021
00022 public static void main(String [] args)
00023 {
00024 for (int k = 0; k <= 10; k += 1)
00025 {
00026 System.out.println(k + "! = " + factorial(k));
00027 }
00028 }
00029 }
Factorials		00003
String		00022
System		00026
args		00022
class		00003
else		00014
factorial		00008 	00016 	00026
for		00024
if		00010
int		00008 	00024
k		00024 	00026
main		00022
n		00008 	00010 	00016
out		00026
println		00026
private		00008
public		00022
return		00012 	00016
static		00008 	00022
void		00022
 */
