
public class DoublyLinkedList {
    static ListNode createDoublyLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            ListNode newNode = new ListNode(arr[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }

        return head;
    }
    static void printDoublyLinkedList(ListNode head) {
        ListNode current = head;
        ListNode last = head;
        while (current != null) {
            System.out.print(current.data + " ");
            last = current;
            current = current.next;
        }
        System.out.println();

        while(last != null) {
            System.out.print(last.data + " ");
            last = last.prev;
        }
        System.out.println();
    }

    static ListNode addNodeAfterIndex(ListNode head, int index, int x) {
        // p>=0 && p < size_of_doubly_linked_list (0-based indexing)
        ListNode newNode = new ListNode(x);
        ListNode curr = head;
        while(index-- > 0) {
            curr = curr.next;
        }
        
        newNode.next = curr.next;
        newNode.prev = curr;
        if(curr.next != null) {
            curr.next.prev = newNode;
        }
        curr.next=newNode;
        
        return head;
    }

    static ListNode insertAtHead(ListNode head, int x) {
        ListNode newNode = new ListNode(x);
        if(head==null) {
            return newNode;
        }
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }
    static ListNode insertAtEnd(ListNode head, int x) {
        ListNode newNode = new ListNode(x);
        if(head==null) {
            return newNode;
        }
        ListNode curr=head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        newNode.prev = curr;
        return head;
    }
    static ListNode insertAtIndex(ListNode head, int index, int x) {
        // Assuming index>=0 && index<=size_of_list (0-based indexing)
        ListNode newNode = new ListNode(x);
        ListNode dummy = new ListNode();
        dummy.next = head;
        head.prev = dummy;
        ListNode curr=dummy;
        while(index-- > 0) {
            curr = curr.next;
        }
        newNode.next = curr.next;
        newNode.prev = curr;
        if(curr.next!=null) {
            curr.next.prev = newNode;
        }
        curr.next = newNode;
        
        dummy.next.prev = null;
        return dummy.next;
    }

    static ListNode deleteAtHead(ListNode head) {
        ListNode ans = head.next;
        ans.prev = null;
        head.next = null;
        return ans;
    }
    static ListNode deleteAtEnd(ListNode head) {
        ListNode curr = head;
        while(curr.next.next != null) {
            curr = curr.next;
        }
        curr.next.prev = null;
        curr.next = null;
        return head;
    }
    static ListNode deleteNodeAtIndex(ListNode head, int x) {
        // Assuming x>=1 && x<=size_of_list (1-based indexing)
        ListNode dummy = new ListNode(-1);
        head.prev = dummy;
        dummy.next = head;
        ListNode curr = dummy;
        while(x-- > 1) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        if(curr.next!=null) {
            curr.next.prev = curr;
        }
        
        dummy.next.prev = null;
        return dummy.next;
    }

    static ListNode reverseDLL(ListNode head) {
        ListNode curr=head, next=null, prev=null;
        
        while(curr!=null) {
            next = curr.next;
            curr.prev = next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        ListNode head = createDoublyLinkedList(arr);
        head = insertAtHead(head, 12);
        head = insertAtEnd(head, 9);
        head = insertAtIndex(head, 2, 30);
        // head = addNodeAfterIndex(head, 2, 15);

        // head = deleteAtHead(head);
        // head = deleteAtEnd(head);
        // head = deleteNodeAtIndex(head, 6);

        // head = reverseDLL(head);
        printDoublyLinkedList(head);
        
    }
}

class ListNode {
    int data;
    ListNode next, prev;

    ListNode() {
        this.data = 0;
        this.next = null;
        this.prev = null;
    }

    ListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    ListNode(int data, ListNode next, ListNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

