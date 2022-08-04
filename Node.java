package algoo;


import java.util.ArrayList;
import java.util.List;

public class Node {
	public int val;
	 
    public Node left;
    public Node right;
    public Node up;
    public Node down;
 
    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }
 
    public Node(Node lowerLevelNode) {
        this.val = lowerLevelNode.val;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = lowerLevelNode;
    }
    
     
     public class SkipList {
        private Node head;
        private Node tail;
     
        public SkipList() {
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MAX_VALUE);
     
            head.right = tail;
            tail.left = head;
        }
        int times=0;
        public Node search(int val) {
            Node curr = head;
            
            Node node;
     
            while (curr != null) {
                while (curr.right != null && curr.right.val <= val ) {
                    curr = curr.right;
                    times++;
                }
     
                if (curr.val == val) {
                    break;
                }
     
                curr = curr.down;
            }
            
     
            return curr;
        }
     
        public boolean insert(int data) {
            List<Node> update = new ArrayList<>();
     
            Node curr = head;
            while (curr != null) {
                while (curr.right != null && curr.right.val < data ) {
                    curr = curr.right;
                }
     
                update.add(curr);
                curr = curr.down;
            }
     
            
            int level = 0;
            Node newNode = null;
     
            while (level == 0 || flipCoin()) {
                
                if (newNode == null) {
                    newNode = new Node(data);
                } else {
                    newNode = new Node(newNode);
                }
     
                Node nodeToUpdate;
     
                if (update.size() <= level) {
                    createNewLayer();
                    nodeToUpdate = this.head;
                } else {
                    nodeToUpdate = update.get(update.size() - level - 1);
                }
     
                
                newNode.right = nodeToUpdate.right;
                newNode.left = nodeToUpdate;
     
                newNode.right.left = newNode;
                nodeToUpdate.right = newNode;
     
                level++;
            }
     
            return true;
        }
     
        public boolean delete(int data) {
            List<Node> pointersToUpdate = new ArrayList<>();
     
            Node curr = this.head;
            while (curr != null) {
                while (curr.right != null && curr.right.val < data ) {
                    curr = curr.right;
                }
     
                if (curr.right.val == data) {
                    pointersToUpdate.add(curr);
                }
     
                curr = curr.down;
            }
     
            for (int i = 0; i < pointersToUpdate.size(); i++) {
                Node nodeToUpdate = pointersToUpdate.get(i);
                Node nodeToDelete = nodeToUpdate.right;
     
                nodeToUpdate.right = nodeToDelete.right;
                nodeToDelete.right.left = nodeToUpdate;
     
                nodeToDelete.up = null;
                nodeToDelete.down = null;
            }
     
            return true;
        }
     int layers=0;
        private void createNewLayer() {
            Node newHead = new Node(Integer.MIN_VALUE);
            Node newTail = new Node(Integer.MAX_VALUE);
     
            newHead.right = newTail;
            newTail.left = newHead;
     
            head.up = newHead;
            newHead.down = head;
            head = newHead;
     
            tail.up = newTail;
            newTail.down = tail;
            tail = newTail;
            layers++;
        }
     
        private boolean flipCoin() {
            return Math.random() >= 0.5;
        }
     
        public void print() {
            Node curr = this.head;
     
            while (curr.down != null) {
                curr = curr.down;
            }
     
            curr = curr.right;
     
            while (curr.right != null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
     
            System.out.println();
        }
        int levels=0;
        public void printAllLevel() {
            Node curr = this.head;
     
            while (curr != null) {
                Node firstInLevel = curr;
                firstInLevel = firstInLevel.right;
                levels++;
                while (firstInLevel.right != null) {
                    System.out.print(firstInLevel.val + " ");
                    firstInLevel = firstInLevel.right;
                    
                }
     
                curr = curr.down;
                System.out.println();
            }
        }
        public int printlevel() {
        	return layers;
        }
        public void printcertainlevel(int val) {
        	Node curr = this.head;
            
            while (curr != null) {
                Node firstInLevel = curr;
                firstInLevel = firstInLevel.right;
                levels++;
                for(int i=0;i<val;i++) {
                	System.out.print(firstInLevel.val + " ");
                    firstInLevel = firstInLevel.right;
                }
     
                curr = curr.down;
                System.out.println();
            }
        }
    }
     public static void main( String[] args ) {
    	 Node n=new Node(9);
         SkipList list ;
         list =n.new SkipList();
         list.insert(3);
         list.insert(6);
         list.insert(7);
         list.insert(9);
         list.insert(12);
         list.insert(19);
         list.insert(17);
         list.insert(26);
         list.insert(21);
         list.insert(25);
  
         list.print();
         Node node = list.search(25);
         System.out.println("number of times to search" +" "+list.times);
         System.out.println("number of levels"+" "+list.printlevel());
         list.printcertainlevel(2);
  
         list.delete(10);

         
     }
     
}

