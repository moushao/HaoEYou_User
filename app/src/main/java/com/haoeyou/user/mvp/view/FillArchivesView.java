package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.ArchivesInfoResponseBean;

/**
 * Created by Mou on 2017/6/9.
 */

public interface FillArchivesView extends BaseView {
    void setInfo(ArchivesInfoResponseBean bean);

    void changeSuccess();

    void changeFailed(String message);

    void addSuccess();
}