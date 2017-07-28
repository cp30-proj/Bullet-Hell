
package stackcalculatordp;

/**
 *
 * @author Paolo Dela Peña
 */
public class StackDP <E>{
    private final int MAX_STACK = 100;
    private E[] items = (E[])new Object[MAX_STACK]; ;
    private int numItems=0;
    
    public boolean full(){
        return numItems==MAX_STACK;
    }
    
    public boolean empty(){
        return 0==numItems;
    }
    
    public E top() throws StackEmptyExceptionDP{
        if(numItems>0)
            return items[numItems-1];
        else
            throw new StackEmptyExceptionDP("ERROR! Stack empty");
    }
    
    public void push(E newitem) throws StackFullException{
        if(numItems<MAX_STACK){
            items[numItems] = newitem;
            numItems++;
        }
        else
            throw new StackFullException("ERROR! Stack full");
    }
    
    public E pop()throws StackEmptyExceptionDP{
        if(numItems>0){
            E temp = items[numItems-1];
            numItems--;
            return temp;
        }
        else
            throw new StackEmptyExceptionDP("ERROR! Stack empty");
    }
    
    public int size(){
        return numItems;
    }
    
}
