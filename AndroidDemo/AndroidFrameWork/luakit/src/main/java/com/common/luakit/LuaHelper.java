package com.common.luakit;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.chromium.base.PathUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LuaHelper {
    private static final String TAG = "LuaHelper";

    public static final int TYPE_CALLBACK_PRESS = 0;
    public static final int TYPE_CALLBACK_LONG_PRESS = 1;

    private static native void startLuaKitNative(Context c);

    private static native void registerCalleeNative(int size, Object[] methods);

    public static native void callback(int type, long ref);

    public static native Object callLuaFunction(String moduleName, String methodName);

    public static native Object callLuaFunction(String moduleName, String methodName, Object p1);

    public static native Object callLuaFunction(String moduleName, String methodName, Object p1, Object p2);

    public static native Object callLuaFunction(String moduleName, String methodName, Object p1, Object p2, Object p3);

    public static native Object callLuaFunction(String moduleName, String methodName, Object p1, Object p2, Object p3, Object p4);

    public static native Object callLuaFunction(String moduleName, String methodName, Object p1, Object p2, Object p3, Object p4, Object p5);

    static { System.loadLibrary("luaYoga");}

    public static void startLuaKit(Context c){
        String toPath = PathUtils.getDataDirectory(c)+"/lua";
        File toFolder = new File(toPath);
        if (toFolder.exists()){
            deleteDirection(toFolder);
        }
        toFolder = new File(toPath);
        toFolder.mkdir();
        copyFolderFromAssets(c, "lua",toPath);
        Log.d(TAG, "copyFolderFromAssets");
        startLuaKitNative(c);
    }

    public static void registerCallee(List<Method> methods) {
        List<MethodInfo> methodInfos = new ArrayList<>(methods.size());
        for (Method method : methods) {
            MethodInfo methodInfo = new MethodInfo(method);
            Log.d(TAG, "JNI Class: " + methodInfo.getClassName());
            Log.d(TAG, "JNI Method: " + methodInfo.getMethodName());
            Log.d(TAG, "JNI Return: " + methodInfo.getReturnName());
            Log.d(TAG, "JNI Params: " + methodInfo.getParamsName());
            Log.d(TAG, "JNI Signature: " + methodInfo.getMethodSignature());
            methodInfos.add(methodInfo);
        }
        registerCalleeNative(methodInfos.size(), methodInfos.toArray());
    }

    private static boolean deleteDirection(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return false;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                deleteDirection(file);// 递归
            }
        }
        dir.delete();
        return true;
    }

    private static void copyFolderFromAssets(Context context, String rootDirFullPath, String targetDirFullPath) {
        Log.d(TAG, "copyFolderFromAssets " + "rootDirFullPath-" + rootDirFullPath + " targetDirFullPath-" + targetDirFullPath);
        try {
            String[] listFiles = context.getAssets().list(rootDirFullPath);
            for (String string : listFiles) {
                Log.d(TAG, "name-" + rootDirFullPath + "/" + string);
                if (isFileByName(string)) {
                    copyFileFromAssets(context, rootDirFullPath + "/" + string, targetDirFullPath + "/" + string);
                } else {
                    String childRootDirFullPath = rootDirFullPath + "/" + string;
                    String childTargetDirFullPath = targetDirFullPath + "/" + string;
                    new File(childTargetDirFullPath).mkdirs();
                    copyFolderFromAssets(context, childRootDirFullPath, childTargetDirFullPath);
                }
            }
        } catch (IOException e) {
            Log.d(TAG, "copyFolderFromAssets " + "IOException-" + e.getMessage());
            Log.d(TAG, "copyFolderFromAssets " + "IOException-" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private static boolean isFileByName(String string) {
        if (string.contains(".")) {
            return true;
        }
        return false;
    }

    private static void copyFileFromAssets(Context context, String assetsFilePath, String targetFileFullPath) {
        Log.d(TAG, "copyFileFromAssets ");
        InputStream assestsFileImputStream;
        try {
            assestsFileImputStream = context.getAssets().open(assetsFilePath);

            FileOutputStream fos = new FileOutputStream(new File(targetFileFullPath));
            byte[] buffer = new byte[1024];
            int byteCount=0;
            while((byteCount=assestsFileImputStream.read(buffer))!=-1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            assestsFileImputStream.close();
            fos.close();
        } catch (IOException e) {
            Log.d(TAG, "copyFileFromAssets " + "IOException-" + e.getMessage());
            e.printStackTrace();
        }
    }

}
