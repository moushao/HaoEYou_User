package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.DepartmentResponseBean;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.SecondModel;
import com.haoeyou.user.mvp.view.SecondFragmentView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/1.
 */

public class SecondPresenter extends BasePresenter<SecondFragmentView> {
    SecondModel mModel = new SecondModel();

    public void getDoctorsPage(Context mContext, String jsonBean) {
        mModel.getDoctorsPage(mContext, jsonBean, new MVPCallBack<ArrayList<Doctors>>() {
            @Override
            public void succeed(ArrayList<Doctors> mData) {
                if (mView != null) {
                        mView.getDoctorPageSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                mView.getDoctorPageFailed();
            }
        });
    }

    public void getStandardDepartments(Context mContext) {
        mModel.getStandardDepartments(mContext, new MVPCallBack<ArrayList<DepartmentResponseBean>>() {
            @Override
            public void succeed(ArrayList<DepartmentResponseBean> mData) {
                if (mView != null) {
                    mView.getStandardDepartmentsSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
            }
        });
    }
}