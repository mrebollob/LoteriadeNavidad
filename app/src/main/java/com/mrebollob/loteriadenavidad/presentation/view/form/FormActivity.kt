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

package com.mrebollob.loteriadenavidad.presentation.view.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.domain.entities.Color
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.form.FormPresenter
import com.mrebollob.loteriadenavidad.presentation.view.BaseActivity
import com.mrebollob.loteriadenavidad.utils.analytics.AnalyticsHelper
import com.mrebollob.loteriadenavidad.utils.extensions.isNotBlank
import com.mrebollob.loteriadenavidad.utils.extensions.toFloat
import com.mrebollob.loteriadenavidad.utils.extensions.toInt
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class FormActivity : BaseActivity(), FormMvpView {

    @Inject lateinit var presenter: FormPresenter
    @Inject lateinit var analyticsHelper: AnalyticsHelper
    var isNewActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        initializeDependencyInjector()
        isNewActivity = (savedInstanceState == null)

        initUI()
        analyticsHelper.logContentView("Form view", "Input", "form-view", "Is new", "todo")
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener({ onBackPressed() })

        showInputMethod(labelEditText)

        itemRedColor.setOnClickListener { changeColor(Color.RED) }
        itemBlueColor.setOnClickListener { changeColor(Color.BLUE) }
        itemGreenColor.setOnClickListener { changeColor(Color.GREEN) }
        itemWhiteColor.setOnClickListener { changeColor(Color.WHITE) }
        itemYellowColor.setOnClickListener { changeColor(Color.YELLOW) }
    }

    fun showInputMethod(view: View) {
        Handler().postDelayed({
            view.isFocusableInTouchMode = true
            view.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }, 200)
    }

    private fun initializeDependencyInjector() {
        appComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_done, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_done) {
            presenter.let {
                if (labelEditText.isNotBlank() && numberEditText.isNotBlank()
                        && betEditText.isNotBlank()) {

                    it.createOrUpdateLotteryTicket(
                            label = labelEditText.text.toString(),
                            number = numberEditText.text.toInt(),
                            bet = betEditText.text.toFloat())
                }
            }
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun changeColor(color: Color) = presenter.let { it.updateColor(color) }

    override fun onStart() {
        super.onStart()
        presenter.lotteryTicketId = getCardId()
        presenter.attachView(this, isNewActivity)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun showlotteryTicket(lotteryTicket: LotteryTicket) {
        TODO("not implemented")
    }

    override fun showLotteryTickets() {
        TODO("not implemented")
    }

    override fun showCreateLotteryTicketError() {
        TODO("not implemented")
    }

    override fun showUpdateLotteryTicketError() {
        TODO("not implemented")
    }

    private fun getCardId(): String {
        return intent.getStringExtra(EXTRA_LOTTERY_TICKET_ID)
    }

    companion object Navigator {

        val EXTRA_LOTTERY_TICKET_ID = "extra_lottery_ticket_id"

        fun open(context: Context) {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra(EXTRA_LOTTERY_TICKET_ID, "")
            context.startActivity(intent)
        }

        fun open(context: Context, lotteryTicketId: String) {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra(EXTRA_LOTTERY_TICKET_ID, lotteryTicketId)
            context.startActivity(intent)
        }
    }
}