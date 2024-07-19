import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        
        System.out.println("Enter the number of students:");
        int student = sc.nextInt();

        for(int i=0;i<student;i++)
        {
            System.out.println("Enter the grade of student "+(i+1)+":");
            int grade = sc.nextInt();
            grades.add(grade);
        }
        double average = calAverage(grades);
        int highest = findHighest(grades);
        int lowest = findLowest(grades);

        System.out.println("Average grade: "+average);
        System.out.println("Highest grade: "+ highest);
        System.out.println("Lowest grade: "+ lowest);
        
        sc.close();
    }
    public static double calAverage(ArrayList<Integer> grades)
    {
        int sum=0;
        for(int grade : grades)
        {
            sum += grade;
        }
        return sum/(double) grades.size();
    }
    public static int findHighest(ArrayList<Integer> grades)
    {
        int highest = grades.get(0);
        for(int grade : grades)
        {
            if(grade > highest)
            highest = grade;
        }
        return highest;
    }
    public static int findLowest(ArrayList<Integer> grades)
    {
        int lowest = grades.get(0);
        for(int grade : grades)
        {
            if(grade < lowest)
            lowest = grade;
        }
        return lowest;
    }

}
