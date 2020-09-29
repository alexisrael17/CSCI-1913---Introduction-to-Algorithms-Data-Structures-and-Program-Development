import types

def left(e):
    return e[0]
def op(e):
    return e[1]
def right(e):
    return e[2]
def isInside(v, e):
    if type(e) == tuple:
        return isInside(v, left(e)) or isInside(v, right(e))
    else:
        if v == e:
            return True
        else:
            return False
def solve(v ,q):
    if isInside(v, left(q)):
        return solving(v,q)
    elif isInside(v, right(q)):
        return solving(v,(right(q),op(q),left(q)))
    else:
        return None
def solving(v, q):
    if v == left(q):
        return q
    else:
        if op(left(q)) == '+':
            return solving(v,solvingAdd(v, q))
        elif op(left(q)) == '-':
            return solving(v,solvingSubtract(v, q))
        elif op(left(q)) == '*':
            return solving(v,solvingMultiply(v, q))
        elif op(left(q)) == '/':
            return solving(v,solvingDivide(v, q))
        else:
            return None
def solvingAdd(v, q):
    if isInside(v,left(left(q))):
        return (left(left(q)),'=',(right(q),'-',right(left(q))))
    else:
        return (right(left(q)),'=',(right(q),'-',left(left(q))))
def solvingSubtract(v, q):
    if isInside(v,left(left(q))):
        return (left(left(q)),'=',(right(q),'+',right(left(q))))
    else:
        return (right(left(q)),'=',(left(left(q)),'-',right(q)))

def solvingMultiply(v, q):
    if isInside(v,left(left(q))):
        return (left(left(q)),'=',(right(q),'/',right(left(q))))
    else:
        return (right(left(q)),'=',(right(q),'/',left(left(q))))
def solvingDivide(v, q):
    if isInside(v,left(left(q))):
        return (left(left(q)),'=',(right(q),'*',right(left(q))))
    else:
        return (right(left(q)),'=',(left(left(q)),'/',right(q)))

#
#  TESTS. Test the equation solver for CSci 1913 Lab 1.
#
#    James Moen
#    22 Jan 17
#
#  Every test is followed by a comment which shows what must be printed if your
#  code works correctly. It also shows how many points the test is worth, for a
#  total of 35 possible points.
#

print(isInside('x', 'x'))                          #  True   1 point
print(isInside('x', 'y'))                          #  False  1 point
print(isInside('x', ('x', '+', 'y')))              #  True   2 points
print(isInside('x', ('a', '+', 'b')))              #  False  2 points
print(isInside('+', ('a', '+', 'b')))              #  False  2 points
print(isInside('x', (('m', '*', 'x'), '+', 'b')))  #  True   2 points

print(solve('x', (('a', '+', 'x'), '=', 'c')))
#  ('x', '=', ('c', '-', 'a'))  2 points

print(solve('x', (('x', '+', 'b'), '=', 'c')))
#  ('x', '=', ('c', '-', 'b'))  2 points

print(solve('x', (('a', '-', 'x'), '=', 'c')))
#  ('x', '=', ('a', '-', 'c'))  2 points

print(solve('x', (('x', '-', 'b'), '=', 'c')))
#  ('x', '=', ('c', '+', 'b'))  2 points

print(solve('x', (('a', '*', 'x'), '=', 'c')))
#  ('x', '=', ('c', '/', 'a'))  2 points

print(solve('x', (('x', '*', 'b'), '=', 'c')))
#  ('x', '=', ('c', '/', 'b'))  2 points

print(solve('x', (('a', '/', 'x'), '=', 'c')))
#  ('x', '=', ('a', '/', 'c'))  2 points

print(solve('x', (('x', '/', 'b'), '=', 'c')))
#  ('x', '=', ('c', '*', 'b'))  2 points

print(solve('y', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('y', '=', (('m', '*', 'x'), '+', 'b'))  2 points

print(solve('x', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('x', '=', (('y', '-', 'b'), '/', 'm'))  2 points

print(solve('a', (('b', '+', 'c'), '=', ('d', '*', (('a', '/', 'e'), '-', 'f')))))
# ('a', '=', (((('b', '+', 'c'), '/', 'd'), '+', 'f'), '*', 'e'))  5 points