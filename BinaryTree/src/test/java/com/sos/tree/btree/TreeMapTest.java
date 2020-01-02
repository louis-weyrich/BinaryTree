package com.sos.tree.btree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sos.tree.map.TreeMap;
import com.sos.tree.util.RandomUtil;

public class TreeMapTest
{

	@Test
	public void testTreeMapInt()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer value = rand.generateRandom();
			Integer returnValue = tree.put(key,value);
			if(returnValue != null)
			{
				testKey = key;
			}
		}
		
		assertTrue(tree.containsKey(testKey));
		Integer value = tree.get(testKey);
		assertNotNull(value);
		
	}

	@Test
	public void testSize()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer value = rand.generateRandom();
			Integer returnValue = tree.put(key,value);
			if(returnValue != null)
			{
				size--;
			}
		}
		
		assertTrue(tree.size() == size);
		assertTrue(tree.keySet().size() == size);
	}

	@Test
	public void testIsEmpty()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer value = rand.generateRandom();
			Integer returnValue = tree.put(key,value);
			if(returnValue != null)
			{
				size--;
			}
		}
		
		assertFalse(tree.isEmpty());

	}

	@Test
	public void testContainsKey()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
			}
		}
		
		assertTrue(tree.containsKey(testKey));
		Integer value = tree.get(testKey);
		assertTrue(value != null);
		assertTrue(value.equals(testKey));
	}

	@Test
	public void testContainsValue()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
			}
		}
		
		assertTrue(tree.containsValue(testKey));
		assertTrue(tree.values().stream().findAny().of(testKey).isPresent());
	}

	@Test
	public void testGet()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
			}
		}
		
		Integer returnValue = tree.get(testKey);
		assertNotNull(returnValue);
		assertTrue(returnValue.equals(testKey));
	}

	@Test
	public void testRemove()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
				size--;
			}
		}
		
		assertTrue(tree.size() == size);
		Integer returnValue = tree.remove(testKey);
		assertNotNull(returnValue);
		assertTrue(tree.size() == size - 1);
	}

	@Test
	public void testPutAll()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 400;
		
		for(int x = 0; x < 4; x++)
		{
			Map <Integer,Integer> map = new HashMap <Integer,Integer> ();
			
			for(int index = 0; index < 100; index++)
			{
				Integer key = rand.generateRandom();
				Integer returnValue = map.put(key,key);
				if(returnValue != null)
				{
					testKey = key;
					size--;
				}
			}
			
			tree.putAll(map);
		}
		
		assertTrue(tree.containsKey(testKey));

	}

	@Test
	public void testClear()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
				size--;
			}
		}
		
		assertTrue(tree.size() == size);
		tree.clear();
		assertTrue(tree.size() == 0);
		assertTrue(tree.isEmpty());
		assertFalse(tree.containsKey(testKey));
	}

	@Test
	public void testKeySet()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
				size--;
			}
		}
		
		assertTrue(tree.containsKey(testKey));
		assertTrue(tree.keySet().contains(testKey));

	}

	@Test
	public void testValues()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
				size--;
			}
		}
		
		assertTrue(tree.values().contains(testKey));
	}

	@Test
	public void testEntrySet()
	{
		RandomUtil rand = new RandomUtil(0, 99);
		TreeMap <Integer, Integer> tree = new TreeMap <Integer, Integer> ();
		Integer testKey = null;
		int size = 100;
		
		for(int index = 0; index < 100; index++)
		{
			Integer key = rand.generateRandom();
			Integer returnValue = tree.put(key,key);
			if(returnValue != null)
			{
				testKey = key;
				size--;
			}
		}
	}

}
