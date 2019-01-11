package com.kinght.commerce.ui.MainActivity.ChooseFragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;
import com.kinght.commerce.ui.LoginActivity.LoginActivity;
import com.kinght.commerce.ui.RegisterActivity.RegisterActivity;
import com.kinght.commerce.ui.base.BaseFragment;
import com.kinght.commerce.utility.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseFragment extends BaseFragment implements ChooseFragmentMvpView {
    View root;

    @Inject
    ChooseFragmentMvpPresenter<ChooseFragmentMvpView> presenter;
    @BindView(R.id.fragment_choose_login_button)
    MaterialButton fragmentChooseLoginButton;
    @BindView(R.id.fragment_choose_register_button)
    MaterialButton fragmentChooseRegisterButton;

    public ChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_choose, container, false);
        ((MvpApp) getActivity().getApplication()).getActivityComponent().injectLoginFragment(this);

        presenter.onAttach(this);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void openRegisterActivity() {
        CommonUtils.changeActivity(getActivity(), RegisterActivity.class);
    }

    @Override
    public void openLoginActivity() {
        CommonUtils.changeActivity(getActivity(), LoginActivity.class);
    }

    @OnClick({R.id.fragment_choose_login_button, R.id.fragment_choose_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_choose_login_button:
                presenter.openLogin();
                break;
            case R.id.fragment_choose_register_button:
                presenter.openRegister();
                break;
        }
    }
}
