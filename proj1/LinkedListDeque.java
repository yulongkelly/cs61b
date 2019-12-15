public class LinkedListDeque<Type>{
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
	}

	public LinkedListDeque(Type x) {
		sentinel = new NewNode(null, null, null);
		sentinel.next = new NewNode(sentinel, x, sentinel);
		sentinel.previous = sentinel.next;
		size = 1;
	} 

	public boolean isEmpty() {
		if(sentinel.next == null){
			return true;
		} else{
			return false;
		}
	}

	public void addFirst(Type x){
		sentinel.next = new NewNode(sentinel, x, sentinel.next);
      	sentinel.next.next.previous = sentinel.next;
      	size = size + 1;
	}

	public void addLast(Type x){
		sentinel.previous.next = new NewNode(sentinel.previous, x, sentinel);
		sentinel.previous = sentinel.previous.next;
		size = size + 1;
	}

	public void removeFirst() {
		if(!isEmpty()){
			sentinel.next = sentinel.next.next;
			sentinel.next.previous = sentinel;
		}
		size = size - 1;
	}

	public void removeLast(){
		if(!isEmpty()){
			sentinel.previous.previous.next = sentinel;
			sentinel.previous = sentinel.previous.previous;
			size = size - 1;
		}
	}

	public void printDeque(){
		NewNode p = sentinel.next;
		for(int n = 1; n<=size; n++){
			System.out.print(p.item + " ");
			p = p.next;
		}
	}

	public int size() {
		return size;
	}

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
	}
}