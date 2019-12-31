package com.sos.tree;

public class KeyValue<K, V> {

	private K key;
	private V value;

	
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	public KeyValue(Node<K,V> node)
	{
		setKey(node.getKey());
		setValue(node.getValue());
	}


	public K getKey() {
		return key;
	}


	public void setKey(K key) {
		this.key = key;
	}


	public V getValue() {
		return value;
	}


	public void setValue(V value) {
		this.value = value;
	}

}
