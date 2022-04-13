package Utilities;



import java.util.Arrays;

public class ArrayList<T> implements List<T>{
    private Object [] elements;
    private int currentIndex;

    //Baseline Constructor
    public ArrayList(){
        this.elements = new Object[10];
        this.currentIndex = 0;
    }


    @Override
    public void add(T element) {
        //If the currentindex (baseline 0) is less than the max length of elements
        if (currentIndex < this.elements.length){
            //Set the passed in Element equal to elements[currentindex] The currentindex value (example 0) of the elements array
            this.elements[currentIndex] = element;
            //Add 1 to the CurrentIndex
            this.currentIndex++;
        }
        else{
            //Otherwise, set Elements equal to itself but add length 10
            this.elements = Arrays.copyOf(this.elements, this.elements.length + 10);
            //Set the passed in Element equal to elements[currentindex] The currentindex value (example 0) of the elements array
            //This does not catch all, but since we're assuming that its only going to be 1 more in most cases, this is more than enough to cover the bases
            this.elements[currentIndex] = element;
        }
        this.currentIndex++;
    }

    @Override
    //Grab value [index] from the Elements Array
    public T get(int index) {
        return (T) this.elements[index];
    }

    @Override
    //Return current size of the index
    public int size() {
        return this.currentIndex;

    }


}