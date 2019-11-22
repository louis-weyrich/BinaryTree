package com.sos.tree;

public class Node<T> implements Comparable <T>
{
	private char direction = 'P';
	private T value;
	private Node<T> parent;
	private Node<T> leftChild;
	private Node<T> rightChild;
	private Node<T> centerChild;

	public Node()
	{
		//Do Nothing
	}

	public Node(T value)
	{
		setValue(value);
	}

	public Node(T value, Node<T> parent)
	{
		setValue(value);
		setParent(parent);
	}

	public int compareTo(T obj)
	{
		if(obj == null)
			return -1;
		
		if(obj.equals(this.value))
			return 0;
		else if(obj.hashCode() > this.value.hashCode())
			return 1;
		else if(obj.hashCode() < this.value.hashCode())
			return -1;
		else
			return Integer.MAX_VALUE;
	}

	public T getValue()
	{
		return this.value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public Node<T> getParent()
	{
		return parent;
	}

	public void setParent(Node<T> parent)
	{
		this.parent = parent;
	}

	public Node<T> getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild)
	{
		if(leftChild != null)
		{
			leftChild.setParent(this);
			leftChild.setDirection('L');
		}
		
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild()
	{
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild)
	{
		if(rightChild != null)
		{
			rightChild.setParent(this);
			rightChild.setDirection('R');
		}
		
		this.rightChild = rightChild;
	}

	public Node<T> getCenterChild() 
	{
		return centerChild;
	}

	public void setCenterChild(Node<T> centerChild) 
	{
		if(centerChild != null)
		{
			centerChild.setParent(this);
			centerChild.setDirection('C');
		}
		
		this.centerChild = centerChild;
	}
	
	public boolean hasChildren()
	{
		return (hasLeftChild() || hasRightChild());
	}
	
	public boolean hasRightChild()
	{
		return (rightChild != null);
	}
	
	public boolean hasLeftChild()
	{
		return (leftChild != null);
	}
	
	public boolean hasParent()
	{
		return (parent != null);
	}
	
	public boolean hasCenterChild()
	{
		return (this.centerChild != null);
	}

	public char getDirection()
	{
		return direction;
	}

	public void setDirection(char direction)
	{
		this.direction = direction;
	}
	
	@Override
	public String toString()
	{
		return value.toString()+direction+"("+((parent != null)?parent.getValue():"*")+")";
	}

	public int hashcode()
	{
		return value.hashCode();
	}
	
	public boolean isLeaf()
	{
		return (this.leftChild == null && this.rightChild == null);
	}
	
	public boolean isroot()
	{
		return (this.parent == null);
	}
	
	public void reset()
	{
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.centerChild = null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj)
	{
		if(obj instanceof Node)
		{
			Node <T> node = (Node <T>)obj;
			return node.getValue().equals(value);
		}
		
		return false;
	}
}
