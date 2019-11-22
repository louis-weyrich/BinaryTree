package com.sos.tree.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author louisweyrich
 *
 */
public class TrieTree 
{
	private TrieNode rootNode;
	private int minimumLength = 0;

	/**
	 * 
	 * @param minimumLength
	 */
	public TrieTree(int minimumLength) 
	{
		this.rootNode = new TrieNode(' ', false);
		this.minimumLength = minimumLength;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMinimumLength() {
		return minimumLength;
	}

	/**
	 * 
	 * @param minimumLength
	 */
	public void setMinimumLength(int minimumLength) {
		this.minimumLength = minimumLength;
	}

	/**
	 * 
	 * @param term
	 */
	public void addTerm(String term)
	{
		
		if(term == null) return;
		
		if(containsWord(term)) return;
		
		TrieNode node = rootNode;
		node.incrementCount();
		
		for(int index = 0; index < term.length(); index++)
		{
			Character c = Character.toLowerCase(term.charAt(index));
			if(node.containsChildNode(c))
			{
				node = node.getChild(c);
			}
			else
			{
				TrieNode child = new TrieNode(c, index + 1 == term.length());
				node.addChild(c, child);
				node = child;
			}
			
			if(term.length() == index +1)
			{
				node.setWordEnd(true);
			}
			
			node.incrementCount();
		}
	}
	
	/**
	 * 
	 * @param match
	 * @return
	 */
	public List <String> matchWords(String match)
	{
		if(match == null || match.length() < minimumLength) return new ArrayList <String> (0);
		
		List <String> list = new LinkedList <String> ();
		TrieNode node = this.rootNode;
		
		if(!match.equals(""))
		{
			for(int index = 0; index < match.length(); index++)
			{
				Character c = Character.toLowerCase(match.charAt(index));
				if(node.containsChildNode(c))
				{
					TrieNode childNode = node.getChild(c);				
					node = childNode;
				}
				else
				{
					return list;
				}
			}
		}
		
		if(!node.isLeaf())
		{
			list = matchWords(node, list);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private List <String> matchWords(TrieNode node, List <String> list)
	{
		if(node.isWord())
		{
			list.add(node.getWord());
		}
		
		if(!node.isLeaf())
		{
			for(TrieNode childNode : node.getChildren().values())
			{
				list = matchWords(childNode, list);
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public boolean containsWord(String word)
	{
		TrieNode node = this.rootNode;
		
		for(int index = 0; index < word.length(); index++)
		{
			Character c = Character.toLowerCase(word.charAt(index));
			
			if(node.containsChildNode(c))
			{
				node = node.getChild(c);
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	public void clear()
	{
		this.rootNode.clear();
	}

	/**
	 * 
	 * @param term
	 * @return
	 */
	public TrieNode getNode(String term)
	{
		if(term.equals("")) return this.rootNode;
		
		TrieNode node = this.rootNode;
		
		for(int index = 0; index < term.length(); index++)
		{
			Character c = Character.toLowerCase(term.charAt(index));
			
			if(node.containsChildNode(c))
			{
				node = node.getChild(c);
			}
			else
			{
				return null;
			}
		}
		
		return node;
	}
}
