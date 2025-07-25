
public class SingleLinkedList {
    static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    static void printList(ListNode head) {
        if(head==null) {
            return;
        }
        ListNode curr = head;
        while(curr != null) {
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    static ListNode insertAtBegining(ListNode head, int x) {
        ListNode node = new ListNode(x);
        node.next = head;
        return node;
    }
    static ListNode insertAtEnd(ListNode head, int x) {
        ListNode newNode = new ListNode(x);
        if(head==null) {
            return newNode;
        }
        ListNode curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }
    static ListNode insertAtIndex(ListNode head, int index, int x) {
        // Assuming 0-based indexing
        ListNode newNode = new ListNode(x);
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = dummy;
        while(index-- > 0 && curr.next!=null) {
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;
        return dummy.next;
    }
    static ListNode insertInMiddle(ListNode head, int x) {
        ListNode newNode = new ListNode(x);
        if(head==null) {
            return newNode;
        }
        ListNode slow=head, fast=head;
        while(fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        newNode.next = slow.next;
        slow.next = newNode;
        return head;
    }

    static ListNode deleteHead(ListNode head) {
        head = head.next;
        return head;
    }
    static ListNode deleteAtEnd(ListNode head) {
        if(head==null || head.next==null) {
            return null;
        }
        ListNode curr = head;
        while(curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        return head;
    }
    static ListNode deleteAtIndex(ListNode head, int x) {
        // Assuming x>=1 && x<=linkedList_size (1-based indexing)
        ListNode dummy = new ListNode(x);
        dummy.next = head;
        ListNode curr = dummy;
        while(x-- > 1) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dummy.next;
    }

    static int linkedListSize(ListNode head) {
        int cnt=0;
        ListNode curr=head;
        while(curr != null) {
            curr = curr.next;
            cnt++;
        }
        return cnt;
    }

    static boolean searchKey(ListNode head, int key) {
        ListNode curr=head;
        while(curr != null) {
            if(curr.val==key) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        ListNode head = createLinkedList(arr);
        head = insertAtBegining(head, 15);
        head = insertAtEnd(head, 30);
        head = insertAtIndex(head, 4, 23);
        head = insertInMiddle(head, 100);
        // head = deleteHead(head);
        // head = deleteAtEnd(head);
        // head = deleteAtIndex(head, 4);
        // System.out.println(linkedListSize(head));
        // System.out.println(searchKey(head, 23));
        printList(head);


    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
        val = 0;
        next = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
    }

    ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
}
