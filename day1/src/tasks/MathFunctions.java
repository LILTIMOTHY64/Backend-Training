package tasks;

public class MathFunctions {
    void addition(int a,int b){
        int result = a + b;
        System.out.println("Addition: " + result);
    }

    void subtraction(int a,int b){
        int result = a - b;
        System.out.println("Subtraction: " + result);
    }

    void multiplication(int a,int b){
        int result = a * b;
        System.out.println("Multiplication: " + result);
    }

    void division(int a,int b){
        if (b == 0) {
            System.out.println("Division: Cannot divide by zero");
            return;
        }
        int result = a / b;
        System.out.println("Division: " + result);
    }

    void areaOfCircle(int r){
        double area = Math.PI * r * r;
        System.out.println("Area of Circle: " + area);
    }

    void areaOfTriangle(int b,int h){
        double area = 0.5 * b * h;
        System.out.println("Area of Triangle: " + area);
    }

    void fibonacci(int num){
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += a;
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println("Fibonacci Sum: "+sum);
    }

    void factorial(int num){
        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        System.out.println("Factorial: " + fact);
    }

    public static void main(String[] args) {
        int base = 10;
        int height = 15;
        int radius = 4;
        int num1 = 50;
        int num2 = 2;
        int number = 5;

        MathFunctions mf = new MathFunctions();

        mf.addition(num1,num2);
        mf.subtraction(num1,num2);
        mf.multiplication(num1,num2);
        mf.division(num1,num2);
        mf.areaOfCircle(radius);
        mf.areaOfTriangle(base,height);
        mf.factorial(number);
        mf.fibonacci(number);

    }
}
