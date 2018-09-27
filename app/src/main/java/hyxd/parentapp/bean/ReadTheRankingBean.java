package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/22.
 */

public class ReadTheRankingBean {


    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"StudentNo":"SU0032054","stName":"李一哲","CitemNo":"1000458444","ExamID":"2fc761d5-fcae-4976-a3b3-763610ec7aa2","ECount":2,"CurrCount":1,"Fraction":50},{"StudentNo":"SU0038651","stName":"冯学良","CitemNo":"1000458444","ExamID":"792ef684-2754-4bd3-8b13-0a63e678262f","ECount":2,"CurrCount":1,"Fraction":50},{"StudentNo":"SU0038636","stName":"董良瑞","CitemNo":"1000458444","ExamID":"a450e808-9ca7-462e-8a2f-d15a1c14e89a","ECount":2,"CurrCount":1,"Fraction":50},{"StudentNo":"SU0038635","stName":"张青山","CitemNo":"1000458444","ExamID":"cfc2aae9-5c83-444a-bb27-249ebd43c061","ECount":2,"CurrCount":1,"Fraction":50},{"StudentNo":"SU0038640","stName":"朱文帅","CitemNo":"1000458444","ExamID":"d6a12a51-3029-421f-9af3-35de527c6512","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038638","stName":"冷欣妍","CitemNo":"1000458444","ExamID":"da7dcc50-adda-422c-b16e-d197a3a89112","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038645","stName":"熊少峰","CitemNo":"1000458444","ExamID":"f8e82322-d1b3-4194-a81f-ebdc68b68094","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038644","stName":"董子健","CitemNo":"1000458444","ExamID":"a87146d1-3e67-4f53-b32a-ca4ddcbcb3c4","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038653","stName":"张小芹","CitemNo":"1000458444","ExamID":"b6cdbacf-9531-45c4-a98d-23decf74074e","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038643","stName":"胡明立","CitemNo":"1000458444","ExamID":"b9ecee51-4d90-436e-9c90-026e60418714","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038652","stName":"阳欧","CitemNo":"1000458444","ExamID":"7b3cf48d-7823-4b35-8ae1-e3bc33a9af64","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038639","stName":"张耀文","CitemNo":"1000458444","ExamID":"7d305f63-4fe7-4493-b0f5-6477f8d0761f","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038647","stName":"白鹏","CitemNo":"1000458444","ExamID":"8ca06e6e-b811-4bf4-8da2-44d155c2dede","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038641","stName":"黄睿","CitemNo":"1000458444","ExamID":"3cba3e53-0d79-4aba-b373-a4f3b247892a","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038648","stName":"朱新星","CitemNo":"1000458444","ExamID":"3d9c2303-a77b-42ea-9812-f530db56f4d5","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038650","stName":"苗海涛","CitemNo":"1000458444","ExamID":"6ab02334-9f8c-4b5f-97d8-eef9e38ee8eb","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038637","stName":"郭芙清","CitemNo":"1000458444","ExamID":"6e965720-a7cf-43b3-8e5f-c14163b7ebd7","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038646","stName":"王小康","CitemNo":"1000458444","ExamID":"1fb6ff92-bd5b-4d88-ae9d-23888465b7c4","ECount":2,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038654","stName":"张玉","CitemNo":"1000458444","ExamID":"1fd85926-7fda-4819-995d-895353a685b1","ECount":1,"CurrCount":0,"Fraction":0},{"StudentNo":"SU0038649","stName":"徐震","CitemNo":"1000458444","ExamID":"2cc685dc-2216-4639-8c2b-cc20ae062fae","ECount":1,"CurrCount":0,"Fraction":0}]
     */

    private String code;
    private String data;
    private String detial;
    private String message;
    private List<MyDynamicDataBean> myDynamicData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MyDynamicDataBean> getMyDynamicData() {
        return myDynamicData;
    }

    public void setMyDynamicData(List<MyDynamicDataBean> myDynamicData) {
        this.myDynamicData = myDynamicData;
    }

    public static class MyDynamicDataBean {
        /**
         * StudentNo : SU0032054
         * stName : 李一哲
         * CitemNo : 1000458444
         * ExamID : 2fc761d5-fcae-4976-a3b3-763610ec7aa2
         * ECount : 2
         * CurrCount : 1
         * Fraction : 50
         */

        private String StudentNo;
        private String stName;
        private String CitemNo;
        private String ExamID;
        private int ECount;
        private int CurrCount;
        private int Fraction;

        public String getStudentNo() {
            return StudentNo;
        }

        public void setStudentNo(String StudentNo) {
            this.StudentNo = StudentNo;
        }

        public String getStName() {
            return stName;
        }

        public void setStName(String stName) {
            this.stName = stName;
        }

        public String getCitemNo() {
            return CitemNo;
        }

        public void setCitemNo(String CitemNo) {
            this.CitemNo = CitemNo;
        }

        public String getExamID() {
            return ExamID;
        }

        public void setExamID(String ExamID) {
            this.ExamID = ExamID;
        }

        public int getECount() {
            return ECount;
        }

        public void setECount(int ECount) {
            this.ECount = ECount;
        }

        public int getCurrCount() {
            return CurrCount;
        }

        public void setCurrCount(int CurrCount) {
            this.CurrCount = CurrCount;
        }

        public int getFraction() {
            return Fraction;
        }

        public void setFraction(int Fraction) {
            this.Fraction = Fraction;
        }
    }
}
