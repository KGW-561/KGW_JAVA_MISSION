import java.util.Scanner;

public class MenuManager {
    public static void main(String[] args){
        Menu menu; //학생관리 프로그램 메뉴클래스 각 항목 주요 기능이 선언되있다.
        Scanner scanner = new Scanner(System.in);
        int totalStudents = 0; //처음에 받는 총 학생수

        //학생 수 예외처리 음수안되고,int형 숫자만 입력가능하다.
        while (true) {
            try {
                System.out.print("학생 수 입력: ");
                totalStudents = Integer.parseInt(scanner.nextLine());

                if (totalStudents <= 0) {
                    throw new IllegalArgumentException("양의 정수를 입력해야 합니다.");
                }
                break;
            //정수형이 아닐시
            }catch (NumberFormatException e) {
                System.out.println("1이상의 정수형을 입력해 주세요.");
            }catch (IllegalArgumentException e) {
                System.out.println("1이상의 정수형을 입력해 주세요.");
            }
        }


        //학생객체를 students배열에 선언, 생성자x
        Student[] students = new Student[totalStudents];

        //menu클래스에 생성자선언하면서 menu클래스 안에 있는 students배열객체를 선언해준다.
        menu = new Menu(students);

        while(true){
            menu.showMenu(); // 전체 메뉴를 보여준다
            int num = 0; //메뉴 번호 변수
            //메뉴번호변수 1~5숫자제외 특수문자등 모두 예외처리
            try {
                num = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e) {
                System.out.println("1이상의 정수형을 입력해 주세요");
                continue;
            }
            System.out.println("----------------------");

            //각 번호 입력시 메뉴에 맞는 기능으로 연결 6번은 예외처리, 5번은 프로그램 종료
            switch(num){
                case 1:
                    menu.registerStudent(scanner);
                    break;
                case 2:
                    menu.showStudents();
                    break;
                case 3:
                    menu.searchStudents(scanner);
                    break;
                case 4:
                    menu.updateStudent(scanner);
                    break;
                case 5:
                    if(menu.displayExitMenu(scanner)){
                        scanner.close();
                        System.out.println("[프로그램을 종료합니다.]");
                        System.exit(0);
                    }
                    else
                        break;
                default:
                    System.out.println("입력오류 입니다. 숫자 1~5 범위안에서 입력해주세요");
                    break;
            }
        }
    }
}
