import java.util.InputMismatchException;
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
                if(totalStudents <0){
                    System.out.println("음수는 입력 불가합니다.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요");
            }catch (RuntimeException e) {
                // 모든 RuntimeException 처리
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }


        //학생객체를 students배열에 선언 생성자x
        Student[] students = new Student[totalStudents];

        //menu클래스에 생성자선언하면서 menu클래스 안에 있는 students배열객체를 선언해준다.
        menu = new Menu(students);

        while(true){
            menu.Show_Manu(); // 전체 메뉴를 보여준다
            int num = 0; //메뉴 번호 변수
            //메뉴번호변수 1~5숫자제외 특수문자등 모두 예외처리
            try {
                num = Integer.parseInt(scanner.nextLine());
            }catch (RuntimeException e) { //InputMismatchException은 밑에 switch문에서 default값으로 처리
                // 모든 RuntimeException 처리
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println("----------------------");

            //각 번호 입력시 메뉴에 맞는 기능으로 연결 6번은 예외처리, 5번은 프로그램 종료
            switch(num){
                case 1:
                    menu.StudentAdder(scanner);
                    break;
                case 2:
                    menu.ShowStudents();
                    break;
                case 3:
                    menu.SearchStudents(scanner);
                    break;
                case 4:
                    menu.StudentUpdater(scanner);
                    break;
                case 5:
                    if(menu.SystemExit(scanner)){
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
