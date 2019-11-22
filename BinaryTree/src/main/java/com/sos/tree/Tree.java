package com.sos.tree;

import java.util.List;

public interface Tree <T>
{
	public Node <T> getRoot();
	public char add(T node);
	public Node<T> remove(T id);
	public Node<T> find(T id);
	public Node<T> findCommonAncestor(T value1, T value2);
	public List <T> transversePreOrder();
	public List <T> transverseInOrder();
	public List <T> transversePostOrder();
	public int nodeDepth(T value);
	public int getValueCount(T value);
	public T getHighestValue();
	public T getLowestValue();
	public String printTree(TransverseType transverse);
}
