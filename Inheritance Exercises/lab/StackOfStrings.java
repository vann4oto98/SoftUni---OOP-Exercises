package lab;

import java.util.ArrayList;

public class StackOfStrings {
    private ArrayList<String> data = new ArrayList<>();

    public void push(String item){
        this.data.add(item);
    }

    public String pop(){
        if (!this.data.isEmpty()) {
            return this.data.remove(this.data.size() - 1);
        }
        return null;
    }

    public String peek(){
        if (!this.data.isEmpty()) {
            return this.data.get(this.data.size() - 1);
        }
        return null;
    }

    public boolean isEmpty(){
        return this.data.isEmpty();
    }

}
