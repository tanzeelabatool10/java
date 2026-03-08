import java.util.*;

public class arrayAsfunction {

    public static void update(int marks[], int nonChangeable){
        nonChangeable = 10;
        for(int i=0; i<marks.length; i++){
            marks[i] = marks[i] + 1;
        }
    }

    public static void main(String args[]){
        int marks[] = {97,98,99};
        int nonChangeable = 100;
        update(marks,nonChangeable);
        System.out.println(nonChangeable);

        for(int i=0; i<marks.length; i++){
            System.out.println(marks[i]);
        }
    }
}