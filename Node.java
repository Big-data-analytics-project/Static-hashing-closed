
// This class implements a node/element of a hash table 

public class Node<K, V>{
	V value;
	K key;
	Node<K,V> next; 
	
	// Constructor
	public Node(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

}
