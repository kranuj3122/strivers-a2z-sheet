
public class LinkedListHardProblem {
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


    static int listLength(ListNode head) {
        int cnt = 0;
        while(head!=null) {
            cnt++;
            head = head.next;
        }
        return cnt;
    }
    static ListNode rotateRight(ListNode head, int k) {
        if(head==null) {
            return null;
        }
        int len = listLength(head);
        k %= len;
        if(k==0) {
            return head;
        }
        ListNode d=head;
        while(k-- > 0) {
            d = d.next;
        }
        ListNode l=head;
        while(d.next!=null) {
            l=l.next;
            d=d.next;
        }
        d.next=head;
        ListNode ans = l.next;
        l.next = null;
        return ans;
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        int len = listLength(head);
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode d=dummy, prev=dummy, curr=head;
        while(len>=k) {
            for(int i=0; i<k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;

            }
            ListNode newPrev = d.next;
            d.next.next = curr;
            d.next = prev;
            prev = newPrev;
            d = prev;
            len -= k;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(arr);

        // ListNode rotatedHead = rotateRight(head, 2);
        // printList(rotatedHead);

        ListNode kReversed = reverseKGroup(head, 3);
        printList(kReversed);
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

