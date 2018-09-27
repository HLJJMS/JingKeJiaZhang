package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/24.
 */

public class FBackClassBean {

    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"Student_No":"SU0038674","Student_Name":"苏艺伟","classNo":"SJ000090","className":"2018暑数学示范课程小四提高班17","TrunckNo":"100000047","TTeacherNo":"US0005685","TTeacherName":"杨海霞","BranchNo":"100000102","BTeacherNo":"US0005672","BTeacherName":"王玉珍","F0004":"暑","F0013":"数学","F0031":"双师","Period_From":"2018-06-15","Period_To":"2018-06-24"},{"Student_No":"SU0038674","Student_Name":"苏艺伟","classNo":"SJ000091","className":"2018暑数学示范课程小四提高班18","TrunckNo":"100000048","TTeacherNo":"US0005684","TTeacherName":"苏艺伟","BranchNo":"100000104","BTeacherNo":"US0005672","BTeacherName":"王玉珍","F0004":"暑","F0013":"数学","F0031":"双师","Period_From":"2018-06-19","Period_To":"2018-09-19"}]
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
         * Student_No : SU0038674
         * Student_Name : 苏艺伟
         * classNo : SJ000090
         * className : 2018暑数学示范课程小四提高班17
         * TrunckNo : 100000047
         * TTeacherNo : US0005685
         * TTeacherName : 杨海霞
         * BranchNo : 100000102
         * BTeacherNo : US0005672
         * BTeacherName : 王玉珍
         * F0004 : 暑
         * F0013 : 数学
         * F0031 : 双师
         * Period_From : 2018-06-15
         * Period_To : 2018-06-24
         */

        private String Student_No;
        private String Student_Name;
        private String classNo;
        private String className;
        private String TrunckNo;
        private String TTeacherNo;
        private String TTeacherName;
        private String BranchNo;
        private String BTeacherNo;
        private String BTeacherName;
        private String F0004;
        private String F0013;
        private String F0031;
        private String Period_From;
        private String Period_To;

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

        public String getClassNo() {
            return classNo;
        }

        public void setClassNo(String classNo) {
            this.classNo = classNo;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getTrunckNo() {
            return TrunckNo;
        }

        public void setTrunckNo(String TrunckNo) {
            this.TrunckNo = TrunckNo;
        }

        public String getTTeacherNo() {
            return TTeacherNo;
        }

        public void setTTeacherNo(String TTeacherNo) {
            this.TTeacherNo = TTeacherNo;
        }

        public String getTTeacherName() {
            return TTeacherName;
        }

        public void setTTeacherName(String TTeacherName) {
            this.TTeacherName = TTeacherName;
        }

        public String getBranchNo() {
            return BranchNo;
        }

        public void setBranchNo(String BranchNo) {
            this.BranchNo = BranchNo;
        }

        public String getBTeacherNo() {
            return BTeacherNo;
        }

        public void setBTeacherNo(String BTeacherNo) {
            this.BTeacherNo = BTeacherNo;
        }

        public String getBTeacherName() {
            return BTeacherName;
        }

        public void setBTeacherName(String BTeacherName) {
            this.BTeacherName = BTeacherName;
        }

        public String getF0004() {
            return F0004;
        }

        public void setF0004(String F0004) {
            this.F0004 = F0004;
        }

        public String getF0013() {
            return F0013;
        }

        public void setF0013(String F0013) {
            this.F0013 = F0013;
        }

        public String getF0031() {
            return F0031;
        }

        public void setF0031(String F0031) {
            this.F0031 = F0031;
        }

        public String getPeriod_From() {
            return Period_From;
        }

        public void setPeriod_From(String Period_From) {
            this.Period_From = Period_From;
        }

        public String getPeriod_To() {
            return Period_To;
        }

        public void setPeriod_To(String Period_To) {
            this.Period_To = Period_To;
        }
    }
}
