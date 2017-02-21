import java.io.*;

// NOTE: This is a double linked list (forward and backward) but not circular.
// i.e. First element's head will reference null as will the last element's tail.
class MyElement {
  MyElement previous;
  MyElement next;
  int value;

  MyElement() {
      previous = null;
      next = null;
      value = 0;
  }
  MyElement(int new_value) {
    previous = null;
    next = null;
    value = new_value;
  }
}
public class MyDoubleLinkedList {
  private MyElement my_list_head;
  private MyElement my_list_tail;
  private int size_of_list;

  public MyDoubleLinkedList() {
    // Empty list
    my_list_head = null;
    my_list_tail = null;
    size_of_list = 0;

    return;
  }
  public MyDoubleLinkedList(int number_elements){
    MyElement temp_element = null;
    MyElement prev_element = null;

    if (number_elements < 1) {
      System.out.printf("ERROR: MyDoubleLinkedList: number_elements '%d' < 1\n", number_elements);
      System.exit(-1);
    }
    // Initialize the list (at least one element)
    my_list_head = new MyElement();
    prev_element = my_list_head;
    temp_element = my_list_head;
    size_of_list++;

    for (int i=1; i<number_elements; i++) {
        temp_element = new MyElement();
        prev_element.next = temp_element;
        temp_element.previous = prev_element;
        size_of_list++;
    }

    // Last item created is the tail in this list
    my_list_tail = temp_element;
  }

  // Reader functions
  public int length() {
    return size_of_list;
  }
  public MyElement first() {
    return my_list_head;
  }
  public MyElement last() {
    return my_list_tail;
  }
  // Position for reading values is 1 based (starts at 1)
  public int elementValue(int position) {
    MyElement current_element = my_list_head;

    if (position < 1 || position > size_of_list) {
      System.out.printf("ERROR: position '%d' is invalid.  Valid values 1 - %d\n", position, size_of_list);
      System.exit(-1);
    }
    // Shortcut if position is end of list because we already have the tail
    if (position == size_of_list) {
      return my_list_tail.value;
    }
    for (int i=1; i<position; i++) {
      current_element = current_element.next;
    }
    return current_element.value;
  }

  // Insert an element after position in list.
  // 0 = insert at head
  // n (list size) = insert after tail
  public MyElement insertElementAfter(int position, int value) {
    MyElement current_element;
    MyElement temp_element;

    if (position > size_of_list) {
      System.out.printf("ERROR: MyDoubleLinkedList: insertElementAfter: position '%d' > size_of_list '%d'/n", position, size_of_list);
      System.exit(-1);
    }

    temp_element = new MyElement(value);

    if (position == 0 && size_of_list == 0) {
        // List is currently empty
        my_list_head = temp_element;
        my_list_tail = temp_element;
        size_of_list = 1;
    } else if (position == 0) {
      // Insert in front of head (prev == NULL)
      current_element = my_list_head;
      current_element.previous = temp_element;
      temp_element.next = current_element;
      temp_element.previous = null;
      my_list_head = temp_element;
      size_of_list++;
    } else if (position == size_of_list) {
      // Insert at end of list
      current_element = my_list_tail;
      current_element.next = temp_element;
      temp_element.previous = current_element;
      temp_element.next = null;
      my_list_tail = temp_element;
      size_of_list++;
    } else {
      // Insert in middle of list somewhere (insert after element chosen)
      current_element = my_list_head;
      for (int i=1; i<position; i++) {
        if (current_element.next != null) {
          current_element = current_element.next;
        } else {
          System.out.printf("ERROR: MyDoubleLinkedList: insertElementAfter: position '%d'", position);
          System.out.printf(" is not a valid element (NULL)\n");
          System.exit(-1);
        }
      }
      current_element.next.previous = temp_element;
      temp_element.next = current_element.next;
      temp_element.previous = current_element;
      current_element.next = temp_element;
      size_of_list++;
    }

    // No particular reason to return new element, but do anyway (might want to
    // use it directly at this point)
    return temp_element;
  }

  public void deleteElement(int position) {
    MyElement current_element = my_list_head;

    if (position < 1 || position > size_of_list) {
      System.out.printf("ERROR: MyDoubleLinkedList: deleteElement: ");
      System.out.printf("position '%d' is invalid.  Valid values 1 - %d\n", position, size_of_list);
      System.exit(-1);
    }

    // Deleting head
    if (position == 1) {
        // If only one element:
        if (size_of_list == 1) {
          my_list_head = null;
          my_list_tail = null;
          size_of_list = 0;
          return;
        }
        my_list_head = current_element.next;
        my_list_head.previous = null;
        current_element.next = null;
        size_of_list--;
        return;
    }

    // Deleting tail
    if (position == size_of_list) {
      // NOTE: Case where tail == head is taken care of above where head is only one element
      current_element = my_list_tail;
      my_list_tail = current_element.previous;
      my_list_tail.next = null;
      current_element.previous = null;
      size_of_list--;
      return;
    }

    // Deleting an element mid list
    for (int i=1; i<position; i++) {
      current_element = current_element.next;
    }
    current_element.next.previous = current_element.previous;
    current_element.previous.next = current_element.next;
    current_element.previous = null;
    current_element.next = null;
    size_of_list--;
  }
}
