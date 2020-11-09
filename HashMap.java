import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class HashMap<K,V> {
	private ArrayList<Node<K,V>> buckets;     // Hash table
	private int nbucketss; 					 // capacity
	private int size;     					 // current size
	
	// Constructor
	public HashMap()
	{
		buckets = new ArrayList<>();
		nbucketss = 0;
		size = 0;
	}
	
	// Constructor : Initialize an empty chain of capacity n 
	public HashMap(int n)
	{
		buckets = new ArrayList<>();
		nbucketss = n;
		size = 0;
		
		for(int i = 0; i < nbucketss; i++)
			buckets.add(null);
	}
	
	public String toString() {
		return buckets.toString()+"\n";
	}
	
	//Helpers
	// Since our attributs are private, we define methods to get their values
	// Get size 
	public int size() {return size;}
	//Get capacity
	public int nbucketss() {return nbucketss;}
	// Check if the chain is empty
	public boolean isEmpty() {return size()==0;}
	
	//HashCode function
	public int hashCode(K key)
	{
		return key.hashCode();
	}

	// Hash function	
	private int getIndex(K key)
	{
		int hashCode = hashCode(key);
		return ((hashCode % nbucketss)+nbucketss)%nbucketss; 
	}
	
	// Add a node 
	public void add(K key, V value)
	{
		int index = getIndex(key);
		Node<K,V> node = buckets.get(index);
		while( node != null) {
			if(node.key.equals(key)) {
				node.value = value;
				return;
			}
			node = node.next;
		}
		size++;
		node = buckets.get(index);
		Node<K,V> newNode = new Node<K,V>(key,value);
		newNode.next = node;
		buckets.set(index, newNode);
	}
	
	// Get value given key
	public V get(K key)
	{
		int index = getIndex(key);
		Node<K,V> node = buckets.get(index);
		while(node != null) {
			if(node.key.equals(key)) return node.value;
			node = node.next;
		}
		return null;
	}
	
	// Remove 
	public V remove(K key)
	{
		int index = getIndex(key);
		Node<K,V> node = buckets.get(index);
		if(node==null) return null;
		if(node.key.equals(key))
		{
			V val = node.value;
			node = node.next;
			buckets.set(index, node);
			size--;
			return val;
		}
		else
		{
			Node<K,V> nodeP = null;
			while(node!=null) {
				if(node.key.equals(key)) {
					nodeP.next = node.next;
					size--;
					return node.value;
				}
				nodeP = node;
				node = node.next;
			}
			size--;
			return null;
		}
	}

	
	
	
	/*public static void main(String[] args) {
		HashMap<String, Integer>hash = new HashMap<>(10);
		hash.add("Example1", 1);
		hash.add("Example2", 2);
		System.out.println(hash.size());
		hash.add("Example3", 3);
		System.out.println(hash.size());
		System.out.println(hash.isEmpty());
		//hash.add("Example4", 3);
		hash.add("Example4", 3);
		System.out.println(hash.size());

		
		
	}*/

}
