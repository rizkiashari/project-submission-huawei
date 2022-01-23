package com.rizkiashari.restoapp.model;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.huawei.agconnect.cloud.database.AGConnectCloudDB;
import com.huawei.agconnect.cloud.database.CloudDBZone;
import com.huawei.agconnect.cloud.database.CloudDBZoneConfig;
import com.huawei.agconnect.cloud.database.CloudDBZoneObjectList;
import com.huawei.agconnect.cloud.database.CloudDBZoneQuery;
import com.huawei.agconnect.cloud.database.CloudDBZoneSnapshot;
import com.huawei.agconnect.cloud.database.ListenerHandler;
import com.huawei.agconnect.cloud.database.OnSnapshotListener;
import com.huawei.agconnect.cloud.database.exceptions.AGConnectCloudDBException;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CloudDBZoneWrapper {
    private static final String TAG = "CloudDBZoneWrapper";

    private AGConnectCloudDB mCloudDB;

    private CloudDBZone mCloudDBZone;

    private ListenerHandler mRegister;

    private CloudDBZoneConfig mConfig;

    private UiCallBack mUiCallBack = UiCallBack.DEFAULT;

    private int mRestoIndex = 0;

    private ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();

    public OnSnapshotListener<RestoInfo> mSnapshotListener = new OnSnapshotListener<RestoInfo>() {
        @Override
        public void onSnapshot(CloudDBZoneSnapshot<RestoInfo> cloudDBZoneSnapshot, AGConnectCloudDBException e) {
            if(e != null){
                Log.w(TAG, "onSnapshot" + e.getMessage());
                return;
            }
            CloudDBZoneObjectList<RestoInfo> snapshotObjects = cloudDBZoneSnapshot.getSnapshotObjects();
            List<RestoInfo> restoInfoList = new ArrayList<>();

            try {
                if (snapshotObjects != null) {
                    while (snapshotObjects.hasNext()) {
                        RestoInfo restoInfo = snapshotObjects.next();
                        restoInfoList.add(restoInfo);
                        updateRestoIndex(restoInfo);
                    }
                }
                mUiCallBack.onSubscribe(restoInfoList);
            } catch (AGConnectCloudDBException snapshotException) {
                Log.w(TAG, "onSnapshot:(getObject) " + snapshotException.getMessage());
            } finally {
                cloudDBZoneSnapshot.release();
            }
        }
    };

    public CloudDBZoneWrapper() {
        mCloudDB = AGConnectCloudDB.getInstance();
    }

    public static void initAGConnectCloudDB(Context context) {
        AGConnectCloudDB.initialize(context);
    }

    public void createObjectType() {
        try {
            mCloudDB.createObjectType(ObjectTypeInfoHelper.getObjectTypeInfo());
        } catch (AGConnectCloudDBException e) {
            Log.w(TAG, "createObjectType: " + e.getMessage());
        }
    }

    public void openCloudDBZone(){
        mConfig = new CloudDBZoneConfig("RestoInfo",
                CloudDBZoneConfig.CloudDBZoneSyncProperty.CLOUDDBZONE_CLOUD_CACHE,
                CloudDBZoneConfig.CloudDBZoneAccessProperty.CLOUDDBZONE_PUBLIC);
        mConfig.setPersistenceEnabled(true);
        try {
            mCloudDBZone = mCloudDB.openCloudDBZone(mConfig, true);
        }catch (AGConnectCloudDBException e) {
            Log.w(TAG, "openCloudDBZone: " + e.getMessage());
        }
    }

    public void openCloudDBZoneV2(){
        mConfig = new CloudDBZoneConfig("RestoInfo",
                CloudDBZoneConfig.CloudDBZoneSyncProperty.CLOUDDBZONE_CLOUD_CACHE,
                CloudDBZoneConfig.CloudDBZoneAccessProperty.CLOUDDBZONE_PUBLIC);
        mConfig.setPersistenceEnabled(true);
        Task<CloudDBZone> openDBZoneTask = mCloudDB.openCloudDBZone2(mConfig, true);
        openDBZoneTask.addOnSuccessListener(new OnSuccessListener<CloudDBZone>() {
            @Override
            public void onSuccess(CloudDBZone cloudDBZone) {
                Log.i(TAG, "Open cloudDBZone success");
                mCloudDBZone = cloudDBZone;
                // Add subscription after opening cloudDBZone success
                addSubscription();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "Open cloudDBZone failed for " + e.getMessage());
            }
        });
    }

    public void closeCloudDBZone() {
        try {
            mRegister.remove();
            mCloudDB.closeCloudDBZone(mCloudDBZone);
        } catch (AGConnectCloudDBException e) {
            Log.w(TAG, "closeCloudDBZone: " + e.getMessage());
        }
    }

    public void deleteCloudDBZone() {
        try {
            mCloudDB.deleteCloudDBZone(mConfig.getCloudDBZoneName());
        } catch (AGConnectCloudDBException e) {
            Log.w(TAG, "deleteCloudDBZone: " + e.getMessage());
        }
    }

    public void addCallBacks(UiCallBack uiCallBack) {
        mUiCallBack = uiCallBack;
    }

    public void addSubscription() {
        if (mCloudDBZone == null) {
            Log.w(TAG, "CloudDBZone is null, try re-open it");
            return;
        }

        try {
            CloudDBZoneQuery<RestoInfo> snapshotQuery = CloudDBZoneQuery.where(RestoInfo.class);
            mRegister = mCloudDBZone.subscribeSnapshot(snapshotQuery,
                    CloudDBZoneQuery.CloudDBZoneQueryPolicy.POLICY_QUERY_FROM_CLOUD_ONLY, mSnapshotListener);
        } catch (AGConnectCloudDBException e) {
            Log.w(TAG, "subscribeSnapshot: " + e.getMessage());
        }
    }

    private void updateRestoIndex(RestoInfo restoInfo) {
        try {
            mReadWriteLock.writeLock().lock();
            if(mRestoIndex < restoInfo.getId()){
                mRestoIndex = restoInfo.getId();
            }
        }finally {
            mReadWriteLock.writeLock().unlock();
        }
    }

    public interface UiCallBack{
        UiCallBack DEFAULT = new UiCallBack() {
            @Override
            public void onAddQuery(List<RestoInfo> restoInfoList){
                Log.i(TAG, "Using Default onAddQuery");
            }
            @Override
            public void onSubscribe(List<RestoInfo> restoInfoList) {
                Log.i(TAG, "Using default onSubscribe");
            }

            @Override
            public void onDelete(List<RestoInfo> restoInfoList) {
                Log.i(TAG, "Using default onDelete");
            }

            @Override
            public void updateUiOnError(String errorMessage) {
                Log.i(TAG, "Using default updateUiOnError");
            }
        };

        void onAddQuery(List<RestoInfo> restoInfoList);
        void onSubscribe(List<RestoInfo> restoInfoList);
        void onDelete(List<RestoInfo> restoInfoList);
        void updateUiOnError(String errorMessage);
    }
}
