package com.credithc.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.credithc.mvp.lifecycle.FragmentLifecyclePublisher;


/**
 * @author liyong
 * @date 2020/8/26
 * @des
 */
public abstract class LifecycleFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentLifecyclePublisher.onAttach(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentLifecyclePublisher.onCreate(this);
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLifecyclePublisher.onCreateView(this);
        return createView(inflater, container, savedInstanceState);
    }

    public abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentLifecyclePublisher.onViewCreated(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentLifecyclePublisher.onActivityCreated(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentLifecyclePublisher.onStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentLifecyclePublisher.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentLifecyclePublisher.onPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        FragmentLifecyclePublisher.onStop(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentLifecyclePublisher.onDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentLifecyclePublisher.onDestroy(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FragmentLifecyclePublisher.onDetach(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        FragmentLifecyclePublisher.setUserVisibleHint(isVisibleToUser, this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        FragmentLifecyclePublisher.onHiddenChanged(hidden, this);
    }

}