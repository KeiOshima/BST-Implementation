import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This class represents an implementation of a binary search tree.
 * With the elements being ordered using their natural ordering.
 * This class implements many of the methods provided by the java framework's
 * Treeset class.
 * 
 * @author Kei Oshima.
 */

public class BST<E extends Comparable<E>> implements Iterable<E> {

    // private node class
    private class BSTNode<E extends Comparable<E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        // node constructor
        public BSTNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;

        }

        // node constructor that takes a left and right node as well as an element.
        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

    // variables needed through the program.
    private BSTNode<E> root = null;
    private int size = 0;

    /**
     * constructs a new empty tree sorted according to it's natural ordering of its
     * elements
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * BST constructor that takes in a collection and creates a new tree
     * Containing the elements in the specified collection sorted according to it's
     * natural ordering of elements.
     * 
     * @throws NullPointerException if the given collection is null
     * @param collection
     */
    public void BST​(E[] collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Adds the specified element to the set if it is not already present.
     * If element is present than we do not add it
     * Containing the elements in the specified collection sorted according to it's
     * natural ordering of elements.
     * 
     * @throws NullPointerException if the given collection is null
     * @param element to be added to this set.
     * @return true if the element was not already present, false otherwise.
     */
    public boolean add​(E e) {

        // if e is null throw exception
        if (e == null) {
            throw new NullPointerException();
        }

        // if the root is null we make the element as the root.
        if (root == null) {
            size++;
            root = new BSTNode<E>(e);
            return true;

        }
        // call recursive function.
        return addrec(e, root);

    }

    /**
     * recursive helper method that helps to actually add the node to the tree.
     * 
     * @throws NullPointerException if the given collection is null
     * @param collection containing the elements to be added to the tree
     * @return true if the tree was changed as a result of the call
     */
    private boolean addrec(E e, BSTNode<E> node) {

        // if the element is already in the tree we don't change and return false.
        if (e.compareTo(node.data) == 0) {
            return false;
        }

        // here we determine where to add the new value.
        if (e.compareTo(node.data) < 0) {
            // node.left is null we create a new node with the given value.
            if (node.left == null) {
                size++;
                node.left = new BSTNode<E>((e));
                return true;

            }
            // add to left subtree.
            return addrec(e, node.left);
        }

        if (e.compareTo(node.data) > 0) {
            // if node.right is null we create a new right node with the given value.
            if (node.right == null) {
                size++;
                node.right = new BSTNode<E>((e));
                return true;

            } // add to right subtree.
            return addrec(e, node.right);

        }

        // if we have duplicate we return false and leave the tree unchanged.
        return false;
    }

    /**
     * Adds all the elements of the specified collection to the tree.
     * 
     * @param collection
     * @throws NullPointerException - if the collection is null or if any element in
     *                              the collection is null.
     * @return true if this set was changed as a result of the call.
     */
    public boolean addAll​(Collection<? extends E> collection) {
        // check if the given collection is null.
        if (collection == null) {
            throw new NullPointerException();

        }
        // iterate through the collection
        for (E data : collection) {
            // if an element in the collection is null we throw an exception
            if (data == null) {
                throw new NullPointerException();
            }
            // add the data.
            add​(data);
        }
        return true;
    }

    /**
     * Removes the specific element from the tree if it is present.
     * 
     * @param o-object that is to be removed from this set if it is present.
     * @throws NullPointerException - if the specified element is null.
     * @throws ClassCastException   - if the specified object cannot be compared
     *                              with the elements in the tree.
     * @return true if the tree contained the specific element.
     */
    public boolean remove(Object o) {

        // check if the given object is null
        if (o == null) {
            throw new NullPointerException();
        }

        try {
            // if the element we inputed is not in our tree we return false.
            if (!(this.contains​(o))) {
                return false;
            }
            // get the recursive method.
            root = removeRec(o, root);
            // change size of tree
            size--;
            return true;

        }
        // catch if the element inputed cannot be compared with the element in the tree.
        catch (ClassCastException e) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive helper method that helps to find and eventually remove the node and
     * return the reference to it.
     * 
     * @param o    the object we want to remove from the tree.
     * @param node form where the recursive call was made.
     */
    private BSTNode<E> removeRec(Object o, BSTNode<E> node) {

        // value was not found
        if (node == null) {

            return null;
        }
        // casting so we can actually compare the elements.
        E date = (E) o;

        // comparing the value our the node we are at to the node we want to
        if (node.data.compareTo(date) > 0) {

            node.left = removeRec(date, node.left);
        }

        else if (node.data.compareTo(date) < 0) {
            node.right = removeRec(date, node.right);
        }
        // if we found the node we want to remove we call our recursive remove method.
        else {
            node = removenode(node);

        }

        return node;
    }

    /**
     * recursive method that actually helps to remove the element
     * 
     * @param node to be removed from this tree.
     * @return the node we removed.
     */
    private BSTNode<E> removenode(BSTNode<E> node) {
        E data;
        if (node.left == null && node.right == null) {
            return null;
        }
        // call to handle the leaf and one child node with a right subtree.
        if (node.left == null) {
            return node.right;
        }
        // call to handle the leaf and one child node with a left subtree.
        else if (node.right == null) {
            return node.left;
        }
        // if we have two children first we get the predecessor.
        else {
            // call predecessor method.
            data = getPredecessor(node.left);
            node.data = data;
            node.left = removeRec(data, node.left);
            return node;
        }

    }

    /**
     * method that returns the information held in the very right most tree.
     */
    private E getPredecessor(BSTNode<E> current) {
        // throw if we somehow get a null value.
        if (current == null) {
            throw new NullPointerException();

        }
        BSTNode<E> node = current;
        while (node.right != null) {
            node = node.right;
        }

        return node.data;
    }

    /**
     * method the should return true if the set contains the specified element.
     * More formally returns true if and only if the set contains the element e such
     * that Objects.equals(o, e).
     * 
     * @param o- object to be checked for containment in this set.
     * @throws ClassCastException   - if the specified object cannot be compared
     *                              with the elements in the tree.
     * @throws NullPointerException - if the specified element is null and this set
     *                              uses natural ordering, or the comparator does
     *                              not permit null elements
     * @return true if this set does contain the specified element.
     */
    public boolean contains​(Object o) {

        // if object is nulls
        if (o == null) {
            throw new NullPointerException();

        }

        try {
            // cast in order to use for recursive method.
            E date = (E) o;
            return containsRec(this.root, date);

        } catch (ClassCastException e) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive method that checks to see if the tree contains the given object.
     * 
     * @param current- node we are currently at.
     * @param date     - object that we want to check if it's in the tree.
     * @return
     */
    private boolean containsRec(BSTNode<E> current, E date) {

        // if node is null
        if (current == null) {
            return false;
        }

        // if the node we are currently at equal the object return true.
        if (current.data.compareTo(date) == 0) {
            return true;
        }

        // if the node is greater than the data we are looking for go to the left
        // subtree.
        if (current.data.compareTo(date) > 0) {
            return containsRec(current.left, date);
        }
        // if the node is less than the data we are looking for go to the right subtree.
        if (current.data.compareTo(date) < 0) {
            return containsRec(current.right, date);
        }
        // return false if we did not get the value.
        return false;

    }

    /**
     * method that checks if this collection contains all of the elements in the
     * specified collection.
     * iterates over the collection and check each element returned by the iterator
     * 
     * @param c- collection to be checked for containment in this tree.
     * @throws NullPointerException if the specified collection contains one or more
     *                              null elements or if the specified collection is
     *                              null.
     * @return true if the tree does contain all of the elements in the specified
     *         tree false otherwise..
     */
    public boolean containsAll​(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        // iterate through the collections
        for (Object x : c) {
            // check if elements in the collection is null
            if (x == null) {
                throw new NullPointerException();
            }

            if (this.contains​(x)) {
                continue;
            }
            // if we found an element that is not in the tree from the collection return
            // false.
            else {
                return true;
            }
        }
        return true;

    }

    /**
     * method that returns the number of element in the tree.
     * 
     * @return the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * removes all the elements from the set.
     */
    public void clear() {
        size = 0;
        root = null;

    }

    /**
     * checks to see if the given set is empty or not
     * 
     * @return true if the set contains no elements.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method that checks if a given tree is a full tree
     * 
     * @return true if this tree is full
     */
    public boolean isFull() {
        // calls recursive method to check if given tree is full.
        return isFullRec(root);
    }

    /**
     * recursive method that returns if a tree is full or not.
     * 
     * @param current node
     * @return true if tree is full else fall.
     */
    private boolean isFullRec(BSTNode<E> current) {
        // if the tree is empty we return true;
        if (current == null) {
            return true;
        }
        // if both the left and right node are null we return true.
        if (current.left == null && current.right == null) {
            return true;
        }
        // if both node.left and node.rigth are are not null we go to the left adn rigth
        // subtree.
        if (current.left != null && current.right != null) {
            return (isFullRec(current.left) && isFullRec(current.right));
        }
        // else we return false.
        else {
            return false;
        }

    }

    /**
     * method that returns the height of the tree.
     * 
     * @return the height of the tree or 0 if the tree is empty.
     */
    public int height() {
        // recursively calls the height method.
        return GetHeight(root);
    }

    /**
     * recursive method that helps to return the height of the tree.
     * 
     * @param current- the root node.
     * @return the height
     */
    private int GetHeight(BST<E>.BSTNode<E> current) {
        // if we have both right and left null we return 0;
        if (current.left == null && current.right == null) {
            return 0;

        } else if (current.left == null) {
            return GetHeight(current.right) + 1;

        }
        // if the right node is null we go to the left subtree and add 1
        else if (current.right == null) {
            return GetHeight(current.left) + 1;

        } else {
            int leftHeight = GetHeight(current.left);
            int rightHeight = GetHeight(current.right);
            return Math.max(leftHeight, rightHeight) + 1;

        }

    }

    @Override
    /**
     * method that returns an iterator over the elements in this tree in ascending
     * order
     * 
     * @return an iterator over the elements in the set in ascending order.
     */
    public Iterator<E> iterator() {
        // call the iterator method that contains both the postorder and preorder
        // iterator
        return new iteratorArray();
    }

    /**
     * method that returns an iterator over the elements in this tree in order of
     * preorder traversal.
     * 
     * @return an iterator over the elements in the tree in order of the preorder
     *         traversal.
     */
    public Iterator<E> preorderIterator() {
        // return the preorder iterator this is done by using the integer 1.
        return new iteratorArray(1);

    }

    /**
     * method that returns an iterator over the element in the tree in order of the
     * postorder traversal
     * 
     * @return an iterator over the elements in this tree in order of the postorder
     *         traversal.
     */

    public Iterator<E> postorderIterator() {
        // return the postorder iterator this is done by using the integer 2.
        return new iteratorArray(2);
    }

    /**
     * the method represents the iterator class that implements Iterable interface.
     * method contains methods next(), hasnext() and contians method for the
     * inorder, preorder, and postorder iterator.
     * we use an Arraylist as a container for the values.
     * implementation is similar to that found in the Trees.java workspace by Lauren
     * Digiovanni but instead of strings we use an arrayList.
     */
    private class iteratorArray implements Iterator<E> {

        BSTNode<E> current;
        int itercounter = 0;
        ArrayList<E> list = new ArrayList<E>();

        // method that calls the recursive method for the inorder traversal.
        public iteratorArray() {
            current = root;
            itercounter = 0;
            inorderRec(current);

        }

        // method that takes in an integer and based on the integer returns either the
        // postorder or preorder iterator.
        public iteratorArray(int i) {
            current = root;
            itercounter = 0;

            // if integer is 1 we call the recursive method that helps traverse the tree in
            // a preorder manner..
            if (i == 1) {
                preorderRec(current);

            }
            // if integer is 2 we call the recursive method that helps traverse the tree in
            // a postorder manner.
            if (i == 2) {
                postorderRec(current);
            }

        }

        @Override
        public boolean hasNext() {
            if (itercounter <= list.size() - 1) {
                return true;
            } else {
                return false;
            }

        }

        @Override
        public E next() {
            if (!hasNext()) {
                return null;
            }
            E data = list.get(itercounter);
            itercounter++;
            return data;

        }

        /*
         * method that helps to traverse the tree in a postorder manner.
         */
        public void postorderRec(BSTNode<E> current) {
            if (current == null) {
                return;

            }
            postorderRec(current.left);
            postorderRec(current.right);
            list.add(current.data);

        }

        /*
         * method that helps to traverse the tree in a preorder manner.
         */
        private void preorderRec(BSTNode<E> current) {

            if (current == null) {
                return;

            }
            list.add(current.data);
            preorderRec(current.left);
            preorderRec(current.right);
        }

        /*
         * method that helps to traverse the tree in a inorder manner.
         */
        public void inorderRec(BSTNode<E> current) {
            if (current == null) {
                return;
            }
            inorderRec(current.left);
            list.add(current.data);
            inorderRec(current.right);
        }

    }

    /**
     * method that returns the element at the specified position on the tree
     * 
     * @param index of the element to return
     * @throws IndexOutOfBoundsException - if the index < 0 || index >= 0
     * @return the elements at the specified position of the tree.
     */
    public E get​(int index) {
        int counter = 0;

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        // create a new iterator to help us traverse the tree to get the element at the
        // specified index.
        iteratorArray x = new iteratorArray();

        while (counter <= x.list.size() - 1) {
            E current = x.list.get(counter);
            // checks to see if the current element is equal to the value at the specified
            // index.
            if (current == x.list.get(index)) {
                return current;
            }
            // to help us get to the next index.
            counter++;
        }
        return null;

    }

    /**
     * method that returns a collection whose elements are in the range of
     * fromElement to toElement both being inclusive.
     * 
     * @param fromElement - low endpoint (inclusive of the returned collection.
     * @param toElement-  high endpoint (inclusive) of the returned collection.
     * @throws NullPointerException     - if fromElement or toElement is null.
     * @throws IllegalArgumentException - if fromElement is greater than toElement.
     * @return returns a collection containing a portion of the tree whose elements
     *         range fromElement to toElement inclusive
     *         I used a similar implementation found on Stackoverflow.
     *         https://stackoverflow.com/questions/46918053/binary-search-tree-filter-values-in-a-range
     *         but instead of using a Queue I used an ArrayList
     */
    public ArrayList<E> getRange​(E fromElement, E toElement) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
        }

        if (fromElement.compareTo(toElement) > 0) {
            throw new IllegalArgumentException();
        }

        // create a Arraylist to store the values we want.
        ArrayList<E> range = new ArrayList<E>();
        // call the recursive helper needed to get the values
        getRangeRec(range, root, fromElement, toElement);
        // sort the Arraylist if needed
        Collections.sort(range);
        return range;

    }

    /**
     * 
     * @param range-      that stores the values that are in range from fromElement
     *                    to toElement
     * @param current-    node we a re currently at.
     * @param fromElement lower range that is inclusive
     * @param toElement   upper range that is inclusive
     */
    private void getRangeRec(ArrayList<E> range, BSTNode<E> current, E fromElement, E toElement) {

        if (current == null) {
            return;
        }
        // if the value toElement is greater than the node we are currently at we go to
        // the right subtree.
        if (toElement.compareTo(current.data) > 0) {
            getRangeRec(range, current.right, fromElement, toElement);
        }

        // if the value we are currently at is greater than the value of fromElement we
        // go to the left subtree.
        if (fromElement.compareTo(current.data) < 0) {
            getRangeRec(range, current.left, fromElement, toElement);
        }

        // check to see if the value is in range of fromElement to toElement if it is we
        // add it to the arraylist.
        if ((toElement.compareTo(current.data) >= 0) && (fromElement.compareTo(current.data) <= 0)) {
            range.add(current.data);

        }

    }

    /**
     * method that returns the least element in this tree greater than or equal to
     * the given element. Or null if the element does not exist.
     * 
     * @param e - the value that we want to match.
     * @throws NullPointerException - if the specified element is null.
     * @throws ClassCastException   - if the specified element is unable to be
     *                              compared with the elements currently in the set
     * @return the least element that is greater than or equal, or null if there is
     *         no such element.
     */
    public E ceiling​(E e) {

        // if element is null;
        if (e == null) {
            throw new NullPointerException();
        }

        try {
            // call the recursive method to help find the value we want.
            BSTNode<E> current = ceilingRec(root, e);
            // if no value exist that can math the parameters we return null.
            if (current == null) {
                return null;
            }
            // return the value;
            return current.data;

        } catch (ClassCastException o) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive method that helps us find the value that matches the specifications
     * for the ceiling method.
     * 
     * @param current- value we are going to compare to the value we want to match.
     * @param e        the value we want to match.
     * @return the value that matches the ceilings specifications.
     */

    private BSTNode<E> ceilingRec(BSTNode<E> current, E e) {
        // if current is null.
        if (current == null) {
            return null;
        }
        // if the value we are looking for is equal to the data we can return the value.
        if (e.compareTo(current.data) == 0) {
            return current;
        }
        // if the node is bigger that the data we are looking for go to the left
        // subtree.
        if (current.data.compareTo(e) > 0) {
            BSTNode<E> x = ceilingRec(current.left, e);
            // check if the value at the left is null if it is we return the value we are
            // currently at.
            if (x == null) {
                return current;
            }
            // go to left subtree
            return x;

        }

        // else we go to the right subtree.
        return ceilingRec(current.right, e);
    }

    /**
     * method that returns the greatest element in this set less than or equal to
     * the given element or null if no such element exist.
     * 
     * @param e- the value we want to match.
     * @throws ClassCastException   - if the specified element cannot be compared
     *                              with the elements currently in the set.
     * @throws NullPointerException - if the specified element is null.
     * @return the greatest element less than or equal to the parameter, or null if
     *         no such element exist.
     */
    public E floor​(E e) {
        // if element is null;
        if (e == null) {
            throw new NullPointerException();
        }

        try {
            // call the recursive method to help find the value we want.
            BSTNode<E> current = floorRec(root, e);
            // if no value exist that can math the parameters we return null.
            if (current == null) {
                return null;
            }
            // return the value;
            return current.data;

        } catch (ClassCastException o) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive method that helps us find the value that matches the specifications
     * for the ceiling method.
     * 
     * @param current- value we are going to compare to the value we want to match.
     * @param e        the value we want to match.
     * @return the value that matches the ceilings specifications.
     */
    private BSTNode<E> floorRec(BSTNode<E> current, E e) {
        // if element is null;
        if (current == null) {
            return null;
        }

        // if the value we are looking for is equal to the data we can return the value.
        if (e.compareTo(current.data) == 0) {
            return current;
        }
        // if the node is less that the data we are looking for go to the right subtree.
        if (current.data.compareTo(e) < 0) {
            BSTNode<E> x = floorRec(current.right, e);
            // check if the value at the right is null if it is we return the value we are
            // currently at.
            if (x == null) {
                return current;
            }
            // go to right subtree
            return x;
        }

        // else we go to the left subtree.
        return floorRec(current.left, e);
    }

    /**
     * method that returns the first/lowest element currently in the tree.
     * 
     * @throws NoSuchElementException - if this set is empty.
     * @return the first (lowest) element currently in the tree.
     */
    public E first() {

        // if tree is empty we throw NoSuchElementException();
        if (size == 0) {
            throw new NoSuchElementException();
        }

        BSTNode<E> node = root;
        // recursive method that helps us actually get the lowest element.
        return firstRec(node);

    }

    /**
     * recursive method that helps us return the lowest element
     * 
     * @param node that we are currently at.
     * @return the lowest element or the next left node in the left subtree.
     */
    private E firstRec(BST<E>.BSTNode<E> node) {
        // we keep calling until we get to the left most element or the lowest element
        if (node.left != null) {
            node = node.left;
            return firstRec(node);
        }
        // return lowest element.
        return node.data;
    }

    /**
     * method that returns the last/highest element in this tree.
     * 
     * @return the last/highest element currently in the tree.
     * @throws NoSuchElementException - if this set is empty.
     */
    public E last() {
        // if tree is empty we throw NoSuchElementException();
        if (size == 0) {
            throw new NoSuchElementException();
        }

        BSTNode<E> node = root;
        // recursive method that helps us actually get the highest element.
        return lastRec(node);

    }

    /**
     * recursive method that helps us return the highest element.
     * 
     * @param node that we are currently at
     */
    private E lastRec(BST<E>.BSTNode<E> current) {
        // we keep calling until we get to the right most or highest element.
        if (current.right != null) {
            current = current.right;
            return lastRec(current);

        }
        // return highest element.
        return current.data;

    }

    /**
     * method that returns the greatest element in this set that is strictly less
     * than the given element or null if no such element exist.
     * 
     * @param e - the value we want to match.
     * @throws NullPointerException - if the specified element is null.
     * @throws ClassCastException   - if the specified element cannot be compared
     *                              with the elements currently in the set.
     * @return the greatest element less than e or null if there is no such element.
     */
    public E lower​(E e) {
        // if element is null
        if (e == null) {
            throw new NullPointerException();
        }
        try {
            // call the recursive method to help find the value we want.
            BSTNode<E> current = lowerRec(root, e);
            // if no value exist that can match the parameters we return null.
            if (current == null) {
                return null;
            }
            // return the value.
            return current.data;

        } catch (ClassCastException o) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive method that helps find the value that matches lower specifications
     * 
     * @param current- node we are currently at.
     * @param e        - object we used to find current
     */
    private BSTNode<E> lowerRec(BSTNode<E> current, E e) {
        // if the element we have is null;
        if (current == null) {
            return null;
        }
        // if the node is less that the data we are looking for go to the right subtree.
        if (current.data.compareTo(e) < 0) {
            BSTNode<E> x = lowerRec(current.right, e);
            // check if the value at the right is null if it is we return the value we are
            // currently at.
            if (x == null) {
                return current;
            } else {
                // go to right subtree
                return x;
            }

        } // else we go to right subtree.
        return lowerRec(current.left, e);
    }

    /**
     * method that returns the least element in this set that is strictly greater
     * than the given element or null if no such element exist.
     * 
     * @param e - the value we want to match.
     * @throws NullPointerException - if the specified element is null.
     * @throws ClassCastException   - if the specified element cannot be compared
     *                              with the elements currently in the set.
     * @return the least element greater than e or null if there is no such element.
     */
    public E higher​(E e) {
        // if element is null
        if (e == null) {
            throw new NullPointerException();
        }
        try {
            // call the recursive method to help find the value we want.
            BSTNode<E> current = higherRec(root, e);
            // if no value exist that can match the parameters we return null.
            if (current == null) {
                return null;
            }
            // return value/
            return current.data;

        } catch (ClassCastException o) {
            throw new ClassCastException();
        }

    }

    /**
     * recursive method that helps us find the value that matches higher
     * specification.
     * 
     * @param current- node we are currently at.
     * @param e        value we want to match.
     */
    private BSTNode<E> higherRec(BSTNode<E> current, E e) {
        // if the element we have is null;
        if (current == null) {
            return null;
        }

        // if the node is bigger that the data we are looking for go to the left
        // subtree.
        if (current.data.compareTo(e) > 0) {
            BSTNode<E> x = higherRec(current.left, e);
            // check if the value at the left is null if it is we return the value we are
            // currently at.
            if (x == null) {
                return current;
            }
            // go to left subtree
            return x;

        }
        // else go to right subtree
        return higherRec(current.right, e);

    }

    /**
     * method that compares the specified object with the tree for equality return
     * true if the given object is also a tree.
     * 
     * @param obj - object to be compared for equality with this tree.
     * @return true if the specified object is equal to the tree.
     */
    public boolean equals​(Object obj) {
        if (obj == null) {
            return false;
        }

        // if the given object is not a instance of a BST we return false
        if (!(obj instanceof BST)) {
            return false;
        } else {
            // cast needed so that we can use iterator
            BST x = (BST) obj;
            if (this.size == x.size) {
                // create two iterators one that is the for the obj the other for the tree we
                // currntly have
                Iterator<E> tree1 = x.iterator();
                Iterator<E> tree2 = this.iterator();
                // while the tree we have has a next node
                while (tree2.hasNext()) {
                    Object check1 = tree1.next();
                    Object check2 = tree2.next();

                    if (check2.equals(check1)) {
                        continue;
                    }

                }
                return true;
            }
            // return false if either the the tree adn object are not the same size or not
            // all elements are equal to each other.
            return false;
        }

    }

    /**
     * method that returns a string representation of the tree
     * The string representation consists of a list of the tree's elements in the
     * order they are returned by its iterator (inorder traversal), enclosed in
     * square brackets ("[]").
     * Adjacent elements are separated by the characters ", " (comma and space).
     * Elements are converted to strings as by String.valueOf(Object).
     * 
     * @return a string representation of this collection
     */
    public String toString() {
        // if we have no tree return empty string
        if (size == 0) {
            return "";
        }
        int counter = 0;
        // iterator to iterate through the values.
        iteratorArray x = new iteratorArray();
        String inorderString = "[";

        while (x.hasNext()) {

            // if we are at last element we don't add the comma
            if (counter == size() - 1) {
                inorderString += String.valueOf(x.next());
            } else {
                inorderString += String.valueOf(x.next()) + ", ";
            }
            counter++;

        }
        // return a string of values enclosed in brackets
        inorderString += "]";
        return inorderString;

    }

    /**
     * method that returns an array containing all of the elements returned by the
     * trees iterator.
     * in the same order, stored in consecutive elements of the array, starting with
     * index 0.
     * The length of the returned array is equal to the number of elements returned
     * by the iterator.
     * 
     * @return an array whose whose runtime component type is Object, containing all
     *         of the elements in this tree.
     */
    public Object[] toArray() {
        int counter = 0;
        Object[] x = new Object[size];
        // iterator to help us traverse the Arraylist.
        iteratorArray y = new iteratorArray();

        while (y.hasNext()) {
            // adding all elements by the trees iterator to the array.
            x[counter] = y.next();
            counter++;

        }
        return x;

    }

    /**
     * method that Produces tree like string representation of this tree. Returns a
     * string representation of this tree in a tree-like format.
     * 
     * @return a string containing all root-leaf paths of this leaf.
     */
    public String toStringTreeFormat() {
        StringBuilder x = new StringBuilder();
        // calls a helper method to help us build the string representation
        toStringPreoder(root, 0, x);

        return x.toString();

    }

    /**
     * helper method that helps to actually build the string.
     * 
     * @param current- node we are currently at.
     * @param y        to keep track of our location
     * @param x        the StringBuilder we are using.
     */
    private void toStringPreoder(BSTNode<E> current, int y, StringBuilder x) {
        // if the node we are at is null
        if (current == null) {
            if (y > 0) {
                for (int i = 0; i < y - 1; i++) {
                    x.append("  ");
                }
                x.append("|--");

            }

            x.append("null");
            x.append("\n");

        }
        // else we append the value of the current node.
        else {
            if (y > 0) {
                for (int i = 0; i < y - 1; i++) {
                    x.append("  ");
                }
                x.append("|--");

            }
            x.append(current.data + "\n");
            toStringPreoder(current.right, y + 1, x);
            toStringPreoder(current.left, y + 1, x);

        }

    }

}
