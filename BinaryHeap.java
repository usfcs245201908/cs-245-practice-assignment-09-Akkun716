public class BinaryHeap
{
    private Integer[] heap;
    private int tail, parent, child;

    public BinaryHeap()
    {
        heap = new Integer[10];
        //This will track the last item in the heap
        tail = -1;
    }

    public void add(int num)
    {
        //Will be the index the item will rest
        tail++;

        //Grow the heap if all space is used
        if(tail == heap.length)
        { growArray(); }

        heap[tail] = num;

        child = tail;

        //If a second child exists, corrects the parent index
        if(child % 2 == 0 && child != 0)
        { parent = child / 2 - 1; }
        else
        { parent = child / 2; }

    //Bubble up
        while(heap[parent] > heap[child])
        {
            //Parent and child values swap
            heap[child] = heap[parent];
            heap[parent] = num;
            //Child index is now the previous parent's index
            child = parent;

            //Corrects parent index for second child
            if(child % 2 == 0 && child != 0)
            { parent = child / 2 - 1; }
            else
            { parent = child / 2; }
        }
    }

    public int remove()
    {
        int removed = heap[0];

        //Checks if more than one elem in heap
        if(tail > 0)
        {
            heap[0] = heap[tail];
            tail--;
        }
        else
        { tail = -1; }

        parent = 0;
        child = 1;

        //If right child exists and is less than left
        if(child + 1 <= tail && heap[child + 1] < heap[child])
        { child = child + 1; }

    //Sift down
        while(child <= tail && heap[child] < heap[parent])
        {
            //Parent and child values swap
            int temp = heap[parent];
            heap[parent] = heap[child];
            heap[child] = temp;
            //Sets next subtree branch
            parent = child;
            child = parent * 2 + 1;

            //Lesser right child check
            if(child + 1 <= tail && heap[child + 1] < heap[child])
            { child = child + 1; }
        }

        return removed;
    }

    private void growArray()
    {
        Integer[] arrCopy = new Integer[heap.length * 2];
        System.arraycopy(heap, 0, arrCopy, 0, heap.length);
        heap = arrCopy;
    }

    public String toString()
    {
        String output = "";

        for(int i = 0; i <= tail; i++)
        {
            output += heap[i];
            if(i < tail)
            { output += ", "; }
        }

        return output;
    }
}
