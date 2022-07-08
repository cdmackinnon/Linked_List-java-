class Linked_List{
    public class Node{   
        //initializing
        private Integer value;
        private Node next;
        private Node previous;

        //constructor 
        public Node(Integer value, Node next, Node previous){
        this.value = value;
        this.next = next;
        this.previous = previous;   
        }
    }


    private Node header = new Node(null,null, null);
    private Node trailer = new Node(null,null, null);
    private int size;
    public Linked_List(){
        this.header.next = this.trailer;
        this.trailer.previous = this.header;
        size = 0;
    }

    public void append(Integer value){
        Node appendee = new Node(value, this.trailer, this.trailer.previous);
        this.trailer.previous.next = appendee;
        this.trailer.previous = appendee;
        this.size++;
    }

    //insert cannot append
    public void insert(Integer value, int index){
        if (index<0 || index>=this.size){
            throw new IllegalArgumentException();
        }
        else if(index == 0){
            Node insertee = new Node(value, this.header.next, this.header);
            this.header.next.previous = insertee;
            this.header.next = insertee;
            this.size++;
        }
        else{
            Node predecessor = index_pointer(index-1);
            Node insertee = new Node(value, predecessor.next, predecessor);
            predecessor.next.previous = insertee;
            predecessor.next = insertee;
            this.size++;
        }
    }

    public Integer get_value(int index){
        return (index_pointer(index).value);
    }
    

     //insert/get_value helper method, returns pointer of an index in LL
     //runs in O(n/2) by choosing forwards or backwards traversal
        private Node index_pointer(int index){
            if (index>=this.size || index<0){
                throw new IllegalArgumentException();      
            }
            else if (index == 0){
                return (this.header.next);
            }
            else if (index == this.size-1){
                return(this.trailer.previous);
            }
            else if (index<=((this.size-1)/2)){
                Node pointer = this.header;
                for(int i = 0; i<index+1; i++){
                    pointer = pointer.next;
                }
                return pointer;
            }
            else{
                Node pointer = this.trailer;
                for(int i = this.size-1; i>index-1; i--){
                    pointer = pointer.previous;
                }
                return pointer;
            }
        }

    public int remove_value_at(int index){
        Node removee = index_pointer(index);
        removee.previous.next = removee.next;
        removee.next.previous = removee.previous;
        this.size--;
        return(removee.value);
    }

    //OVERIDES:

    public String toString(){
        Node pointer = this.header;
        String output = "[";
        for (int i=0; i<this.size-1; i++){
            pointer = pointer.next;
            output += " " + String.valueOf(pointer.value) + ",";
        }
        output += " " + String.valueOf(pointer.next.value) + " ]";
        return output;
    }

    public int length(){
        return(this.size);
    }

    

    public static void main(String [] args)
    {
        Linked_List linked_list = new Linked_List();

    }
}