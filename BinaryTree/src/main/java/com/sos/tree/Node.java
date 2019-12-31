package com.sos.tree;

public class Node<K, V> implements Comparable <K>
{
	private char direction = 'P';
	private K key;
	private V value;
	private Node<K,V> parent;
	private Node<K,V> leftChild;
	private Node<K,V> rightChild;

	
	
	/**
	 * 
	 */
	public Node()
	{
		//Do Nothing
	}

	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public Node(K key, V value)
	{
		setKey(key);
		setValue(value);
	}

	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param parent
	 */
	public Node(K key, V value, Node<K,V> parent)
	{
		this(key,value);
		setParent(parent);
	}

	
	/**
	 * 
	 */
	public int compareTo(K obj)
	{
		if(obj == null)
			return Integer.MAX_VALUE;
		
		if(obj.hashCode() == key.hashCode() && obj.equals(this.key))
			return 0;
		else if(obj.hashCode() > this.key.hashCode())
			return 1;
		else if(obj.hashCode() < this.key.hashCode())
			return -1;
		else
			return Integer.MAX_VALUE;
	}

	
	/**
	 * 
	 * @return
	 */
	public K getKey()
	{
		return this.key;
	}

	
	/**
	 * 
	 * @param key
	 */
	public void setKey(K key)
	{
		this.key = key;
	}

	
	/**
	 * 
	 * @return
	 */
	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	
	/**
	 * 
	 * @return
	 */
	public Node<K,V> getParent()
	{
		return parent;
	}

	
	/**
	 * 
	 * @param parent
	 */
	public void setParent(Node<K,V> parent)
	{
		this.parent = parent;
	}

	
	/**
	 * 
	 * @return
	 */
	public Node<K,V> getLeftChild()
	{
		return leftChild;
	}

	
	/**
	 * 
	 * @param leftChild
	 */
	public void setLeftChild(Node<K,V> leftChild)
	{
		if(leftChild != null)
		{
			leftChild.setParent(this);
			leftChild.setDirection('L');
		}
		
		this.leftChild = leftChild;
	}

	
	/**
	 * 
	 * @return
	 */
	public Node<K,V> getRightChild()
	{
		return rightChild;
	}

	
	/**
	 * 
	 * @param rightChild
	 */
	public void setRightChild(Node<K,V> rightChild)
	{
		if(rightChild != null)
		{
			rightChild.setParent(this);
			rightChild.setDirection('R');
		}
		
		this.rightChild = rightChild;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean hasChildren()
	{
		return (hasLeftChild() || hasRightChild());
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean hasRightChild()
	{
		return (rightChild != null);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean hasLeftChild()
	{
		return (leftChild != null);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean hasParent()
	{
		return (parent != null);
	}

	
	/**
	 * 
	 * @return
	 */
	public char getDirection()
	{
		return direction;
	}

	
	/**
	 * 
	 * @param direction
	 */
	public void setDirection(char direction)
	{
		this.direction = direction;
	}
	
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return key.toString()+direction+"("+((parent != null)?parent.getKey():"*")+")";
	}

	
	/**
	 * 
	 * @return
	 */
	public int hashcode()
	{
		return key.hashCode();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean isLeaf()
	{
		return (this.leftChild == null && this.rightChild == null);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean isroot()
	{
		return (this.parent == null);
	}
	
	
	/**
	 * 
	 */
	public void clear()
	{
		reset();
		setKey(null);
		setValue(null);
	}
	
	
	/**
	 * 
	 */
	public void reset()
	{
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj)
	{
		if(obj instanceof Node)
		{
			Node <K,V> node = (Node <K,V>)obj;
			return node.getKey().equals(key);
		}
		
		return false;
	}
}
