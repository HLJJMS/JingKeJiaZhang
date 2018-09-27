package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/23.
 */

public class readTheAnswerBean {
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

    public class MyDynamicDataBean {
        private String StudentNo;
        private String ExamID;
        private String QID;
        private String Status;

        public String getStudentNo() {
            return StudentNo;
        }

        public void setStudentNo(String studentNo) {
            StudentNo = studentNo;
        }

        public String getExamID() {
            return ExamID;
        }

        public void setExamID(String examID) {
            ExamID = examID;
        }

        public String getQID() {
            return QID;
        }

        public void setQID(String QID) {
            this.QID = QID;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }
}
