package com.mx.sy.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.mx.sy.utils.Cont;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Boolean> {

    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private boolean mSuccess = false;

    Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e( "onPreExecute: ","准备前的预操作" );
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String url = strings[0];
            Request request = new Request.Builder().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();

            if(response!=null && response.isSuccessful()){
                mSuccess = true;
                dealWithResult(response,url);
            }
        } catch (IOException e) {
            e.printStackTrace();
            mSuccess = false;
        }
        return mSuccess;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.e( "onPostExecute: ", "doInBackground的结果:"+aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.e( "onProgressUpdate: ","调用onProgressUpdate方法:"+values[0] );
    }

    /**
     * 处理下载结果
     * @param response 网络返回结果
     * @param url 请求网络的地址
     */
    private boolean dealWithResult(Response response,String url) {
        int len = 0;
        byte[] bytes = new byte[1024];
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            //创建下载文件存储的位置（路径+文件名）
            String savePath=createFolderAndPath();
            File file=new File(savePath,getNameFromUrl(url));
            fos=new FileOutputStream(file);
            is=response.body().byteStream();

            long sum=0; //下载文件的进度
            long total=response.body().contentLength(); //下载文件的总长度
            while((len = is.read(bytes))!=-1){
                fos.write(bytes,0,len);
                sum+=len;
                int progress=(int)(sum*1.0f/total*100);
                publishProgress(progress);
            }
            installApk(file);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                fos.flush();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 安装下载完成的apk
     * @param file
     */
    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.e( "installApk: ",file.getAbsolutePath());
        intent.setDataAndType(getUriFromFile(file), "application/vnd.android.package-archive");
        //解决startActivity采取的上下文的Context而不是Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        //解决手机安装软件的权限问题
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        context.startActivity(intent);
    }

    /**
     * 兼容Android版本获取Uri
     * @param file
     * @return
     */
    private Uri getUriFromFile(File file){
        Uri fileUri = null ;
        if (Build.VERSION.SDK_INT >= 24) { // Android 7.0 以上
            fileUri = FileProvider.getUriForFile(context, "com.mx.sy.fileprovider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    /**
     * 获取文件名
     * @param url
     * @return 从url中获取下载的文件名
     */
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/")+1);
    }

    /**
     * 创建下载文件存储位置
     * @return 绝对路径
     * @throws IOException
     */
    private String createFolderAndPath() throws IOException {
        String fileUrl = Environment.getExternalStorageDirectory()+ "/meixiang";
        File downloadFile=new File(fileUrl);
        if(!downloadFile.mkdirs()){
            downloadFile.createNewFile();
        }
        String savePath=downloadFile.getAbsolutePath();
        return savePath;
    }
}