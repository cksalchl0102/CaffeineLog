package kr.co.caffeinelog.Common;

public class Info {
    public int age;
    public String gender;
    public int weight;

    public Info(){
        age = 20;
        gender = "남자";
        weight = 60;
    }

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
