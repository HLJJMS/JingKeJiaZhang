package hyxd.parentapp.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by wlm on 2018/6/25.
 */

public class RecorderUtil {

    // 录音
    private MediaRecorder recorder;

    private MediaPlayer mediaPlayer;

    private Boolean bool = false;

    private String path;

    //录音所保存的文件
    private File mAudioFile;

    //录音文件保存位置
    public static String mFilePath = new FileUtils().getSDPATH() + "audio/";

    /**
     * 开始录制音频
     */

    public void startRecord(Context context, String filePath) {

        //播放前释放资源
        releaseRecorder();
        //执行录音操作
        recordOperation(context);

    }

    /**
     * @description 录音操作
     */
    private void recordOperation(Context context) {
        //创建MediaRecorder对象
        recorder = new MediaRecorder();
        //创建录音文件,.m4a为MPEG-4音频标准的文件的扩展名
        mAudioFile = new File(mFilePath + "12345" + ".m4a");
        //创建父文件夹
        mAudioFile.getParentFile().mkdirs();
        try {
            //创建文件
            mAudioFile.createNewFile();
            //配置mMediaRecorder相应参数
            //从麦克风采集声音数据
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置保存文件格式为MP4
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置采样频率,44100是所有安卓设备都支持的频率,频率越高，音质越好，当然文件越大
            recorder.setAudioSamplingRate(44100);
            //设置声音数据编码格式,音频通用格式是AAC
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //设置编码频率
            recorder.setAudioEncodingBitRate(96000);
            //设置录音保存的文件
            recorder.setOutputFile(mAudioFile.getAbsolutePath());
            //开始录音
            recorder.prepare();
            recorder.start();
            //记录开始录音时间
            Toast.makeText(context, "录音开始", Toast.LENGTH_SHORT).show();
//            startTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            recordFail(context);
        }
    }

    /**
     * @description 录音失败处理
     */
    private void recordFail(Context context) {
        mAudioFile = null;
        releaseRecorder();
        Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * @description 翻放录音相关资源
     */
    private void releaseRecorder() {
        if (null != recorder) {
            recorder.release();
            recorder = null;
        }
    }

    /**
     * 停止录制，资源释放
     *  注意录音开始和结束两个方法调用之间应有不小于1秒的时间差
     */

    public void stopRecord(Context context){

        if(recorder != null){

            recorder.stop();

            recorder.release();

            recorder = null;

            Toast.makeText(context, "已经结束,文件保存在" + mFilePath, Toast.LENGTH_LONG).show();
//            path = "";

            bool = false;
            //录音完成释放资源
            releaseRecorder();
        }else {
            Toast.makeText(context, "停止录制", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @description 开始播放音频文件
     */
    public void startPlay(final Context context , String filePath) {

            try {
                //初始化播放器
                mediaPlayer = new MediaPlayer();
                //设置播放音频数据文件路径
//                mediaPlayer.setDataSource(mFilePath + "12345" + ".m4a");
                mediaPlayer.setDataSource(filePath);
                //设置播放监听事件
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(context, "播放完毕", Toast.LENGTH_SHORT).show();
                        //播放完成
                        playEndOrFail();
                    }
                });
                //播放发生错误监听事件
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                        Toast.makeText(context, "播放出错", Toast.LENGTH_SHORT).show();
                        playEndOrFail();
                        return true;
                    }
                });
                //播放器音量配置
                mediaPlayer.setVolume(8, 8);
                //是否循环播放
                mediaPlayer.setLooping(false);
                //准备及播放
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
                //播放失败处理
                playEndOrFail();
                Toast.makeText(context, "播放异常", Toast.LENGTH_SHORT).show();

            }

    }

    /**
     * @description 停止播放或播放失败处理
     */
    private void playEndOrFail() {

        if (null != mediaPlayer) {
            mediaPlayer.setOnCompletionListener(null);
            mediaPlayer.setOnErrorListener(null);
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}


