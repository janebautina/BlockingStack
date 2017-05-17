import java.util.ArrayList;
import java.util.List;


public class BlockingStack<T> {
   
   private List<T> stack = new ArrayList<T>();
   private int limit = 0;
   
   public BlockingStack(int limit) {
      this.limit = limit;
   }
   
   public synchronized void push(T item) throws InterruptedException {
      while(stack.size() == this.limit) 
         wait();
      if(stack.size() == 0) 
         notifyAll();
      stack.add(item);
   } 
   
   public synchronized T pop() throws InterruptedException {
      while(stack.size() == 0)  
         wait();
      if(stack.size() == limit)
         notifyAll();
      return stack.remove(stack.size() - 1);
   }
}

