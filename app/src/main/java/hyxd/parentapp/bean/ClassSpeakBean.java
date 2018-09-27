package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/6.
 */

public class ClassSpeakBean {


    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"ClassID":"1000687179","SpeakID":"461c3a61-468d-4058-a7ae-f3cdae4c0ed1","SpeakName":"第1讲 包含与排除","TeacherNo":"US0005684","TeacherName":"苏艺伟","ClassNo":"SJ000076","times":"2018-06-05","start":"17:50:00","ends":"18:00:00","Status":null}]
     */

    private String code;
    private Object data;
    private Object detial;
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

    public List<MyDynamicDataBean> getMyDynamicData() {
        return myDynamicData;
    }

    public void setMyDynamicData(List<MyDynamicDataBean> myDynamicData) {
        this.myDynamicData = myDynamicData;
    }

    public static class MyDynamicDataBean {
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

        private String ClassID;
        private String SpeakID;
        private String SpeakName;
        private String TeacherNo;
        private String TeacherName;
        private String ClassNo;
        private String times;
        private String start;
        private String ends;
        private int Status;

        public String getClassID() {
            return ClassID;
        }

        public void setClassID(String ClassID) {
            this.ClassID = ClassID;
        }

        public String getSpeakID() {
            return SpeakID;
        }

        public void setSpeakID(String SpeakID) {
            this.SpeakID = SpeakID;
        }

        public String getSpeakName() {
            return SpeakName;
        }

        public void setSpeakName(String SpeakName) {
            this.SpeakName = SpeakName;
        }

        public String getTeacherNo() {
            return TeacherNo;
        }

        public void setTeacherNo(String TeacherNo) {
            this.TeacherNo = TeacherNo;
        }

        public String getTeacherName() {
            return TeacherName;
        }

        public void setTeacherName(String TeacherName) {
            this.TeacherName = TeacherName;
        }

        public String getClassNo() {
            return ClassNo;
        }

        public void setClassNo(String ClassNo) {
            this.ClassNo = ClassNo;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnds() {
            return ends;
        }

        public void setEnds(String ends) {
            this.ends = ends;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
    }
}
