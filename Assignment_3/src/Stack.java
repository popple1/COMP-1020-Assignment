public class Stack {
    private Position[] data;
    int ptr;

    public Stack(int size)
    {
        this.data = new Position[size];
        ptr = -1;
    }

    public void push(Position item)
    {
        if(isFull())
        {
            System.out.println("Stack is full");
        }
        ptr ++;
        data[ptr] = item;
    }

    public boolean isFull()
    {
        return ptr == data.length -1;
    }

    public boolean isEmpty()
    {
        return ptr == -1;
    }

    public Position pop()
    {
        if(isEmpty())
        {
           System.out.println("Stack is EMPTY");
        }
        return data[ptr--];
    }

    public Position top(){
        if(isEmpty())
            System.out.println("Cannot peak");
        return data[ptr];
    }


}
