/**
 * 
 */
package com.sos.tree.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.sos.tree.KeyValue;
import com.sos.tree.Node;
import com.sos.tree.binarytree.BinaryTree;

/**
 * @author louisweyrich
 *
 */
public class TreeMap<K, V> implements Map<K, V>
{
	private static final int DEFAULT_ARRAY_SIZE = 20; 
	private int size = 0, arraySize = DEFAULT_ARRAY_SIZE;
	private BinaryTree <K,V> [] data;
	private Comparator <K> keyComparator;
	
	

	/**
	 * 
	 */
	public TreeMap()
	{
		this(DEFAULT_ARRAY_SIZE);
	}

	/**
	 * 
	 */
	public TreeMap(int initialArraySize)
	{
		this.arraySize = initialArraySize;
		this.keyComparator = this.defaultComparator();
		this.data = (BinaryTree<K,V>[])new Object [arraySize];
	}

	/**
	 * 
	 */
	public TreeMap(int initialArraySize, Comparator <K> keyComparator)
	{
		this(initialArraySize);
		this.keyComparator = keyComparator;
	}
	
	
	/**
	 * 
	 * @return
	 */
	protected Comparator<K> defaultComparator()
	{
		return new Comparator <K> ()
		{
			public int compare(K o1, K o2) {
				int value = 0;
				
				if (o1.hashCode() == o2.hashCode() && o1.equals(o2)) {
					value = 0;
				}
				else if(o1.hashCode() > o2.hashCode())
				{
					value = 1;
				}
				else if(o1.hashCode() < o2.hashCode())
				{
					value = -1;
				}
				return value;
			}
		};
	}
	
	/**
	 * 
	 * @param src
	 * @return
	 */
	protected int calculateHash(Object src)
	{
		return Math.abs(src.hashCode()) % this.arraySize;
	}

	/**
	 * 
	 */
	public int size()
	{
		return size;
	}

	/**
	 * 
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * 
	 */
	public boolean containsKey(Object key)
	{
		if(key != null)
		{
			int hash = calculateHash(key);
			
			if(hash >= 0 && hash < arraySize)
			{
				BinaryTree <K,V> tree = data[hash];
				if(tree != null)
				{
					Node <K,V> node = tree.find((K)key);
					if(node != null && node.getKey().equals(key))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * 
	 */
	public boolean containsValue(Object value)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	public V get(Object key)
	{
		if(key != null)
		{
			int hash = calculateHash(key);
			
			if(hash >= 0 && hash < arraySize)
			{
				BinaryTree <K,V> tree = data[hash];
				if(tree != null)
				{
					Node <K,V> node = tree.find((K)key);
					if(node != null && node.getKey().equals(key))
					{
						return node.getValue();
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 */
	public V put(K key, V value)
	{
		if(key != null && value != null)
		{
			int hash = calculateHash(key);
			
			if(hash >= 0 && hash < arraySize)
			{
				BinaryTree <K,V> tree = data[hash];
				if(tree != null)
				{
					Node <K,V> node = tree.find(key);
					tree.add(key, value);
					if(node != null)
					{
						return node.getValue();
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 */
	public V remove(Object key)
	{
		if(key != null)
		{
			int hash = calculateHash(key);
			
			if(hash >= 0 && hash < arraySize)
			{
				BinaryTree <K,V> tree = data[hash];
				if(tree != null)
				{
					Node <K,V> node =  tree.remove((K)key);
					if(node != null)
					{
						return node.getValue();
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 */
	public void putAll(Map<? extends K, ? extends V> map)
	{
		if(map != null)
		{
			Set <K> set = (Set<K>)map.keySet();
			Iterator <K> iterator = set.iterator();
			while(iterator.hasNext())
			{
				K key = iterator.next();
				V value = map.get(key);
				this.put(key, value);
			}
		}

	}

	/**
	 * 
	 */
	public void clear()
	{
		this.size = 0;
		for(BinaryTree<K,V> tree : data)
		{
			tree.clear();
		}
		
		data = (BinaryTree<K,V>[])new Object [arraySize];
	}

	/**
	 * 
	 */
	public Set<K> keySet()
	{
		Set <K> returnSet = new HashSet <K> ();
		
		for(BinaryTree<K,V> tree : data)
		{
			 List <KeyValue<K,V>> list = tree.transverseInOrder();
			 returnSet.addAll(
				list.stream().map(KeyValue::getKey).collect(Collectors.toList()));
		}
		
		return returnSet;
	}

	/**
	 * 
	 */
	public Collection<V> values()
	{
		List <V> returnList = new ArrayList <V> ();
		
		for(BinaryTree<K,V> tree : data)
		{
			List <KeyValue<K,V>> list = tree.transverseInOrder();
			returnList.addAll(
				list.stream().map(KeyValue::getValue).collect(Collectors.toList()));
		}
		
		return returnList;
	}

	
	/**
	 * 
	 */
	public Set<Entry<K, V>> entrySet()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
