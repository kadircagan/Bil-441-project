import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        System.out.println("kupa-4".indexOf("kupa"));
        System.out.println("kupa-4".substring(5));
        String car ="-yas";
        car=car.substring(0,1);
        System.out.println(car+"|");
        if(car =="-" ){
            System.out.println("heyy google");
        }

        ArrayList<String> deniz = new ArrayList<>();
        deniz.add("car");
        deniz.add("casr");
        deniz.add(1,"casdasdassar");
        System.out.println(deniz);

    }
}
