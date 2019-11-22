/**
 * 
 */
package com.sos.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

import com.sos.tree.Node;
import com.sos.tree.TransverseType;
import com.sos.tree.Tree;

/**
 * @author louis.weyrich
 *
 */
public class BinaryTree<T> implements Tree<T>
{
	
	private Node <T> root;

	/**
	 * 
	 */
	public BinaryTree()
	{
		// Do Nothing
	}
	
	public BinaryTree(Node<T> rootNode)
	{
		this.root = rootNode;
	}
	
	public Node <T> getRoot()
	{
		return this.root;
	}

	
	public char add(T value)
	{
		if(this.root == null)
		{
			this.root = new Node <T>(value, null);
			return root.getDirection();
		}
		else
		{
			Node <T> tempNode = this.root;
			int result = -1;
			
			while(tempNode != null && result != 0)
			{
				result = tempNode.compareTo(value);
				
				if(result == 0)
				{
					Node <T> newNode = new Node <T> (value);
					tempNode.setCenterChild(newNode);
					return newNode.getDirection();
				}
				else if(result == 1)
				{
					if(tempNode.getRightChild() == null)
					{
						Node <T> newNode = new Node <T> (value);
						tempNode.setRightChild(newNode);
						return newNode.getDirection();
					}
					else
					{
						tempNode = tempNode.getRightChild();
					}
				}
				else if(result == -1)
				{
					if(tempNode.getLeftChild() == null)
					{
						Node <T> newNode = new Node <T> (value);
						tempNode.setLeftChild(newNode);
						return newNode.getDirection();
					}
					else
					{
						tempNode = tempNode.getLeftChild();
					}
				}
				
			}
			
			return '-';
		}
		
	}

	
	public Node<T> remove(T value)
	{
		Node <T> nodeFound = find(value);
		if(nodeFound.isroot())
		{
			Node <T> rightChild = nodeFound.getRightChild();
			Node <T> leftChild = nodeFound.getLeftChild();
			
			if(leftChild != null)
			{
				Node <T> leftChildsRight = leftChild.getRightChild();
				
				while(leftChildsRight.getRightChild() != null)
				{
					leftChildsRight = leftChildsRight.getRightChild();
				}
				
				leftChildsRight.setParent(null);
				this.root = leftChildsRight;
				Node <T> leftchildsRightLeft = leftChildsRight.getLeftChild();
			
				while(leftchildsRightLeft != null && leftchildsRightLeft.getLeftChild() != null)
				{
					leftchildsRightLeft = leftchildsRightLeft.getLeftChild();
				}
			
				if(leftchildsRightLeft != null) leftchildsRightLeft.setLeftChild(leftChild);
				leftChildsRight.setRightChild(rightChild);
			}
			else if(rightChild != null)
			{
				this.root = rightChild;
			}
		}
		// if leaf null the parents pointer
		else if(nodeFound.isLeaf())
		{
			Node <T> nodeParent = nodeFound.getParent();
			if(nodeFound.getDirection() == 'L')
			{
				nodeParent.setLeftChild(null);
			}
			else
			{
				nodeParent.setRightChild(null);
			}
		}
		// if no right child then promote the left child
		else if(!nodeFound.isLeaf() && nodeFound.getRightChild() == null && nodeFound.getLeftChild() != null)
		{
			Node <T> nodeParent = nodeFound.getParent();
			Node <T> nodeLeft = nodeFound.getLeftChild();
			if(nodeFound.getDirection() == 'L')
			{
				nodeParent.setLeftChild(nodeLeft);
			}
			else
			{
				nodeParent.setRightChild(nodeLeft);
			}
		}		
		// if no left child then promote the right and repoint it to the removeds left
		else if(!nodeFound.isLeaf() && nodeFound.getLeftChild() == null && nodeFound.getRightChild() != null)
		{
			Node <T> nodeParent = nodeFound.getParent();
			Node <T> rightChild = nodeFound.getRightChild();
			// if has a right child that has a left child then replace with the right childs left child
			if(rightChild.getLeftChild() != null)
			{
				Node <T> leftChild = rightChild.getLeftChild();
				if(nodeFound.getDirection() == 'L')
				{
					nodeParent.setLeftChild(leftChild);
				}
				else
				{
					nodeParent.setRightChild(leftChild);
				}
				
				leftChild.setRightChild(rightChild);
			}
			else
			{
				if(nodeFound.getDirection() == 'L')
				{
					nodeParent.setRightChild(rightChild);
				}
				else
				{
					nodeParent.setLeftChild(rightChild);
				}
			}
		}
		
		return nodeFound;
	}

	
	public Node<T> find(T value)
	{
		boolean found = false;
		Node <T> node = this.root;
		
		while(!found)
		{
			int result = node.compareTo(value);
			if(result == 0)
			{
				return node;
			}
			else if(node.getLeftChild() == null && node.getRightChild() == null)
			{
				found = true;
			}
			else if(result == -1)
			{
				node = node.getLeftChild();
			}
			else if(result == 1)
			{
				node = node.getRightChild();
			}
		}
		
		return null;
	}

	
	public Node<T> findCommonAncestor(T value1, T value2)
	{
		List <Node <T>> list1 = new ArrayList <Node<T>> ();
		List <Node <T>> list2 = new ArrayList <Node<T>> ();
		
		Node<T> node1 = find(value1);
		Node<T> node2 = find(value2);
		
		while(node1 != null)
		{
			list1.add(node1);
			node1 = node1.getParent();
		}
		
		while(node2 != null)
		{
			list2.add(node2);
			node2 = node2.getParent();
		}
		
		for(Node<T> n1 : list1)
		{
			for(Node<T> n2 : list2)
			{
				if(n1.equals(n2))
				{
					return n2;
				}
			}
		}
		
		return null;
	}


	
	public List<T> transversePreOrder()
	{
		return nextPreOrderNode(getRoot(), new ArrayList <T> ());
	}
	
	private List<T> nextPreOrderNode(Node<T> node, List <T> list)
	{
		if(node != null)
		{
			list.addAll(getCenterNodeList(node));
			nextPreOrderNode(node.getLeftChild(), list);
			nextPreOrderNode(node.getRightChild(), list);
		}
		return list;
	}

	
	public List<T> transverseInOrder()
	{
		return nextInOrderNode(getRoot(), new ArrayList <T> ());
	}
	
	private List<T> nextInOrderNode(Node<T> node, List <T> list)
	{
		if(node != null)
		{
			nextInOrderNode(node.getLeftChild(), list);
			list.addAll(getCenterNodeList(node));
			nextInOrderNode(node.getRightChild(), list);
		}
		
		return list;
	}


	public List<T> transversePostOrder()
	{
		return nextPostOrderNode(getRoot(), new ArrayList <T> ());
	}
	
	private List<T> nextPostOrderNode(Node<T> node, List<T> list)
	{
		if(node != null)
		{
			nextPostOrderNode(node.getLeftChild(), list);
			nextPostOrderNode(node.getRightChild(), list);
			list.addAll(getCenterNodeList(node));
		}
		
		return list;
	}
	
	private List <T> getCenterNodeList(Node <T> node)
	{
		List <T> list = new ArrayList <T> ();
		
		if(node != null)
		{
			
			list.add(node.getValue());
			while(node.getCenterChild() != null)
			{
				node = node.getCenterChild();
				list.add(node.getValue());
			}
		}
		
		return list;
	}

	
	public int nodeDepth(T value) {
		Node <T> node = find(value);
		int depth = 0;
		
		while(node.hasParent())
		{
			node = node.getParent();
			depth++;
		}
		
		return depth;
	}	

	public int getValueCount(T value) {
		Node <T> node = find(value);
		int count = 0;
		
		if(node != null)
		{
			++count;
			while(node.getCenterChild() != null)
			{
				++count;
				node = node.getCenterChild();
			}
		}
		
		return count;
	}

	public T getHighestValue() 
	{
		Node <T> node = root;
		
		while(node != null)
		{
			if(node.hasLeftChild())
			{
				node = node.getLeftChild();
			}
			else
			{
				return node.getValue();
			}
		}
		
		return root.getValue();
	}
	
	public T getLowestValue()
	{
		Node <T> node = root;
		
		while(node != null)
		{
			if(node.hasRightChild())
			{
				node = node.getRightChild();
			}
			else
			{
				return node.getValue();
			}
		}
		
		return root.getValue();
	}
	
	public String printTree(TransverseType transverse)
	{
		StringBuilder builder = new StringBuilder();
		List <T> list = null;
		
		switch(transverse)
		{
			case IN_ORDER :
			{
				list = this.transverseInOrder();
				break;
			}
			case PRE_ORDER :
			{
				list = this.transversePreOrder();
				break;
			}
			case POST_ORDER :
			{
				list = this.transversePostOrder();
				break;
			}
		}
		
		builder.append("{");
				
		for(T value : list)
		{
			builder.append(value).append(",");
		}
		
		builder.append("}");
		
		return builder.toString();
	}

}
