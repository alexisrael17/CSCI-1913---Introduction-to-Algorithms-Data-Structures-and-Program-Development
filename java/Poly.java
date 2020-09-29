/*
ALEX LEMA
CSCI 1931
PROJECT 2
 */

class Poly
{
    private Term head;
    private class Term {

        int expo;
        int coef;
        Term next;

        public Term(int coef, int expo, Term next) {
            this.coef = coef;
            this.expo = expo;
            this.next = next;
        }
    }


    ////////////////////////////////
    public Poly() {
        head = null;
    }
    ////////////////////////////////

    public Poly term(int coef, int expo) {
        try {
            if (coef == 0 || expo < 0) {
                throw new IllegalArgumentException("Term not created! coefficient: "+coef+" , exponent: "+expo);
            }
            if (head == null || expo > head.expo) {
                head = new Term(coef, expo, head);
                return this;
            }
            Term cur = head;
            Term prev = null;
            while (cur != null && expo < cur.expo) {
                prev = cur;
                cur = cur.next;
            }
            if (cur == null || expo != cur.expo) {
                prev.next = new Term(coef, expo, cur);
            }
            else {
                cur.coef += coef;
            }
            return this;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return this;
        }
    }
    ///////////////////////////////////////////////

    public Poly plus(Poly that) {
        Poly res = new Poly();
        for (Term tmp = head; tmp != null; tmp = tmp.next) {
            res.term(tmp.coef, tmp.expo);
        }
        for (Term tmp = that.head; tmp != null; tmp = tmp.next) {
            res.term(tmp.coef, tmp.expo);
        }
        return res;
    }

    /////////////////////////////////////////////

    public void add(int coef, int expo) {
            try {
                if (coef == 0 || expo < 0) {
                    throw new IllegalArgumentException("Term not created! coefficient: "+coef+" , exponent: "+expo);
                }
                if (head == null || expo > head.expo) {
                    head = new Term(coef, expo, head);
                }
                else {
                    Term cur = head;
                    Term prev = null;
                    while (cur != null && expo < cur.expo) {
                        prev = cur;
                        cur = cur.next;

                    }
                    if (cur == null || expo != cur.expo) {
                        prev.next = new Term(coef, expo, cur);
                    } else {
                        cur.coef += coef;
                        if (cur.coef == 0){
                            if (cur.next!=null){
                                prev.next=cur.next;
                            }else{
                                prev.next=null;
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
            }
        }

    //////////////////////////////////

    public Poly minus() {
        Poly res = new Poly();
        for (Term tmp = head; tmp != null; tmp = tmp.next) {
            if (tmp.expo != 0) {
                res.term(tmp.coef * -1, tmp.expo);
            }
        }
        return res;
    }
    ///////////////////////////////////
    public String toString()
    {

        StringBuilder builder = new StringBuilder();
        if (head == null) {
            builder.append("0");
        }
        else {
            for (Term tmp = head; tmp != null; tmp = tmp.next) {
                if (tmp.coef > 0&& tmp != head) {
                    builder.append("+");
                }

                String form = String.valueOf(tmp.coef);
                if (tmp.expo == 0) {
                    builder.append(form);
                } else if (tmp.expo == 1) {
                    builder.append(form + "x");
                } else {
                    builder.append(form + "x" + tmp.expo);
                }
            }
        }
        return builder.toString();
    }
}


class PollyEsther   {

    public static void main(String[] args) {
        Poly p0 = new Poly();
        Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
        Poly p2 = new Poly().term(2, 1).term(3, 2);
        Poly p3 = p2.minus();

        System.out.println(p0);           //  0
        System.out.println(p1);           //  1x3 + 1x2 + 1x1
        System.out.println(p2);           //  3x2 + 2x1
        System.out.println(p3);           //  −3x2 − 2x1

        System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1
        System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1

        //////////////////////////////////////////////
        /* MY TEST*/

        System.out.println("    *** MY TEST ***"); //    *** MY TEST ***
        System.out.println(p1);          // 1x3+1x2+1x
        p1.add(4, -9);         //Term not created! coefficient: 4 , exponent: -9
        System.out.println(p1);           //1x3+1x2+1x
        p1.add(4, 9);
        System.out.println(p1);           //4x9+1x3+1x2+1x


        Poly p4 = new Poly();
        Poly p5 = new Poly().term(0, 8).term(1, 4).term(2, 6);  //Term not created! coefficient: 0 , exponent: 8
        Poly p6 = new Poly().term(2, 1).term(3, 2);
        Poly p7 = new Poly().term(1, 2).term(1, 3).term(10, 0);
        Poly p8 = p5.minus();

        System.out.println(p4);           //  0
        System.out.println(p5);           // 2x6+1x4
        p4.add(4, -9);          //Term not created! coefficient: 4 , exponent: -9
        p4.add(4, 9);
        p4.add(4, 8);
        p4.add(-4, 8);
        p4.add(-7, 8);
        p4.add(4, 13);

        System.out.println(p4);             //4x13+4x9-7x8
        p4.add(-4, -8);        //Term not created! coefficient: -4 , exponent: -8
        System.out.println(p4);            //4x13+4x9-7x8
        System.out.println(p5);            //2x6+1x4
        System.out.println(p6);              //3x2+2x
        System.out.println(p7);               //1x3+1x2+10
        System.out.println(p8);              //-2x6-1x4
        System.out.println(p6.plus(p7));     //1x3+4x2+2x+10
        System.out.println(p6.plus(p8));      //-2x6-1x4+3x2+2x

        p5.add(1, 4);
        p5.add(1, 4);
        p6.add(2, 8);
        p6.add(3, 8);
        p6.add(4, 7);

        System.out.println(p5);                //2x6+3x4
        System.out.println(p6);                //5x8+4x7+3x2+2x
        Poly p11 = new Poly().term(0, 8);    //Term not created! coefficient: 0 , exponent: 8
        System.out.println(p11);                           //0
    }
}



/*

///OUTPUT///

0
1x3+1x2+1x
3x2+2x
-3x2-2x
1x3+4x2+3x
1x3-2x2-1x
    *** MY TEST ***
1x3+1x2+1x
java.lang.IllegalArgumentException: Term not created! coefficient: 4 , exponent: -9
1x3+1x2+1x
4x9+1x3+1x2+1x
java.lang.IllegalArgumentException: Term not created! coefficient: 0 , exponent: 8
0
2x6+1x4
java.lang.IllegalArgumentException: Term not created! coefficient: 4 , exponent: -9
4x13+4x9-7x8
java.lang.IllegalArgumentException: Term not created! coefficient: -4 , exponent: -8
4x13+4x9-7x8
2x6+1x4
3x2+2x
1x3+1x2+10
-2x6-1x4
1x3+4x2+2x+10
-2x6-1x4+3x2+2x
2x6+3x4
5x8+4x7+3x2+2x
java.lang.IllegalArgumentException: Term not created! coefficient: 0 , exponent: 8
0

*/