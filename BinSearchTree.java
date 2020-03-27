import java.lang.Comparable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinSearchTree<E extends Comparable<E>>
          extends BinTree<E> implements Iterable<E> {

  /** findPos returns position where node with element e,
      or the position where node for e should be inserted,
      Returns null for an empty tree */
  protected BinTreePos<E> findPos(E e) {
    if ( isEmpty() )
      return null;
    BinTreePos<E> lt, rt, pos = root();
    // Put code here to determine position containing e, or the
    // position where a node for e should be inserted.

    return pos;
  }

  /** add inserts a node with entry e
      provided e is not already in the tree*/
  public void add(E e) {
    if ( isEmpty() )
      setRoot(new BinTreeNode<E>(e,null,null,null));
    else {
      BinTreePos<E> pos = findPos(e);
      int comparison = e.compareTo(pos.element());
      if ( comparison == 0 ) // found e in tree!
        return;
      else if ( comparison < 0 )
        pos.setLeft(new BinTreeNode<E>(e,pos,null,null));
      else // comparison > 0
        pos.setRight(new BinTreeNode<E>(e,pos,null,null));
    }
  }

  /** iterator returns an iterator over the elements of the nodes
      of this tree */
  public Iterator<E> iterator() {
    Iterator<E> it = new Iterator<E>() {
      private BinTreePos<E> pos = root();
      private boolean atStart = true;

      public boolean hasNext() {
        return pos != null;
      }

      public E next() {
        // invariant: all to the left of pos is already done
        if ( pos == null )
          throw(new NoSuchElementException("BinSearchTree in-order traversal"));
        if ( atStart ) {
          // move to left-most position before starting
          while ( pos.left() != null )
            pos = pos.left();
          atStart = false;
        }
        E elt = pos.element();
        if ( pos.right() != null )
        {
          pos = pos.right();
          while ( pos.left() != null )
            pos = pos.left();
        }
        else
        {
          while ( pos.parent() != null && pos == pos.parent().right() )
            pos = pos.parent();
          if ( pos.parent() == null || pos.parent() == pos ) // at root
            pos = null;
          else
            pos = pos.parent();
        }
        return elt;
      }
    };
    return it;
  }

  /** main() -- a simple test */
  public static void main(String[] args) {
    BinSearchTree<String> bst = new BinSearchTree<String>();
    String[] words = { "grape", "cranberry", "kumkwat", "vanilla",
              "apple", "walnut", "elderberry", "blackberry" };
    for ( String s : words ) {
      System.out.println("Adding word: "+s);
      bst.add(s);
    }
    System.out.println();
    System.out.println(bst);

    System.out.println("Words in order:");
    for ( String w : bst )
      System.out.print(w + ", ");
    System.out.println("That's all folks!");
  }
}
