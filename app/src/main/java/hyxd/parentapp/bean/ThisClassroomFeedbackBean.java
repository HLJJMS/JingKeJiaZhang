package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/28.
 */

public class ThisClassroomFeedbackBean {

    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"RecordCode":"TR000000028","LessonCode":"1000687207","kemu":"数学","Teacher":"苏艺伟","StartTime":"2018-06-19T10:30:00","EndTime":"2018-06-19T11:30:00","teachingRecords":"2018暑数学示范课程小四提高班18","ThisTime":"4","ThisMaster":"图V5那","LastTime":"4","LastMaster":"","ThisExplain":"啦啦啦","Other":"","Student_No":"SU0038674","Student_Name":"苏艺伟"}]
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

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getDetial() {
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
         * RecordCode : TR000000028
         * LessonCode : 1000687207
         * kemu : 数学
         * Teacher : 苏艺伟
         * StartTime : 2018-06-19T10:30:00
         * EndTime : 2018-06-19T11:30:00
         * teachingRecords : 2018暑数学示范课程小四提高班18
         * ThisTime : 4
         * ThisMaster : 图V5那
         * LastTime : 4
         * LastMaster :
         * ThisExplain : 啦啦啦
         * Other :
         * Student_No : SU0038674
         * Student_Name : 苏艺伟
         */

        private String RecordCode;
        private String LessonCode;
        private String kemu;
        private String Teacher;
        private String StartTime;
        private String EndTime;
        private String teachingRecords;
        private String ThisTime;
        private String ThisMaster;
        private String LastTime;
        private String LastMaster;
        private String ThisExplain;
        private String Other;
        private String Student_No;
        private String Student_Name;

        public String getRecordCode() {
            return RecordCode;
        }

        public void setRecordCode(String RecordCode) {
            this.RecordCode = RecordCode;
        }

        public String getLessonCode() {
            return LessonCode;
        }

        public void setLessonCode(String LessonCode) {
            this.LessonCode = LessonCode;
        }

        public String getKemu() {
            return kemu;
        }

        public void setKemu(String kemu) {
            this.kemu = kemu;
        }

        public String getTeacher() {
            return Teacher;
        }

        public void setTeacher(String Teacher) {
            this.Teacher = Teacher;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getTeachingRecords() {
            return teachingRecords;
        }

        public void setTeachingRecords(String teachingRecords) {
            this.teachingRecords = teachingRecords;
        }

        public String getThisTime() {
            return ThisTime;
        }

        public void setThisTime(String ThisTime) {
            this.ThisTime = ThisTime;
        }

        public String getThisMaster() {
            return ThisMaster;
        }

        public void setThisMaster(String ThisMaster) {
            this.ThisMaster = ThisMaster;
        }

        public String getLastTime() {
            return LastTime;
        }

        public void setLastTime(String LastTime) {
            this.LastTime = LastTime;
        }

        public String getLastMaster() {
            return LastMaster;
        }

        public void setLastMaster(String LastMaster) {
            this.LastMaster = LastMaster;
        }

        public String getThisExplain() {
            return ThisExplain;
        }

        public void setThisExplain(String ThisExplain) {
            this.ThisExplain = ThisExplain;
        }

        public String getOther() {
            return Other;
        }

        public void setOther(String Other) {
            this.Other = Other;
        }

        public String getStudent_No() {
            return Student_No;
        }

        public void setStudent_No(String Student_No) {
            this.Student_No = Student_No;
        }

        public String getStudent_Name() {
            return Student_Name;
        }

        public void setStudent_Name(String Student_Name) {
            this.Student_Name = Student_Name;
        }
    }
}
