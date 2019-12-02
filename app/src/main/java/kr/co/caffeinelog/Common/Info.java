package kr.co.caffeinelog.Common;

public class Info { //사용자 정보 class
    public int age;
    public String gender;
    public int weight;

    public Info(){ //info 값 초기화
        age = 20;
        gender = "남자";
        weight = 60;
    }

    //내부 파일 저장 형식으로 되어있는 것을 파싱해서 변수에 넣어줌
    public Info(String str){
        age = 20;
        gender = "남자";
        weight = 60;

        String strs[] = str.split("####");
        for(int i=0;i<strs.length;i++){
            if(strs[i].length() == 0)   continue;
            String[] components = strs[i].split(":");
            if(components[0].equals("age")){
                age = Integer.parseInt(components[1]);
            }
            if(components[0].equals("gender")){
                gender = components[1];
            }
            if(components[0].equals("weight")){
                weight = Integer.parseInt(components[1]);
            }
        }
    }

    //내부 파일에 저장 형식에 맞추기
    public String toString(){
        String str = "";
        str += "age"+":"+age;
        str += "####";
        str += "gender"+":"+gender;
        str += "####";
        str += "weight"+":"+weight;

        return str;
    }
}
