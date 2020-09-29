#Autor: Alex Lema lemac001
#CSCI 1913
#10/02/2017

#-------------------------------------------------
def most(P, S):
    def helper(P,S):
        if len(S) == 0:
            return []
        elif P(S[0]):
            return [P(S[0])] + helper(P, S[1:])
        else:
            return helper(P, S[1:])
    if len(helper(P,S)) > len(S)/2:
        return True
    else:
        return False

def odd(N):
  return N % 2 != 0

print(most(odd, []))         #  False    2 points
print(most(odd, [0]))        #  False    2 points
print(most(odd, [1]))        #  True     2 points
print(most(odd, [1, 2]))     #  False    2 points
print(most(odd, [1, 2, 3]))  #  True     2 points

#---------------------------------------

def sigma(F, B, E):
    if B > E:
            return 0
    else:
            return F(E) + sigma(F, B, E - 1)

def sqr(N):
  return N * N

print(sigma(sqr, 0, 0))    #  0          2 points
print(sigma(sqr, 1, 0))    #  0          2 points
print(sigma(sqr, 0, 4))    #  30         2 points
print(sigma(sqr, 1, 1))    #  1          2 points
print(sigma(sqr, 2, 100))  #  338349     2 points

#--------------------------------------------------------