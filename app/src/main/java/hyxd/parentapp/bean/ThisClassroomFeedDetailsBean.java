package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/29.
 */

public class ThisClassroomFeedDetailsBean {
    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : {"Table":[{"ClassID":"1000458240","SpeakID":"e6775377-20c7-43ca-ad54-20f14d3c4c2b","StudentNo":"SU0038602","Title":"入门测","ECount":5,"CurrCount":3,"ErrorCount":7},{"ClassID":"1000458240","SpeakID":"e6775377-20c7-43ca-ad54-20f14d3c4c2b","StudentNo":"SU0038602","Title":"出门考","ECount":5,"CurrCount":2,"ErrorCount":8}],"Table1":[{"classId":"1000458240","StudentNo":"SU0038602","ExamID":"353a3925-84d8-4f5c-bc07-0e9170f54d0b","Title":"林海三_课后作业_作业20170912","SecendID":"db9e99c6-d8cb-4a37-a016-4585fd1c9678","Chaptername":"正数和负数的概念","qidcount":2}],"Table2":[{"classId":"1000458239","StudentNo":"SU0038602","Title":"林海三_课后作业_作业20170911","ECount":3,"CurrCount":0,"ErrorCount":6}]}
     */

    private String code;
    private String data;
    private String detial;
    private String message;
    private MyDynamicDataBean myDynamicData;

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

    public MyDynamicDataBean getMyDynamicData() {
        return myDynamicData;
    }

    public void setMyDynamicData(MyDynamicDataBean myDynamicData) {
        this.myDynamicData = myDynamicData;
    }

    public static class MyDynamicDataBean {
        private List<TableBean> Table;
        private List<Table1Bean> Table1;
        private List<Table2Bean> Table2;

        public List<TableBean> getTable() {
            return Table;
        }

        public void setTable(List<TableBean> Table) {
            this.Table = Table;
        }

        public List<Table1Bean> getTable1() {
            return Table1;
        }

        public void setTable1(List<Table1Bean> Table1) {
            this.Table1 = Table1;
        }

        public List<Table2Bean> getTable2() {
            return Table2;
        }

        public void setTable2(List<Table2Bean> Table2) {
            this.Table2 = Table2;
        }

        public static class TableBean {
            /**
             * ClassID : 1000458240
             * SpeakID : e6775377-20c7-43ca-ad54-20f14d3c4c2b
             * StudentNo : SU0038602
             * Title : 入门测
             * ECount : 5
             * CurrCount : 3
             * ErrorCount : 7
             */

            private String ClassID;
            private String SpeakID;
            private String StudentNo;
            private String Title;
            private String ECount;
            private String CurrCount;
            private String ErrorCount;

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

            public String getStudentNo() {
                return StudentNo;
            }

            public void setStudentNo(String StudentNo) {
                this.StudentNo = StudentNo;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getECount() {
                return ECount;
            }

            public void setECount(String ECount) {
                this.ECount = ECount;
            }

            public String getCurrCount() {
                return CurrCount;
            }

            public void setCurrCount(String CurrCount) {
                this.CurrCount = CurrCount;
            }

            public String getErrorCount() {
                return ErrorCount;
            }

            public void setErrorCount(String ErrorCount) {
                this.ErrorCount = ErrorCount;
            }
        }

        public static class Table1Bean {
            /**
             * classId : 1000458240
             * StudentNo : SU0038602
             * ExamID : 353a3925-84d8-4f5c-bc07-0e9170f54d0b
             * Title : 林海三_课后作业_作业20170912
             * SecendID : db9e99c6-d8cb-4a37-a016-4585fd1c9678
             * Chaptername : 正数和负数的概念
             * qidcount : 2
             */

            private String classId;
            private String StudentNo;
            private String ExamID;
            private String Title;
            private String SecendID;
            private String Chaptername;
            private String qidcount;

            public String getClassId() {
                return classId;
            }

            public void setClassId(String classId) {
                this.classId = classId;
            }

            public String getStudentNo() {
                return StudentNo;
            }

            public void setStudentNo(String StudentNo) {
                this.StudentNo = StudentNo;
            }

            public String getExamID() {
                return ExamID;
            }

            public void setExamID(String ExamID) {
                this.ExamID = ExamID;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getSecendID() {
                return SecendID;
            }

            public void setSecendID(String SecendID) {
                this.SecendID = SecendID;
            }

            public String getChaptername() {
                return Chaptername;
            }

            public void setChaptername(String Chaptername) {
                this.Chaptername = Chaptername;
            }

            public String getQidcount() {
                return qidcount;
            }

            public void setQidcount(String qidcount) {
                this.qidcount = qidcount;
            }
        }

        public static class Table2Bean {
            /**
             * classId : 1000458239
             * StudentNo : SU0038602
             * Title : 林海三_课后作业_作业20170911
             * ECount : 3
             * CurrCount : 0
             * ErrorCount : 6
             */

            private String classId;
            private String StudentNo;
            private String Title;
            private String ECount;
            private String CurrCount;
            private String ErrorCount;

            public String getClassId() {
                return classId;
            }

            public void setClassId(String classId) {
                this.classId = classId;
            }

            public String getStudentNo() {
                return StudentNo;
            }

            public void setStudentNo(String StudentNo) {
                this.StudentNo = StudentNo;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getECount() {
                return ECount;
            }

            public void setECount(String ECount) {
                this.ECount = ECount;
            }

            public String getCurrCount() {
                return CurrCount;
            }

            public void setCurrCount(String CurrCount) {
                this.CurrCount = CurrCount;
            }

            public String getErrorCount() {
                return ErrorCount;
            }

            public void setErrorCount(String ErrorCount) {
                this.ErrorCount = ErrorCount;
            }
        }
    }
}
