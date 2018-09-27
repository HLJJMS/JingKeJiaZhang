package hyxd.parentapp.bean;

import java.util.List;

/**
 * Created by wlm on 2018/8/23.
 */

public class ReadTheanalysisBean {

    /**
     * code : 0
     * data : null
     * detial : null
     * message : 操作成功
     * myDynamicData : [{"EID":"cb4de30d-5a14-4640-a9b7-046bdb948a1f","QID":"76a61cc3-0cab-4831-8c47-799c01f3398a","QusAnswer":"C","Stem":">","Choses":">","Correct":"C","Analysis":"2"}]
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
         * EID : cb4de30d-5a14-4640-a9b7-046bdb948a1f
         * QID : 76a61cc3-0cab-4831-8c47-799c01f3398a
         * QusAnswer : C
         * Stem : >
         * Choses : >
         * Correct : C
         * Analysis : 2
         */

        private String EID;
        private String QID;
        private String QusAnswer;
        private String Stem;
        private String Choses;
        private String Correct;
        private String Analysis;

        public String getEID() {
            return EID;
        }

        public void setEID(String EID) {
            this.EID = EID;
        }

        public String getQID() {
            return QID;
        }

        public void setQID(String QID) {
            this.QID = QID;
        }

        public String getQusAnswer() {
            return QusAnswer;
        }

        public void setQusAnswer(String QusAnswer) {
            this.QusAnswer = QusAnswer;
        }

        public String getStem() {
            return Stem;
        }

        public void setStem(String Stem) {
            this.Stem = Stem;
        }

        public String getChoses() {
            return Choses;
        }

        public void setChoses(String Choses) {
            this.Choses = Choses;
        }

        public String getCorrect() {
            return Correct;
        }

        public void setCorrect(String Correct) {
            this.Correct = Correct;
        }

        public String getAnalysis() {
            return Analysis;
        }

        public void setAnalysis(String Analysis) {
            this.Analysis = Analysis;
        }
    }
}
