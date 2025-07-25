import java.util.*;

public class MediumProblemOnDLL {
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

    static ListNode deleteAllOccurOfX(ListNode head, int x) {
        ListNode dummy = new ListNode();
        dummy.next=head;
        head.prev=dummy;
        
        ListNode curr=dummy;
        while(curr.next!=null) {
            if(curr.next.data==x) {
                if(curr.next.next!=null) {
                    curr.next.next.prev = curr;
                }
                curr.next = curr.next.next;
            }
            else {
                curr = curr.next;
            }
        }
        
        dummy.next.prev=null;
        return dummy.next;
    }

    static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, ListNode head) {
        ListNode tail = head;
        while(tail.next!=null) {
            tail = tail.next;
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while(head.data < tail.data) {
            int sum = head.data+tail.data;
            if(sum==target) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(head.data);
                temp.add(tail.data);;
                ans.add(temp);
                head = head.next;
                tail = tail.prev;
            }
            else if(sum>target) {
                tail = tail.prev;
            }
            else {
                head = head.next;
            }
        }
        return ans;
    }

    static ListNode removeDuplicates(ListNode head) {
        ListNode curr=head;
        while(curr.next!=null) {
            if(curr.next.data == curr.data) {
                if(curr.next.next!=null) {
                    curr.next.next.prev = curr;
                }
                curr.next = curr.next.next;
            }
            else {
                curr = curr.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        // int[] arr = {2, 5, 2, 4, 8, 10, 2, 2};
        // ListNode head = createDoublyLinkedList(arr);
        // head = deleteAllOccurOfX(head, 2);
        // printDoublyLinkedList(head);

        // int[] arr = {1, 2, 4, 5, 6, 8, 9};
        // ListNode head = createDoublyLinkedList(arr);
        // System.out.println(findPairsWithGivenSum(7, head));

        int[] arr = {1, 2, 2, 3, 3, 4, 4};
        ListNode head = createDoublyLinkedList(arr);
        head = removeDuplicates(head);
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
