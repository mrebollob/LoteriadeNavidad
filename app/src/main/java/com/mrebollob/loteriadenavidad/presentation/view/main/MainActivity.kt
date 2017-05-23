/*
 * Copyright (c) 2017. Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.presentation.view.main


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.app.util.FeedbackUtils
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.main.MainPresenter
import com.mrebollob.loteriadenavidad.presentation.view.BaseActivity
import com.mrebollob.loteriadenavidad.presentation.view.main.adapter.LotteryTicketsAdapter
import com.mrebollob.loteriadenavidad.utils.analytics.AnalyticsHelper
import com.mrebollob.loteriadenavidad.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, SwipeRefreshLayout.OnRefreshListener {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var mAnalyticsHelper: AnalyticsHelper
    var lotteryTicketsAdapter: LotteryTicketsAdapter? = null
    var isNewActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeDependencyInjector()
        isNewActivity = (savedInstanceState == null)
        initUI()

        mAnalyticsHelper.logContentView("Main view", "Output", "main-view")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_feedback -> {
                FeedbackUtils.askForFeedback(this)
                return true
            }
            R.id.action_about -> return true
            R.id.action_sort_by_label -> return true
            R.id.action_sort_by_number -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initializeDependencyInjector() {
        appComponent.inject(this)
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        initRecyclerView()
        initRefreshLayout()

//        fab.setOnClickListener { view ->
//            presenter.onEditCreditCardClick()
//        }
    }

    private fun initRecyclerView() {
        lotteryTicketsAdapter = LotteryTicketsAdapter(presenter)
        lotteryTicketsListView.layoutManager = LinearLayoutManager(this)
        lotteryTicketsListView.adapter = lotteryTicketsAdapter
    }

    private fun initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this)
        lotteryTicketsListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {}

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val topRowVerticalPosition = if (recyclerView == null || recyclerView.childCount == 0)
                    0 else recyclerView.getChildAt(0).top
                swipeRefreshLayout.isEnabled = topRowVerticalPosition >= 0
            }
        })
    }

    override fun showLotteryTickets(lotteryTickets: List<LotteryTicket>) {
        lotteryTicketsAdapter?.lotteryTickets = lotteryTickets
    }

    override fun showGetLotteryTicketsError() {
        toast("Get lottery ticket error")
    }

    override fun showNewLotteryTicketForm() {
        TODO("not implemented")
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onRefresh() {
        presenter.onRefreshClick()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this, isNewActivity)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}