package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/4/25.
 */

public class ClassInfoBean {


    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"ClassNo":"SJ000091","ClassName":"2018暑数学示范课程小四提高班18","F0004":"暑","F0013":"数学","F0031":"双师","Stno":"SU0038674","TrunkNo":"100000048","TTeacherNo":"US0005684","TTeacherName":"苏艺伟","Period_From":"2018-06-19","Period_To":"2018-09-19","Reserved1":"","BTeacherNo":"US0005672","BTeacherName":"王玉珍"}]
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
         * ClassNo : SJ000091
         * ClassName : 2018暑数学示范课程小四提高班18
         * F0004 : 暑
         * F0013 : 数学
         * F0031 : 双师
         * Stno : SU0038674
         * TrunkNo : 100000048
         * TTeacherNo : US0005684
         * TTeacherName : 苏艺伟
         * Period_From : 2018-06-19
         * Period_To : 2018-09-19
         * Reserved1 :
         * BTeacherNo : US0005672
         * BTeacherName : 王玉珍
         */

        private String ClassNo;
        private String ClassName;
        private String F0004;
        private String F0013;
        private String F0031;
        private String Stno;
        private String TrunkNo;
        private String TTeacherNo;
        private String TTeacherName;
        private String Period_From;
        private String Period_To;
        private String Reserved1;
        private String BTeacherNo;
        private String BTeacherName;

        public String getClassNo() {
            return ClassNo;
        }

        public void setClassNo(String ClassNo) {
            this.ClassNo = ClassNo;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
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

        public String getStno() {
            return Stno;
        }

        public void setStno(String Stno) {
            this.Stno = Stno;
        }

        public String getTrunkNo() {
            return TrunkNo;
        }

        public void setTrunkNo(String TrunkNo) {
            this.TrunkNo = TrunkNo;
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

        public String getReserved1() {
            return Reserved1;
        }

        public void setReserved1(String Reserved1) {
            this.Reserved1 = Reserved1;
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
    }
}
