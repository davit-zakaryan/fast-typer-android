package com.dzakaryan.fasttyper.presentation.statistics

import android.view.View
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsFragment : BaseFragment() {

    //region Properties
    private val viewModel: StatisticsViewModel by viewModel()
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_statistics

    override fun initViews(view: View) {
        viewModel.getPlayingHistory()
    }
    //endregion
}