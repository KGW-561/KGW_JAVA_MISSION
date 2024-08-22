import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    //메뉴에서 다룰 sutdents객체 *StudentManager를 안만든 이유는 전체 학생 수가 정해져있기 떄문.
    //장점 학생 관리를 빠르게 처리가능, 단점 수가 많아지면 관리하기 힘듬
    private Student[] students;

    public Menu(Student[] students){
        this.students = students;
    }

    //전체메뉴 보이기 메서드
    public void showMenu(){
        System.out.println("**** 학생 관리 프로그램 ****");
        System.out.println("1. 학생 등록");
        System.out.println("2. 전체 출력");
        System.out.println("3. 학생 조회");
        System.out.println("4. 정보 수정");
        System.out.println("5. 프로그램 종료");
        System.out.print("관리 번호를 입력하세요. : ");
    }

    //학생 등록 메뉴 ,Student객체를 초기화 하는 함수
    public void registerStudent(Scanner scanner){
        //Student 변수에 대입할 변수 선언;
        String studentName;
        int studentId;
        String department;
        String phoneNumber;

        //학생 수 예외처리 0명일시 오류
        if(students.length == 0){
            System.out.println("[학생 수가 0명입니다. 등록을 할 수 없습니다.]");
            return;
        }
        // 한번 이상 등록하였다면 불가능
        if(students[students.length -1] != null){
            System.out.println("이미 학생 수가 만원입니다.");
            return;
        }

        System.out.println("[학생을 등록합니다.]");

        //Students(학생배열)에 크기만큼 반복합니다.
        for (int i = 0; i < students.length; i++) {

            //학생번호 StudentId를 추가합니다
            studentId = addStudentId(scanner);

            //학생이름 StudentName 추가합니다
            studentName = addStudentName(scanner);

            //학과 department를 추가합니다
            department = addDepartment(scanner);

            //전화번호 phoneNumber를 추가합니다
            phoneNumber = addPhoneNumber(scanner);

            students[i] = new Student(studentName,studentId,department,phoneNumber);
            System.out.println("----------------------");
        }
    }

    //Students객체에 studentId변수 예외처리
    public int addStudentId(Scanner scanner){
        //정상적으로 입력 되었을 시 반환할 변수 선언
        int studentId;
        while (true) {
            try {
                System.out.print("학   번 입력 : ");
                //첫글자 0,특수문자, 공백을 검사하기위해 idTemp에 string타입으로 받음
                String idTemp = scanner.nextLine();
                //String입력 받을 때 오류 검사 메서드 호출

                if (!validateInputStudentId(idTemp)) {
                    continue;
                }

                //공백제거후 int형으로 변환하여 id저장
                studentId = Integer.parseInt(idTemp.replaceAll("\\s+", ""));


                //중복 studentId검사
                if(isDuplicateStudentId(studentId)){
                    System.out.println("[동일한 학번입니다. 다시 입력하세요.]");
                    continue;
                }
                if(studentId < 0){
                    System.out.println("양수만 가능합니다.");
                    continue;
                }
                //8자리 검사
                if(validateInputStudentId(studentId)) {
                    break;
                }

            }catch (NumberFormatException e) {
                System.out.println("[정수형타입으로 입력해주세요.]");
            }
        }
        return studentId;
    }

    //Students객체에 studentName변수 예외처리
    public String addStudentName(Scanner scanner){
        //정상적으로 입력 되었을 시 반환할 변수 선언
        String studentName;
        while(true) {
            try {
                System.out.print("이   름 입력 : ");

                //공백 제거 후 저장
                studentName = scanner.nextLine().replaceAll("\\s+", "");
                //0~10글자 크기 처리
                if (studentName.isEmpty() || studentName.length() > 10) {
                    System.out.println("[이름은 1~10글자로 작성해주세요.]");
                    continue;
                }
                //입력된 문자열에 한글 외의 문자가 포함되었나 검사
                if (studentName.matches("^[가-힣]+$")) {
                    break;//한글만일 시 반복문 탈출
                } else {
                    System.out.println("한글로만 작성해주세요.");
                    continue;
                }
            }catch (NumberFormatException e) {
                System.out.println("[문자열타입으로 입력해주세요.]");
            }
        }
        return studentName;
    }

    //Students객체에 department변수 예외처리
    public String addDepartment(Scanner scanner){
        //정상적으로 입력 되었을 시 반환할 변수 선언
        String department;
        while(true) {
            try {
                System.out.print("학   과 입력 : ");
                //공백 제거 후 저장
                department = scanner.nextLine().replaceAll("\\s+", "");
                if(department.isEmpty()){//공백만 있을 시
                    System.out.println("[최소 한 글자이상 입력해주세요.]");
                    continue;
                }
                if (department.matches("^[가-힣]+$")) {
                    break;//한글만일 시 반복문 탈출
                } else {
                    System.out.println("한글로만 작성해주세요.");
                    continue;
                }
            }catch (NumberFormatException e) {
                System.out.println("[문자열타입으로 입력해주세요.]");
            }
        }
        return department;
    }

    //Students객체에 PhoneNumber변수 예외처리
    public String addPhoneNumber(Scanner scanner){
        //정상적으로 입력 되었을 시 반환할 변수 선언
        String phoneNumber;
        while(true) {
            try {
                //문자열형태로 받지만 정수형 3자리-4자리-4자리를 검사합니다.
                String regex = "\\d{3}-\\d{4}-\\d{4}";
                System.out.print("전화번호 입력 : ");
                phoneNumber = scanner.nextLine().replaceAll("\\s+", "");

                if (!phoneNumber.startsWith("010-")){
                    System.out.println("010-으로 시작해 주세요");
                    continue;
                }
                if (!phoneNumber.matches(regex)) {
                    System.out.println("[입력 양식은 010-0000-0000 입니다.]");
                    continue; // 유효하지 않는 입력이면 continue
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("[문자열타입으로 입력해주세요. 입력 양식은 000-0000-0000 입니다.]");
            }
        }
        return phoneNumber;
    }

    //중복 studentId검사
    public boolean isDuplicateStudentId(int studentId){

        //모든 배열 검사
        for (int i = 0; i < students.length; i++) {
            if(students[i] == null) {
                return false; //널값이면 false
            }
            if (students[i].checkID(studentId)) {
                return true; //중복을 발견
            }
        }
        return false;
    }

    //Id값이 String일때 예외처리
    public boolean validateInputStudentId(String studentId){
        //처음숫자가 0인경우
        if (studentId.startsWith("0")) {
            System.out.println("[0으로 시작할 수 없습니다.]");
            return false;
        }else return true;

    }

    //Id값이 int형일시 예외처리
    public boolean validateInputStudentId(int studentId){
        //8자리검사 (7자리나 9자리이상은 false)
        if (studentId > 99999999 || studentId < 10000000) {
                    System.out.println("[8자리 숫자로 입력해주세요.]");
                    return false;
                }
        return true;
    }

    //중복 검사 후 중복된 Student객체를 반환
    public Student retrieveStudent(int studentId){
        for (Student student : students) {
            if(student == null) {
                return null; //널값이면 false
            }
            if (student.checkID(studentId)) {
                return student; //중복을 발견
            }
        }
        return null;
    }

    //전체 학생 출력
    public void showStudents(){
        System.out.println("==== 전체 학생 출력 ====");
        //students배열 수 만큼
        for (int i = 0; i < students.length; i++) {
            showStudent(students[i]);
        }
    }
    //한개의 학생 출력
    public void showStudent(Student student){
        System.out.println("학  번:"+student.getStudentId());
        System.out.println("이  름:"+student.getStudentName());
        System.out.println("학  과:"+student.getDepartment());
        System.out.println("연락처: "+student.getPhoneNumber());
        System.out.println("-----------------");
    }

    //학번으로 학생 조회
    public void searchStudents(Scanner scanner){
        int studentId;

        //학번 예외처리
        while (true) {
            try {
                System.out.println("[학생을 조회 합니다.]");
                System.out.print("학번을 입력하십시오. : ");
                //첫글자 0,특수문자, 공백을 검사하기위해 idTemp에 string타입으로 받음
                String idTemp = scanner.nextLine();
                //String입력 받을 때 오류 검사 메서드 호출

                if (!validateInputStudentId(idTemp)) {
                    continue;
                }
                //공백제거후 int형으로 변환하여 id저장
                studentId = Integer.parseInt(idTemp.replaceAll("\\s+", ""));

                if(studentId < 0){
                    System.out.println("양수만 가능합니다.");
                    continue;
                }
                //8자리 검사
                if(validateInputStudentId(studentId)) {
                    break;
                }

            }catch (NumberFormatException e) {
                System.out.println("[정수형타입으로 입력해주세요.]");
            }
        }

        //중복된 studentId가 있으면 그 student를 출력한다.
        if(isDuplicateStudentId(studentId)){
            showStudent(retrieveStudent(studentId));
        }
        else{ // 없으면 메시지 출력
            System.out.println("일치하는 학번이 없습니다.");
        }
    }

    //학번으로 학생 정보 과목,전화번호 수정
    public void updateStudent(Scanner scanner){
        //입력 받을 학번
        int studentId;
        //학번 예외처리
        while (true) {
            try {
                System.out.println("[학생 정보를 수정합니다.]");
                System.out.print("학번을 입력하십시오. : ");
                //첫글자 0,특수문자, 공백을 검사하기위해 idTemp에 string타입으로 받음
                String idTemp = scanner.nextLine();
                //String입력 받을 때 오류 검사 메서드 호출

                if (!validateInputStudentId(idTemp)) {
                    continue;
                }
                //공백제거후 int형으로 변환하여 id저장
                studentId = Integer.parseInt(idTemp.replaceAll("\\s+", ""));

                if(studentId < 0){
                    System.out.println("양수만 가능합니다.");
                    continue;
                }
                //8자리 검사
                if(validateInputStudentId(studentId)) {
                    break;
                }

            }catch (NumberFormatException e) {
                System.out.println("[정수형타입으로 입력해주세요.]");
            }
        }



        //일치하는 학번이 없을 시 출력
        if(!isDuplicateStudentId(studentId)){
            System.out.println("일치하는 학번이 없습니다.");
            return;
        }

        //일치하는 학번이 있으면 학번 이름을 출력하고 학과,전화번호를 다시 입력한다.
        if(isDuplicateStudentId(studentId)){
            Student student = retrieveStudent(studentId);
            System.out.println("학  번:"+student.getStudentId());
            System.out.println("이  름:"+student.getStudentName());
            student.setDepartment(addDepartment(scanner));
            student.setPhoneNumber(addPhoneNumber(scanner));
            System.out.println("----------------------");
        }
    }

    //프로그램 종료 함수 y,n 대소문자 구별
    public boolean displayExitMenu(Scanner scanner) {
        //입력받을 변수
        String answer;
        System.out.println("프로그램을 종료하시겠습니까?(y/n)");
        while (true) {
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                return true; //y가 입력 되었을 시 true
            } else if (answer.equals("n")) {
                return false; //n이 입력 되었을 시 false
            } else //y,n 제외 입력 값일 시 반복
                System.out.println("'y' or 'n' 만 입력하세요.");
                continue;

        }
    }
}
