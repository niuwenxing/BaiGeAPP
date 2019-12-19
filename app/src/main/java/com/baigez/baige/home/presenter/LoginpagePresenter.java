package com.baigez.baige.home.presenter;

import com.baigez.rootlibrary.activity.mvp.presenter.BasePresenter;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;

/**
 * @ProjectName: BaiGe
 * @Package: com.baigez.baige.home.impl
 * @ClassName: LoginpagePresenter
 * @Description: 登录
 * @Author: 牛文星
 * @CreateDate: 2019/12/19 23:19
 * @UpdateUser: 牛文星
 * @UpdateDate: 2019/12/19 23:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginpagePresenter<V extends IBaseView> extends BasePresenter<V> {

    @Override
    public void cancelBiz() {
        cancelRequest();
    }
}
