package jack.rollcall;


public class Student {
    public String sno;
    public String sname;
    public String cname;
    public int sign1=0;
    public int sign2=0;
    public int sign3=0;
    public int sign4=0;
    public int sign5=0;
    public void setsno(String sno) {
        this.sno = sno;
    }
    public String getsno(){
        return sno;
    }
    public void setsname(String sname){
        this.sname=sname;
    }
    public String sname(){
        return sname;
    }
    public void setcname(String cname){
        this.cname=cname;
    }
    public String cname(){
        return cname;
    }
    public void setsign1(int sign1){
        this.sign1=sign1;
    }
    public int getsign1(){
        return sign1;
    }
    public void setsign2(int sign2){
        this.sign2=sign2;
    }
    public int getsign2(){
        return sign2;
    }
    public void setsign4(int sign4){
        this.sign4=sign4;
    }
    public int getsign4(){
        return sign4;
    }
    public void setsign5(int sign5){
        this.sign5=sign5;
    }
    public int getsign5(){
        return sign5;
    }
    public void setsign3(int sign3){
        this.sign3=sign3;
    }
    public int getsign3(){
        return sign3;
    }

}
