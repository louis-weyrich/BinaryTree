package com.sos.tree.btree;

import org.junit.Test;

import com.sos.tree.Node;
import com.sos.tree.TransverseType;
import com.sos.tree.Tree;
import com.sos.tree.binarytree.BinaryTree;
import com.sos.tree.util.RandomUtil;

/**
 * Unit test for simple App.
 */
public class BinaryTreeTest
{
    
	@Test
	public void testTree()
	{
		Tree <Integer> tree = new BinaryTree <Integer> ();
		RandomUtil rand = new RandomUtil(10, 99);
		System.out.print("Input:      {");
		for(int index = 0; index < 40; index++)
		{
			Integer value = rand.generateRandom();
			char dir = tree.add(value);
			//System.out.print(value+(new Character(dir).toString())+",");
			System.out.print(value+",");
		}
		System.out.println("}\n\n");
		
		System.out.println("PRE_ORDER:  "+tree.printTree(TransverseType.PRE_ORDER)+"\n\n");
		System.out.println("IN_ORDER:   "+tree.printTree(TransverseType.IN_ORDER)+"\n\n");
		System.out.println("POST_ORDER: "+tree.printTree(TransverseType.POST_ORDER)+"\n\n");
		
		System.out.println("Highest Value = "+tree.getHighestValue());
		System.out.println("Lowest Value = "+tree.getLowestValue());
		System.out.println("Highest Depth = "+tree.nodeDepth(tree.getHighestValue()));
		System.out.println("Lowest Depth = "+tree.nodeDepth(tree.getLowestValue()));

		Node <Integer> commonNode = tree.findCommonAncestor(tree.getHighestValue(), tree.getLowestValue());
		
		if(commonNode != null)
			System.out.println("CommonNode = "+commonNode.getValue());
	}
}
