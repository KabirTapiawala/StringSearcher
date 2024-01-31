public class BST < E extends Comparable < E >>
{
  protected TreeNode < E > root;
  protected int size = 0;

  public BST ()
  {
  }

  public BST (E[]objects)
  {
  for (E object:objects)
      {
	// insert(object);
      }
  }

  public boolean search (E e)
  {
      System.out.printf("\nSearching for element: \n", e);
    TreeNode < E > current = root;

    while (current != null)
      {
	if (e.compareTo (current.element) < 0)
	  {
	    current = current.left;
	  }
	else if (e.compareTo (current.element) > 0)
	  {
	    current = current.right;
	  }
	else
	  {
	      print(current);
	    return true;
	  }
      }
      System.out.printf("Keyword %s does not exist in the information database! ", e);
    return false;
  }

  public boolean insert (E e, Article a)
  {
    if (root == null)
      {
	root = createNewNode (e);
	root.addFirst (a);
      }
    else
      {
	TreeNode < E > parent = null;
	TreeNode < E > current = root;
	while (current != null)
	  {
	    parent = current;
	    if (e.compareTo (current.element) < 0)
	      {
		current = current.left;
	      }
	    else if (e.compareTo (current.element) > 0)
	      {
		current = current.right;
	      }
	    else
	      {
		current.addFirst (a);
		return false;
	      }
	  }
	if (e.compareTo (parent.element) < 0)
	  {
	    parent.left = createNewNode (e);
	    parent.left.addFirst (a);	//added
	  }
	else
	  {
	    parent.right = createNewNode (e);
	    parent.right.addFirst (a);	//added
	  }
      }
    size++;
    return true;
  }

  public boolean delete (E e)
  {
    // Basic implementation of delete method
    // This method needs to be properly implemented
    return false;
  }

  protected TreeNode < E > createNewNode (E e)
  {
    return new TreeNode <> (e);
  }

  public void inorder ()
  {
    inorder (root);
  }

  protected void inorder (TreeNode < E > root)
  {
    if (root == null)
      return;
    inorder (root.left);
    System.out.print (root.element + " ");
    Node < Article > artRec = root.head;
    System.out.println ("\t");
    while (artRec != null)
      {
	System.out.printf ("%s", artRec);
	artRec = artRec.next;
      }
    System.out.println ();
    inorder (root.right);
  }

  protected void print (TreeNode < E > root)
  {
    if (root == null)
      return;
    System.out.print (root.element + " ");
    Node < Article > artRec = root.head;
    System.out.println ("\t");
    while (artRec != null)
      {
	System.out.printf ("%s", artRec);
	artRec = artRec.next;
      }
    System.out.println ();
  }

  public void postorder ()
  {
    postorder (root);
  }

  protected void postorder (TreeNode < E > root)
  {
    if (root == null)
      return;
    postorder (root.left);
    postorder (root.right);
    System.out.print (root.element + " ");
  }

  public int getSize ()
  {
    return size;
  }

  public TreeNode < E > getRoot ()
  {
    return root;
  }

  public java.util.ArrayList < TreeNode < E >> path (E e)
  {
    java.util.ArrayList < TreeNode < E >> list =
      new java.util.ArrayList <> ();
    TreeNode < E > current = root;
    while (current != null)
      {
	list.add (current);
	if (e.compareTo (current.element) < 0)
	  {
	    current = current.left;
	  }
	else if (e.compareTo (current.element) > 0)
	  {
	    current = current.right;
	  }
	else
	  {
	    break;
	  }
      }
    return list;
  }

  public java.util.Iterator < E > iterator ()
  {
    return new InOrderIterator ();
  }

  private class InOrderIterator implements java.util.Iterator < E >
  {
    private java.util.ArrayList < E > list = new java.util.ArrayList <> ();
    private int current = 0;

    public InOrderIterator ()
    {
      inorder (root);
    }

    private void inorder (TreeNode < E > root)
    {
      if (root == null)
	return;
      inorder (root.left);
      list.add (root.element);
      inorder (root.right);
    }

    @Override public boolean hasNext ()
    {
      return current < list.size ();
    }

    @Override public E next ()
    {
      return list.get (current++);
    }

    @Override public void remove ()
    {
      delete (list.get (current));	// Now correctly references BST's delete method
      list.clear ();
      inorder (root);
    }
  }

  public void clear ()
  {
    root = null;
    size = 0;
  }
}