package hyxd.parentapp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.ActivityManage;

//改错界面（改正）
public class AlterErrorActivity extends BaseActivity implements View.OnClickListener {


    private TextView onBack;

    private TextView title_tv;
    private ImageView iv_old_photo, iv_new_photos;

    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    private String imageFilePath;
    
    private RelativeLayout rl_web_upload, rl_photo_upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_error);

        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);


        findView();

    }

    private void findView(){
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        iv_new_photos = (ImageView) findViewById(R.id.iv_new_photos);
        iv_old_photo = (ImageView) findViewById(R.id.iv_old_photo);
        rl_photo_upload = findViewById(R.id.rl_photo_upload);
        rl_web_upload = findViewById(R.id.rl_web_upload);

        title_tv.setText("第一讲有理数的概念");

        onBack.setOnClickListener(this);
        rl_web_upload.setOnClickListener(this);
        rl_photo_upload.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();

                break;
            case R.id.rl_web_upload:
                Toast.makeText(context, "本地上传", Toast.LENGTH_SHORT).show();
                // 相册选取
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 103);
                break;
            case R.id.rl_photo_upload:
                Toast.makeText(context, "拍照上传", Toast.LENGTH_SHORT).show();
                //设置图片的保存路径,作为全局变量
                imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/filename.jpg";
                File temp = new File(imageFilePath);
                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                startActivityForResult(it, 102);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 102:
                if (resultCode == Activity.RESULT_OK) {

                    Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
                    iv_new_photos.setImageBitmap(bmp);
                }
                break;
            case 103:
                Bitmap bm = null;
                // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
                ContentResolver resolver = getContentResolver();

                try {
                    if (data != null){
                        Uri originalUri = data.getData(); // 获得图片的uri

                        bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片

                        // 这里开始的第二部分，获取图片的路径：
                        String[] proj = { MediaStore.Images.Media.DATA };

                        // 好像是android多媒体数据库的封装接口，具体的看Android文档
                        @SuppressWarnings("deprecation")
                        Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                        // 按我个人理解 这个是获得用户选择的图片的索引值
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                        cursor.moveToFirst();
                        // 最后根据索引值获取图片路径
                        String path = cursor.getString(column_index);
                        iv_old_photo.setImageURI(originalUri);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }



}
