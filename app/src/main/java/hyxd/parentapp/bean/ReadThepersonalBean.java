package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/22.
 */

public class ReadThepersonalBean {

    private String code;
    private Object data;
    private Object detial;
    private String message;

    public List<MyDynamicDataBean> getMyDynamicData() {
        return myDynamicData;
    }

    public void setMyDynamicData(List<MyDynamicDataBean> myDynamicData) {
        this.myDynamicData = myDynamicData;
    }

    private List<MyDynamicDataBean> myDynamicData;



    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDetial() {
        return detial;
    }

    public void setDetial(Object detial) {
        this.detial = detial;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public  class MyDynamicDataBean {
        /**
         * ClassID : 1000687179
         * SpeakID : 461c3a61-468d-4058-a7ae-f3cdae4c0ed1
         * SpeakName : 第1讲 包含与排除
         * TeacherNo : US0005684
         * TeacherName : 苏艺伟
         * ClassNo : SJ000076
         * times : 2018-06-05
         * start : 17:50:00
         * ends : 18:00:00
         * Status : null
         */

        private String StudentNo;
        private String CitemNo;
        private String ExamID;
        private String Ssum;
        private String Bsum;
        public String getStudentNo() {
            return StudentNo;
        }

        public void setStudentNo(String studentNo) {
            StudentNo = studentNo;
        }

        public String getCitemNo() {
            return CitemNo;
        }

        public void setCitemNo(String citemNo) {
            CitemNo = citemNo;
        }

        public String getExamID() {
            return ExamID;
        }

        public void setExamID(String examID) {
            ExamID = examID;
        }

        public String getSsum() {
            return Ssum;
        }

        public void setSsum(String ssum) {
            Ssum = ssum;
        }

        public String getBsum() {
            return Bsum;
        }

        public void setBsum(String bsum) {
            Bsum = bsum;
        }




    }
}
