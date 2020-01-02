package com.sos.tree;

import java.util.List;
import java.util.Map;

public interface Tree <K,V>
{
	public Node <K,V> getRoot();
	public boolean add(K key, V value);
	public Node<K,V> remove(K id);
	public Node<K,V> find(K id);
	public Node<K,V> findCommonAncestor(K key1, K key2);
	public List <Map.Entry<K,V>> transversePreOrder();
	public List <Map.Entry<K,V>> transverseInOrder();
	public List <Map.Entry<K,V>> transversePostOrder();
	public int nodeDepth(K key);
	public K getHighestKeyValue();
	public K getLowestKeyValue();
	public String printTree(TransverseType transverse);
}
