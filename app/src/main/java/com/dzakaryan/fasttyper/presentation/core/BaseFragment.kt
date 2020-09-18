package com.dzakaryan.fasttyper.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    //region Override open methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as? DrawerLocker)?.setDrawerLocked(false)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.show()
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        observeData()
    }
    //endregion

    //region Protected open methods
    protected abstract fun getLayoutId(): Int

    protected open fun retrieveViewData(bundle: Bundle?) {}

    protected open fun observeData() {}

    protected open fun initViews(view: View) {}
    //endregion
}