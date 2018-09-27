package hyxd.parentapp.common;


/**
 * Created by wlm on 2017/6/6.
 */

public class Constants {

    public static String userCode = "";
    //测试环境
    public static final String HTTP_URL = "http://tst.zhongqizhiyun.com:8020/api/WXOnlineAppApi/";

////    正式环境
//    public static final String HTTP_URL = "http://testerp.iwenxin.net/webservice/FineClass.asmx";

    //查找错误信息的方法
    public static final String ErrorInfoMethodName = "TEST";

    //颇尔公司编码
    public static final String CPCode = "10007";

    //key
    public static final String key = "QspZCjj7iEGrPWQUg4eqyw";

    //获取短信验证码接口
    public static final String GetMsgMN = "A001GetPhoneCode";

    //注册账号接口
    public static final String RegisterMN = "A001AppRegister";

    //登录接口
    public static final String LoginMN = "A001AppSignin";
    //忘记密码接口
    public static final String ForgetPasMN = "A001AppBackPwd";

    //获取课程列表接口
    public static final String GetClassListMN = "GetClassListYi";

    //获取课程讲接口
    public static final String GetClassSpeakMN = "ClassSpeak";

    //获取讲对应的课后作业试题
    public static final String GetHomerWorkMN = "ReadTheTest";

    //提交作业答案
    public static final String SubmitAnswerMN = "ReadTheTestSave";

    //获取课堂反馈班级
    public static final String GetFBClassMN = "ClassFeedBack";
    //课堂反馈已反馈的讲
    public static final String GetPersonalClassFeedBack = "PersonalClassFeedBack";
    //    课堂反馈课堂反馈详情信息
    public static final String ThisClassroomFeedback = "ThisClassroomFeedback";
    //    课堂反馈作业详情
    public static final String ThisClassroomFeedDetails = "ThisClassroomFeedDetails";

}
