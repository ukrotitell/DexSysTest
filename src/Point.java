import java.util.*;

public class Point {
    private static List<String> firstGroupPoints = new ArrayList<>();
    private static List<String> secondGroupPoints = new ArrayList<>();
    private static List<String> thirdGroupPoints = new ArrayList<>();
    private static int countOfPointWithoutGroup;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] operation = scanner.nextLine().split(" ");
            if (operation[0].equals("exit")) {
                break;
            }
            if (operation[0].equals("add")) {
                addPointToList(operation);
            }
            if (operation[0].equals("print") && operation.length == 1) {
                printPointByGroups();
            }
            if (operation[0].equals("print") && operation.length > 1) {
                printPoints(operation);
            }
            if (operation[0].equals("clear")) {
                clearMemory();
            }
            if (operation[0].equals("help")) {
                help();
            }
            if (operation[0].equals("remove")) {
                removeGroups(operation);
            }
        }


    }

    public static void addPointToList(String[] operation) {
        for (int i = 1; i < operation.length; i += 2) {
            boolean hasGroup = false;

                try {
                    if (Integer.parseInt(operation[i+1]) - Integer.parseInt(operation[i]) >= 0) {
                        firstGroupPoints.add("[" + operation[i] + ";" + operation[i+1] + "]");
                        hasGroup = true;
                    }
                    if (Integer.parseInt(operation[i+1]) - Math.pow(Integer.parseInt(operation[i]), 2) >= 0) {
                        secondGroupPoints.add("[" + operation[i] + ";" + operation[i+1] + "]");
                        hasGroup = true;
                    }
                    if (Integer.parseInt(operation[i+1]) - Math.pow(Integer.parseInt(operation[i]), 3) >= 0) {
                        thirdGroupPoints.add("[" + operation[i] + ";" + operation[i+1] + "]");
                        hasGroup = true;
                    }
                    if (!hasGroup) {
                        countOfPointWithoutGroup++;
                    }
                } catch (NumberFormatException e1) {
                    System.out.println("Bad numbers");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Not point");
                }

        }
    }

    public static void printPoints(String[] operation) {
        Set<String> setWithJoinedGroups = new HashSet<>();
        boolean isGroupOne = false;
        boolean isGroupTwo = false;
        boolean isGroupThree = false;
        for (int i = 1; i < operation.length; i++) {
            if (operation[i].equals("1")) isGroupOne = true;
            if (operation[i].equals("2")) isGroupTwo = true;
            if (operation[i].equals("3")) isGroupThree = true;
        }
        if (isGroupOne) setWithJoinedGroups.addAll(firstGroupPoints);
        if (isGroupTwo) setWithJoinedGroups.addAll(secondGroupPoints);
        if (isGroupThree) setWithJoinedGroups.addAll(thirdGroupPoints);
        setWithJoinedGroups.forEach(System.out::println);
    }

    public static void printPointByGroups() {


        if (firstGroupPoints.size() == 0) {
            System.out.print("No points in first group");
        } else {

            firstGroupPoints.forEach(System.out::print);
        }

        System.out.println();

        if (secondGroupPoints.size() == 0) {
            System.out.print("No points in second group");
        } else {
            secondGroupPoints.forEach(System.out::print);
        }
        System.out.println();

        if (thirdGroupPoints.size() == 0) {
            System.out.print("No points in third group");
        } else {
            thirdGroupPoints.forEach(System.out::print);
        }

        System.out.println();
        System.out.println("Count of points without group = " + countOfPointWithoutGroup);

    }

    public static void removeGroups(String[] operation) {
        boolean isGroupOne = false;
        boolean isGroupTwo = false;
        boolean isGroupThree = false;
        for (int i = 1; i < operation.length; i++) {
            if (operation[i].equals("1")) isGroupOne = true;
            if (operation[i].equals("2")) isGroupTwo = true;
            if (operation[i].equals("3")) isGroupThree = true;
        }
        if (isGroupOne) firstGroupPoints.clear();
        if (isGroupTwo) secondGroupPoints.clear();
        if (isGroupThree) thirdGroupPoints.clear();
    }

    public static void clearMemory() {
        firstGroupPoints.clear();
        secondGroupPoints.clear();
        thirdGroupPoints.clear();
    }

    public static void help() {
        System.out.println("add <point>        - добавить в память программы точки, координаты передаются парами чисел через пробел\n" +
                "                     прим. add 1 1 -2 -3 //добавить 2 точки: (1,1) и (-2,-3)\n" +
                "print              - напечатать построчно каждую из трех групп (входящие в них точки)\n" +
                "                     если в группу не попадает ни одна точка, то вывести сообщение, что группа пуста\n" +
                "                     последней строкой напечатать количество точек, не вошедших ни в одну группу\n" +
                "print <group_num>  - напечатать одним списком точки, входящие в группу(ы) переданную(ые) параметром <group_num>\n" +
                "                     прим. print 1 2\n" +
                "remove <group_num> - удалить из памяти все точки, входящие в группу(ы) <group_num>\n" +
                "                     прим. remove 2 3\n" +
                "clear              - очистить память\n" +
                "help               - вывод справки по командам");
    }

}
