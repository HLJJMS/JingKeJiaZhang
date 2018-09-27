package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/27.
 */

public class PersonalClassFeedBackBean {


    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"classId":"1000687207","classNo":"SJ000091","times":"2018-06-19","timestar":"10:30:00","timeend":"11:30:00","SpeakID":"49cb7276-a902-47c9-918d-4ef94ddb3d05","SpeakName":"第1讲 四则运算示范课程","Student_No":"SU0038674","Student_Name":"苏艺伟","Students":"图V5那"}]
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
         * classId : 1000687207
         * classNo : SJ000091
         * times : 2018-06-19
         * timestar : 10:30:00
         * timeend : 11:30:00
         * SpeakID : 49cb7276-a902-47c9-918d-4ef94ddb3d05
         * SpeakName : 第1讲 四则运算示范课程
         * Student_No : SU0038674
         * Student_Name : 苏艺伟
         * Students : 图V5那
         */

        private String classId;
        private String classNo;
        private String times;
        private String timestar;
        private String timeend;
        private String SpeakID;
        private String SpeakName;
        private String Student_No;
        private String Student_Name;
        private String Students;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getClassNo() {
            return classNo;
        }

        public void setClassNo(String classNo) {
            this.classNo = classNo;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getTimestar() {
            return timestar;
        }

        public void setTimestar(String timestar) {
            this.timestar = timestar;
        }

        public String getTimeend() {
            return timeend;
        }

        public void setTimeend(String timeend) {
            this.timeend = timeend;
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

        public String getStudents() {
            return Students;
        }

        public void setStudents(String Students) {
            this.Students = Students;
        }
    }
}
