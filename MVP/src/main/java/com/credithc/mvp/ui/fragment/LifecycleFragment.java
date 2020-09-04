package com.credithc.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.credithc.mvp.ui.lifecycle.FragmentLifecycle;

/**
 * @author liyong
 * @date 2020/8/26
 * @des
 */
public abstract class LifecycleFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentLifecycle.onAttach(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentLifecycle.onCreate(this);
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLifecycle.onCreateView(this);
        return createView(inflater, container, savedInstanceState);
    }

    public abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentLifecycle.onViewCreated(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentLifecycle.onActivityCreated(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentLifecycle.onStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentLifecycle.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentLifecycle.onPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        FragmentLifecycle.onStop(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentLifecycle.onDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentLifecycle.onDestroy(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FragmentLifecycle.onDetach(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        FragmentLifecycle.setUserVisibleHint(isVisibleToUser, this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        FragmentLifecycle.onHiddenChanged(hidden, this);
    }

}