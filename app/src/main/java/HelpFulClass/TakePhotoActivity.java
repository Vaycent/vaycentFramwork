package HelpFulClass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 16/9/7.
 */
public class TakePhotoActivity extends Activity {
    public ImageButton takePhoto;
    public ImageView picture;
    private final File TEMP_PIC_FILE = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/com.smartone.schsa/"+ "tempPic");
    private static final int CAMERA_REQUEST = 1;
    private static final int PHOTO_REQUEST = 2;
    private static final int CHOOSE_PHOTO_REQUEST = 3;
    private static final int CROP_PHOTO_REQUEST= 4;
    private Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//            takePhoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent photoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////              photoIntent.putExtra("return-data", true);
//                    photoIntent.setType("image/*");
//                    photoIntent.setFlags(PHOTO_REQUEST);
//
//                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    cameraIntent.setFlags(CAMERA_REQUEST);
//
////                File outputImage = new File(picture_folder,"output_image.jpg");
////                try{
////                    if(outputImage.exists()){
////                        outputImage.delete();
////                    }
////                    outputImage.createNewFile();
////                }catch(IOException e){
////                    e.printStackTrace();
////                }
////                imageUri = Uri.fromFile(outputImage);
////                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//
//
//                    ArrayList<Intent> targetIntents = new ArrayList<Intent>();
//                    targetIntents = getTargetIntentList(targetIntents, photoIntent);
//                    targetIntents = getTargetIntentList(targetIntents, cameraIntent);
//
//                    Intent target = Intent.createChooser(targetIntents.remove(0), TakePhoto.this.getResources().getString(R.string.Complete_action_using));
//                    target.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetIntents.toArray(new Parcelable[]{}));
//                    startActivityForResult(target, CHOOSE_PHOTO_REQUEST);
//
//                }
//            });

    }

    public void takePhotoOnClick(){
        Intent photoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoIntent.setType("image/*");
        photoIntent.setFlags(PHOTO_REQUEST);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.setFlags(CAMERA_REQUEST);

        ArrayList<Intent> targetIntents = new ArrayList<Intent>();
        targetIntents = getTargetIntentList(targetIntents, photoIntent);
        targetIntents = getTargetIntentList(targetIntents, cameraIntent);

        Intent target = Intent.createChooser(targetIntents.remove(0),"Completed");
        target.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetIntents.toArray(new Parcelable[]{}));
        startActivityForResult(target, CHOOSE_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("Phonto onActivityResult","requestCode:"+requestCode);
//        Log.d("Phonto onActivityResult","resultCode:"+resultCode);
//        if(data.getData()==null){
//            Log.d("Phonto onActivityResult","data.getData() is null");
//
//        }else{
//            Log.d("Phonto onActivityResult",data.getData().toString());
//
//        }
//
//        String contain= data==null?"null":data.getData().toString();
//        Log.d("Phonto onActivityResult","data.getData():"+contain);

        if(resultCode==RESULT_OK&&data.getData()!=null){
            switch (requestCode) {
                case CHOOSE_PHOTO_REQUEST:
                    startCropPhoto(data.getData());
                    break;
                case CROP_PHOTO_REQUEST:
                    DisplayImage(data.getData().toString().replace("file://",""));
                    if(!deleteTempFile(TEMP_PIC_FILE))
                        Toast.makeText(this, "Failed to delete tempFile", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }else{
            switch (requestCode){
                case CHOOSE_PHOTO_REQUEST:
                    mlog.d("Some exceptions have been occurred when \"Choose_Photo\" ");
                    break;
                case CROP_PHOTO_REQUEST:
                    mlog.d("Some exceptions have been occurred when \"Crop_Photo\" ");
                    break;
                default:
                    mlog.d("Some unknown errors ");
                    break;
            }
        }
    }



/////////////////////////////////////////

    //
    private void DisplayImage(String imagePath) {
        if(imagePath!=null){
            bitmap = BitmapFactory.decodeFile(imagePath);
            System.out.println("bitmap:"+bitmap);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

//    if(data!=null){
//        Bundle bundle = data.getExtras();
//        if(bundle!=null){
//            Bitmap photo = bundle.getParcelable("data");
//            ByteArrayOutputStream OPS = new ByteArrayOutputStream();
//            photo.compress(CompressFormat.PNG, 100, OPS);
//
//
//        }
//    }



    public ArrayList<Intent> getTargetIntentList(ArrayList<Intent> targetIntents, Intent sourceIntent){

        PackageManager pm = getPackageManager();

        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(sourceIntent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolveInfo : resolveInfoList) {
            ActivityInfo acInfo = resolveInfo.activityInfo;

            Intent intent = new Intent(sourceIntent);
            intent.setPackage(acInfo.packageName);
            intent.setClassName(acInfo.packageName, acInfo.name);
            targetIntents.add(intent);
        }
        return targetIntents;
    }

    private void startCropPhoto( Uri uri) {

        Log.d("Phonto","startCropPhoto.........");

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

//        if(baseFunc.getScreenDensity(TakePhotoActivity.this)==1280.0){
//            intent.putExtra("outputX", 500);
//            intent.putExtra("outputY", 500);
//        }else if(baseFunc.getScreenDensity(TakePhotoActivity.this)==960.0){
//            intent.putExtra("outputX", 375);
//            intent.putExtra("outputY", 375);
//        }


//            intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(initImgFile()));

        startActivityForResult(intent, CROP_PHOTO_REQUEST);


    }

    private File initImgFile(){
        if(!TEMP_PIC_FILE.exists()){
            TEMP_PIC_FILE.mkdir();
        }

        File imgFile=new File(TEMP_PIC_FILE + File.separator + getPictureFileName());


        if(imgFile.exists()){
            try{
                imgFile.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return imgFile;

    }

    private String getPictureFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".png";
    }

    private boolean deleteTempFile(File file){
        if(file.exists()){
            if (file.isFile()) {
                file.delete();
            }else if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                for (File f : childFile) {
                    deleteTempFile(f);
                }
                file.delete();
            }
        }
        return true;
    }



    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        mlog.i("onSaveInstanceState");
        savedInstanceState.putParcelable("BitmapImage", bitmap);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        mlog.i("onRestoreInstanceState");
        if(bitmap!=null){
            bitmap = savedInstanceState.getParcelable("BitmapImage");
            picture.setImageBitmap(bitmap);
        }
    }
}
