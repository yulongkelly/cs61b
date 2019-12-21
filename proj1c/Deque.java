public interface Deque<Type>{

	public boolean isEmpty();

	public void addFirst(Type x);

	public void addLast(Type x);

	public Type removeFirst();

	public Type removeLast();

	public void printDeque();

	public int size() ;

	public Type get(int index);
}