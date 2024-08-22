public class Student {
    private String studentName; //학생이름
    private int studentId; //학번
    private String department; //학과명
    private String phoneNumber; //전화번호


    //오류를 막기위한 초기화
    public Student(){
        this.studentName = null;
        this.studentId = 0;
        this.department = null;
        this.phoneNumber = null;
    }

    //실질적인 생성자 (이름,학번,학과,전화번호)를 입력받는다.
    public Student(String studentName,int studentId, String department,String PhoneNumber){
        this.studentName = studentName;
        this.studentId = studentId;
        this.department = department;
        this.phoneNumber = PhoneNumber;
    }


    //입력한 값과 학생id가 같은지 확인
    public boolean checkID(int studentId){
        return this.studentId == studentId;
    }


    //각 변수의 get함수
    public int getStudentId() {
        return studentId;
    }
    public String getDepartment() {
        return department;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getStudentName() {
        return studentName;
    }

    //각 변수의 set함수
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
