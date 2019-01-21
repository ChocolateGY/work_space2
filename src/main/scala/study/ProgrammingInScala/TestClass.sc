var flag = false

def myAssert(predicate: => Boolean) =
  if (flag && !predicate)
    throw new AssertionError()
val x= 5
myAssert(5/0 ==0)
//myAssert(()=>5<3)
def boolAssert(predicate:Boolean) =
    if(flag && !predicate)
        throw new AssertionError()

boolAssert(x/0 == 0)


