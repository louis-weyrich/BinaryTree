/**
 * 
 */
package com.sos.tree.binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sos.tree.KeyValue;
import com.sos.tree.Node;
import com.sos.tree.TransverseType;
import com.sos.tree.Tree;

/**
 * @author louis.weyrich
 *
 */
public class BinaryTree<K,V> implements Tree<K,V>
{
	
	private Node <K,V> root;

	
	/**
	 * 
	 */
	public BinaryTree()
	{
		// Do Nothing
	}
	
	
	/**
	 * 
	 * @param rootNode
	 */
	public BinaryTree(Node<K,V> rootNode)
	{
		this.root = rootNode;
	}
	
	
	/**
	 * 
	 */
	public Node <K,V> getRoot()
	{
		return this.root;
	}

	
	/**
	 * 
	 */
	public boolean add(K key, V value)
	{
		if(this.root == null)
		{
			this.root = new Node <K,V>(key, null);
			root.setValue(value);
			return true;
		}
		else
		{
			Node <K,V> tempNode = this.root;
			int result = -1;
			
			while(tempNode != null && result != 0)
			{
				result = tempNode.compareTo(key);
				
				if(result == 0)
				{
					tempNode.setValue(value);
					return false;
				}
				else if(result == 1)
				{
					if(tempNode.getRightChild() == null)
					{
						Node <K,V> newNode = new Node <K,V> (key, value);
						tempNode.setRightChild(newNode);
						return true;
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
						Node <K,V> newNode = new Node <K,V> (key, value);
						tempNode.setLeftChild(newNode);
						return true;
					}
					else
					{
						tempNode = tempNode.getLeftChild();
					}
				}
				
			}
			
			return false;
		}
	}

	
	/**
	 * 
	 */
	public Node<K,V> remove(K value)
	{
		Node <K,V> nodeFound = find(value);
		if(nodeFound.isroot())
		{
			Node <K,V> rightChild = nodeFound.getRightChild();
			Node <K,V> leftChild = nodeFound.getLeftChild();
			
			if(leftChild != null)
			{
				Node <K,V> leftChildsRight = leftChild.getRightChild();
				
				while(leftChildsRight.getRightChild() != null)
				{
					leftChildsRight = leftChildsRight.getRightChild();
				}
				
				leftChildsRight.setParent(null);
				this.root = leftChildsRight;
				Node <K,V> leftchildsRightLeft = leftChildsRight.getLeftChild();
			
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
			Node <K,V> nodeParent = nodeFound.getParent();
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
			Node <K,V> nodeParent = nodeFound.getParent();
			Node <K,V> nodeLeft = nodeFound.getLeftChild();
			if(nodeFound.getDirection() == 'L')
			{
				nodeParent.setLeftChild(nodeLeft);
			}
			else
			{
				nodeParent.setRightChild(nodeLeft);
			}
		}		
		// if no left child then promote the right and re-point it to the removed left
		else if(!nodeFound.isLeaf() && nodeFound.getLeftChild() == null && nodeFound.getRightChild() != null)
		{
			Node <K,V> nodeParent = nodeFound.getParent();
			Node <K,V> rightChild = nodeFound.getRightChild();
			// if has a right child that has a left child then replace with the right childs left child
			if(rightChild.getLeftChild() != null)
			{
				Node <K,V> leftChild = rightChild.getLeftChild();
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

	
	/**
	 * 
	 */
	public Node<K,V> find(K value)
	{
		boolean found = false;
		Node <K,V> node = this.root;
		
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

	
	/**
	 * 
	 */
	public Node<K,V> findCommonAncestor(K value1, K value2)
	{
		List <Node <K,V>> list1 = new ArrayList <Node<K,V>> ();
		List <Node <K,V>> list2 = new ArrayList <Node<K,V>> ();
		
		Node<K,V> node1 = find(value1);
		Node<K,V> node2 = find(value2);
		
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
		
		for(Node<K,V> n1 : list1)
		{
			for(Node<K,V> n2 : list2)
			{
				if(n1.equals(n2))
				{
					return n2;
				}
			}
		}
		
		return null;
	}

	
	/**
	 * 
	 */
	public void clear()
	{
		List <KeyValue<K,V>> list = transverseInOrder();
		Iterator <KeyValue<K,V>> iterator = list.iterator();
		while(iterator.hasNext())
		{
			KeyValue<K,V> kv = iterator.next();
			remove(kv.getKey());
		}
		
		root = null;
	}
	
	
	/**
	 * 
	 */
	public List<KeyValue<K,V>> transversePreOrder()
	{
		return nextPreOrderNode(getRoot(), new ArrayList <KeyValue<K,V>> ());
	}
	
	
	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private List<KeyValue<K,V>> nextPreOrderNode(Node<K,V> node, List <KeyValue<K,V>> list)
	{
		if(node != null)
		{
			list.add(new KeyValue <K,V> (node));
			nextPreOrderNode(node.getLeftChild(), list);
			nextPreOrderNode(node.getRightChild(), list);
		}
		return list;
	}

	
	/**
	 * 
	 */
	public List<KeyValue<K,V>> transverseInOrder()
	{
		return nextInOrderNode(getRoot(), new ArrayList <KeyValue<K,V>> ());
	}
	
	
	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private List<KeyValue<K,V>> nextInOrderNode(Node<K,V> node, List <KeyValue<K,V>> list)
	{
		if(node != null)
		{
			nextInOrderNode(node.getLeftChild(), list);
			list.add(new KeyValue <K,V> (node));
			nextInOrderNode(node.getRightChild(), list);
		}
		
		return list;
	}


	/**
	 * 
	 */
	public List<KeyValue<K,V>> transversePostOrder()
	{
		return nextPostOrderNode(getRoot(), new ArrayList <KeyValue<K,V>> ());
	}
	
	
	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private List<KeyValue<K,V>> nextPostOrderNode(Node<K,V> node, List<KeyValue<K,V>> list)
	{
		if(node != null)
		{
			nextPostOrderNode(node.getLeftChild(), list);
			nextPostOrderNode(node.getRightChild(), list);
			list.add(new KeyValue <K,V> (node));
		}
		
		return list;
	}

	
	/**
	 * 
	 */
	public int nodeDepth(K value) {
		Node <K,V> node = find(value);
		int depth = 0;
		
		while(node.hasParent())
		{
			node = node.getParent();
			depth++;
		}
		
		return depth;
	}	

	
	/**
	 * 
	 */
	public K getHighestKeyValue() 
	{
		Node <K,V> node = root;
		
		while(node != null)
		{
			if(node.hasLeftChild())
			{
				node = node.getLeftChild();
			}
			else
			{
				return node.getKey();
			}
		}
		
		return root.getKey();
	}
	
	
	/**
	 * 
	 */
	public K getLowestKeyValue()
	{
		Node <K,V> node = root;
		
		while(node != null)
		{
			if(node.hasRightChild())
			{
				node = node.getRightChild();
			}
			else
			{
				return node.getKey();
			}
		}
		
		return root.getKey();
	}
	
	
	/**
	 * 
	 */
	public String printTree(TransverseType transverse)
	{
		StringBuilder builder = new StringBuilder();
		List <KeyValue<K,V>> list = null;
		
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
		
		builder.append("{\n\tList:\n\t[\n");
				
		for(KeyValue <K,V> keyValue : list)
		{
			builder.append("\t\t\"").append(keyValue.getKey()).append("\":\"").append(keyValue.getValue()).append("\",\n");
		}
		
		builder.append("\t]\n}");
		
		return builder.toString();
	}

}
