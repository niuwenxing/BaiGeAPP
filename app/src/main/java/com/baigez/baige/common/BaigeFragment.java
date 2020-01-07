package com.baigez.baige.common;

import com.baigez.baige.common.widget.DefaultPresenterImpl;
import com.baigez.rootlibrary.activity.mvp.fragment.BaseFragment;
import com.baigez.rootlibrary.activity.mvp.presenter.BasePresenter;
import com.baigez.rootlibrary.activity.mvp.view.IBaseView;

/**
 * @ProjectName: BaiGe
 * @Package: com.baigez.baige.common
 * @ClassName: BaigeFragment
 * @Description: java类作用描述
 * @Author: 牛文星
 * @CreateDate: 2019/12/22 21:08
 * @UpdateUser: 牛文星
 * @UpdateDate: 2019/12/22 21:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaigeFragment <V extends IBaseView,P extends BasePresenter<V>> extends BaseFragment<V,P> {

    @Override
    protected void createPresenter() {
        presenter=(P) new DefaultPresenterImpl();
    }

}
