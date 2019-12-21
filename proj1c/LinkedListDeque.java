public class LinkedListDeque<Type> implements Deque<Type>{
	public class NewNode {
		public NewNode previous;
		public Type item;
		public NewNode next;

	public NewNode(NewNode n1, Type i, NewNode n2) {
			previous = n1;
			item = i;
			next = n2;
		}
	}

	private NewNode sentinel;
	public int size;

	public LinkedListDeque(){
		sentinel = new NewNode(null, null, null);
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
		size = 0;
	}

	public LinkedListDeque(Type x) {
		sentinel = new NewNode(null, null, null);
		sentinel.next = new NewNode(sentinel, x, sentinel);
		sentinel.previous = sentinel.next;
		size = 1;
	} 
	@Override
	public boolean isEmpty() {
		if(sentinel.next == null){
			return true;
		} else{
			return false;
		}
	}
	@Override
	public void addFirst(Type x){
		sentinel.next = new NewNode(sentinel, x, sentinel.next);
      	sentinel.next.next.previous = sentinel.next;
      	size = size + 1;
	}
	@Override
	public void addLast(Type x){
		sentinel.previous.next = new NewNode(sentinel.previous, x, sentinel);
		sentinel.previous = sentinel.previous.next;
		size = size + 1;
	}
	@Override
	public Type removeFirst() {
		if(!isEmpty()){
			Type first = sentinel.next.item;
			sentinel.next = sentinel.next.next;
			sentinel.next.previous = sentinel;
			size = size - 1;
			return first;
		}
		return null;
	}
	@Override
	public Type removeLast(){
		if(!isEmpty()){
			Type last = sentinel.previous.item;
			sentinel.previous.previous.next = sentinel;
			sentinel.previous = sentinel.previous.previous;
			size = size - 1;
			return last;
		}
		return null;
	}
	@Override
	public void printDeque(){
		NewNode p = sentinel.next;
		for(int n = 1; n<=size; n++){
			System.out.print(p.item + " ");
			p = p.next;
		}
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public Type get(int index){
		NewNode p = sentinel.next;
		for(int n = 0; n<size; n++){
			if(n == index){
				return p.item;
			}
			p = p.next;
		}
		return null;
	}

	public void reverse(){
      NewNode front = sentinel.next;
      for(int i = 1; i<=size; i++){
         NewNode next = sentinel.next.next;
         sentinel.next.next = sentinel.next.previous;
         sentinel.next.previous = next;
         sentinel.next = next;
      }
      sentinel.next = sentinel.previous;
      sentinel.previous = front;   
	}

	public static void main(String[] args){
		LinkedListDeque<Integer> list = new LinkedListDeque<Integer>(3);
		list.addFirst(10);
		list.addFirst(13);
		list.addLast(23);
		list.removeFirst();
		list.removeLast();
		list.size();
		list.printDeque();
		list.get(1);
		list.addFirst(30);
		list.reverse();
	}
}