public class ArrayDeque<Type>{
    private Type[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int resize_factor = 2;
    private static double usage_factor = 0.25;

    public ArrayDeque(){
        size = 0; 
        items =(Type []) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    public void resize(){
        Type[] b = (Type []) new Object[size*resize_factor];
        System.arraycopy(items, 0, b, 0, size);
        items = b;
    }

    public boolean isEmpty() {
        if(size == 0){
            return true;
        } 
        else{
            return false;
        }
    }

    public boolean isFull(){
        if(size == items.length){
            return true;
        }
        else{
            return false;
        }
    }

    public void addFirst(Type x){
        items[nextFirst] = x;
        size = size + 1;
        if(isFull()){
            resize();
        }
        nextFirst = nextFirst - 1;
        if(nextFirst<0){
            nextFirst = items.length-1;
        } 
    }

    public void addLast(Type x){
        items[nextLast] = x;
        size = size + 1;
        if(isFull()){
            resize();
        }
        nextLast = nextLast + 1;
    }

    public void checkUsage(){
        if(size<usage_factor*items.length){
            Type[] b = (Type []) new Object[items.length/2];
            int p = 0;
            for(int i = 0; i < items.length; i++){
                if(items[i] != null){
                    b[p] = items[i];
                    p++;
                }
            }
            items = b;
            nextFirst = size;
            nextLast = size + 1;
        }

    }

    public Type removeFirst(){
        if (nextFirst == 0) {
            return null;
        }
        Type first = items[nextFirst+1];
        items[nextFirst+1] = null;
        size = size - 1;
        nextFirst = nextFirst + 1;
        if(nextFirst>=items.length){
            nextFirst = 1;
        }
        checkUsage();
        return first;
    }

    public Type removeLast(){
        if (nextLast == 1) {
            return null;
        }
        Type last = items[nextLast-1];
        items[nextLast-1] = null;
        size= size-1;
        nextLast = nextLast-1;//Housekeeping
        checkUsage();
        return last;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 0; i < size; i++){
            System.out.print(items[i]+" ");
        }
    }

    public Type get(int index){
        return items[index];
    }

    public void reverse() {
        
    }
 
    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addFirst(50);
        a.addFirst(30);
        a.addLast(100);
        a.removeFirst();
        a.removeLast();
        a.get(2);
    }
}