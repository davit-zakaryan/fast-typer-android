package com.dzakaryan.fasttyper.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    //region Override open methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as? DrawerLocker)?.setDrawerLocked(false)
        return inflater.inflate(getLayoutId(), container, false)
    }
    //endregion


    protected abstract fun getLayoutId(): Int

    protected open fun retrieveViewData(bundle: Bundle?) {}

    protected open fun observeData() {}
}