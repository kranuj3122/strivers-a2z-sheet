
public class LinkedListMedium {
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

    static ListNode middleNode(ListNode head) {
        ListNode slow=head, fast=head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode reverseList(ListNode head) {
        ListNode curr=head, prev=null;
        while(curr!=null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    static ListNode recursiveReverseList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode newHead = recursiveReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }
        ListNode slow=head, fast=head;
        while(fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = reverseList(slow.next);
        
        slow = slow.next;
        fast = head;
        while(slow!=null) {
            if(slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    static ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null) {
            return head;
        }
        ListNode even = new ListNode();
        ListNode odd = new ListNode();
        ListNode e = even, o=odd, curr=head;
        int i=0;
        while(curr != null) {
            if(i%2 == 0) {
                e.next = curr;
                e = e.next;
            }
            else {
                o.next = curr;
                o = o.next;
            }
            i++;
            curr = curr.next;
        }
        o.next = null;
        e.next = odd.next;
        return even.next;
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = dummy;
        while(n-- > 0) {
            curr = curr.next;
        }
        ListNode left = dummy;
        while(curr.next!=null) {
            left = left.next;
            curr = curr.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    static ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null) {
            return null;
        }
        ListNode slow=head, fast=head, prev=null;
        while(fast!=null && fast.next!=null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode d=dummy;
        int carry=0;
        while(l1!=null && l2!=null) {
            int sum = l1.val+l2.val+carry;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            d.next = node;
            d = d.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1!=null) {
            int sum = l1.val+carry;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            d.next = node;
            d = d.next;
            l1 = l1.next;
        }
        while(l2!=null) {
            int sum = l2.val+carry;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            d.next = node;
            d = d.next;
            l2 = l2.next;
        }
        if(carry>0) {
            ListNode node = new ListNode(carry);
            d.next = node;
        }

        return dummy.next;
    }

    static ListNode addOne(ListNode head) {
        head = reverseList(head);
        
        ListNode dummy = new ListNode(-1);
        ListNode d=dummy;
        int carry=1;
        while(head!=null) {
            int sum = head.val+carry;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            d.next = node;
            d = d.next;
            head = head.next;
        }
        if(carry>0) {
            ListNode node = new ListNode(carry);
            d.next = node;
        }
        
        return reverseList(dummy.next);
    }

    static ListNode segregate012(ListNode head) {
        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode z=zero, o=one, t=two;
        ListNode curr=head;
        while(curr!=null) {
            if(curr.val==0) {
                z.next=curr;
                z=z.next;
            }
            else if(curr.val==1) {
                o.next=curr;
                o=o.next;
            }
            else {
                t.next=curr;
                t=t.next;
            }
            curr=curr.next;
        }
        t.next=null;
        o.next=two.next;
        z.next=one.next;
        return zero.next;
    }

    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(list1!=null && list2!=null) {
            if(list1.val<=list2.val) {
                curr.next=list1;
                list1=list1.next;
            }
            else {
                curr.next=list2;
                list2=list2.next;
            }
            curr=curr.next;
        }
        if(list1!=null) {
            curr.next=list1;
        }
        if(list2!=null) {
            curr.next=list2;
        }
        return dummy.next;
    }

    static ListNode sortList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode slow=head, fast=head;
        while(fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(fast);
        return mergeTwoLists(left, right);
    }

    public static void main(String[] args) {
        /*
        int[] arr = {1,2,3,4,5};
        ListNode head = createLinkedList(arr);

        ListNode midNode = middleNode(head);
        printList(midNode);

        // ListNode rev = reverseList(head);
        ListNode rev = recursiveReverseList(head);
        printList(rev);
        */

        // int[] arr = {1,2,3,3,2,1};
        // ListNode head = createLinkedList(arr);
        // System.out.println(isPalindrome(head));

        // int[] arr = {1,2,3,4,5,6};
        // ListNode head = createLinkedList(arr);
        // oddEvenList(head);
        // printList(head);

        /*
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(arr);

        head = removeNthFromEnd(head, 2);
        head = deleteMiddle(head);

        printList(head);
        */

        // int[] arr1 = {9, 9, 9, 9, 9, 9, 9};
        // int[] arr2 = {9, 9, 9};
        // ListNode l1 = createLinkedList(arr1);
        // ListNode l2 = createLinkedList(arr2);
        // ListNode result = addTwoNumbers(l1, l2);
        // printList(result);

        // int[] arr = {9, 9, 9, 9, 9, 9, 9};
        // ListNode head = createLinkedList(arr);
        // ListNode result = addOne(head);
        // printList(result);

        // int[] arr = {1, 2, 2, 1, 2, 0, 2, 2};
        // ListNode head = createLinkedList(arr);
        // ListNode result = segregate012(head);
        // printList(result);

        // int[] arr1 = {1, 2, 4};
        // int[] arr2 = {1, 3, 4};
        // ListNode list1 = createLinkedList(arr1);
        // ListNode list2 = createLinkedList(arr2);
        // ListNode mergedList = mergeTwoLists(list1, list2);
        // printList(mergedList);

        int[] arr = {-1, 5, 3, 4, 0};
        ListNode head = createLinkedList(arr);
        ListNode sortedList = sortList(head);
        printList(sortedList);
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
