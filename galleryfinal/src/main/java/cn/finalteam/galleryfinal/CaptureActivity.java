package cn.finalteam.galleryfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.model.PhotoTempModel;
import cn.finalteam.galleryfinal.utils.Utils;

public class CaptureActivity extends PhotoBaseActivity {
    private ArrayList<PhotoInfo> mPhotoList;
    private ArrayList<PhotoInfo> mSelectPhotoList;
    private LinkedHashMap<Integer, PhotoTempModel> mPhotoTempMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window win = getWindow();

        WindowManager.LayoutParams lp = win.getAttributes();

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;

        lp.height = 0;

        lp.dimAmount = 0;

        win.setAttributes(lp);

        setContentView(R.layout.activity_capture);
        takePhotoAction();
    }

    @Override
    protected void onResume() {
//        finish();
        super.onResume();
    }

//    /**
//     * 拍照
//     */
//    protected void takePhotoAction() {
//        if (!DeviceUtils.existSDCard()) {
//            String errormsg = getString(R.string.empty_sdcard);
//            toast(errormsg);
//            if (mTakePhotoAction) {
//                resultFailure(errormsg, true);
//            }
//            return;
//        }
//
//        File takePhotoFolder = null;
//        if (StringUtils.isEmpty(mPhotoTargetFolder)) {
//            takePhotoFolder = GalleryFinal.getCoreConfig().getTakePhotoFolder();
//        } else {
//            takePhotoFolder = new File(mPhotoTargetFolder);
//        }
//        boolean suc = FileUtils.mkdirs(takePhotoFolder);
//        File toFile = new File(takePhotoFolder, "IMG" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".jpg");
//
//        ILogger.d("create folder=" + toFile.getAbsolutePath());
//        if (suc) {
//            mTakePhotoUri = Uri.fromFile(toFile);
//            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);
//            startActivityForResult(captureIntent, GalleryFinal.TAKE_REQUEST_CODE);
//        } else {
//            takePhotoFailure();
//            ILogger.e("create file failure");
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
        if ( requestCode == GalleryFinal.TAKE_REQUEST_CODE ) {
            if (resultCode == RESULT_OK && mTakePhotoUri != null) {
                final String path = mTakePhotoUri.getPath();
                if (new File(path).exists()) {
                    final PhotoInfo info = new PhotoInfo();
                    info.setPhotoId(Utils.getRandom(10000, 99999));
                    info.setPhotoPath(path);
                    updateGallery(path);
                    takeResult(info);
                }
            }
        }
        finish();
    }
//
//    private void takePhotoFailure() {
//        String errormsg = getString(R.string.take_photo_fail);
//        if (mTakePhotoAction) {
//            resultFailure(errormsg, true);
//        } else {
//            toast(errormsg);
//        }
//    }

    @Override
    protected void takeResult(PhotoInfo info) {
        if (mPhotoList == null) {
            mPhotoList = new ArrayList<>();
        }
        if (mSelectPhotoList == null) {
            mSelectPhotoList = new ArrayList<>();
        }
        if (mPhotoTempMap == null) {
            mPhotoTempMap = new LinkedHashMap<>();
        }
        if (!GalleryFinal.getFunctionConfig().isMutiSelect()) {
            mPhotoList.clear();
            mSelectPhotoList.clear();
        }
        mPhotoList.add(0, info);
        mSelectPhotoList.add(info);
        mPhotoTempMap.put(info.getPhotoId(), new PhotoTempModel(info.getPhotoPath()));
        resultData(mSelectPhotoList);
        Log.i("photo", "照相选图");

    }

}
