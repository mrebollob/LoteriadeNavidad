package com.mrebollob.loteriadenavidad.app.modules.lotteryticketform;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.ui.BaseActivity;
import com.mrebollob.loteriadenavidad.app.util.DecimalDigitsInputFilter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class LotteryTicketFormActivity extends BaseActivity implements LotteryTicketFormView {

    public static final String LOTTERY_TICKET_EXTRA = "lottery_ticket_extra";

    @Inject
    LotteryTicketFormPresenter presenter;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.et_label)
    protected EditText etLabel;
    @Bind(R.id.et_number)
    protected EditText etNumber;
    @Bind(R.id.et_bet)
    protected EditText etBet;
    @Bind(R.id.rg_lottery)
    protected RadioGroup rgLottery;

    private PresentationLotteryTicket mLotteryTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArguments();
        initUi();
    }

    private void initUi() {
        initToolbar();
        initForm();
    }

    @Override
    public int onCreateViewId() {
        return R.layout.activity_lottery_ticket_form;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new LotteryTicketFormModule(this));
    }

    private void parseArguments() {
        mLotteryTicket = getIntent().getParcelableExtra(LOTTERY_TICKET_EXTRA);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initForm() {

        etBet.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});

        if (mLotteryTicket != null) {
            Resources res = getResources();
            etLabel.setText(mLotteryTicket.getLabel());

            String numberText = String.format(res.getString(R.string.number_format), mLotteryTicket.getNumber());
            etNumber.setText(numberText);

            String numberBet = "" + mLotteryTicket.getBet();
            etBet.setText(numberBet);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lottery_ticket_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveLotteryTicket();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    private void saveLotteryTicket() {

        etLabel.setError(null);
        etNumber.setError(null);
        etBet.setError(null);

        String label = etLabel.getText().toString();
        String number = etNumber.getText().toString();
        String bet = etBet.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(label)) {
            etLabel.setError(getString(R.string.error_field_required));
            focusView = etLabel;
            cancel = true;
        }

        if (TextUtils.isEmpty(number)) {
            etNumber.setError(getString(R.string.error_field_required));
            focusView = etNumber;
            cancel = true;
        }

        if (TextUtils.isEmpty(bet)) {
            etBet.setError(getString(R.string.error_field_required));
            focusView = etBet;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            if (mLotteryTicket == null) mLotteryTicket = new PresentationLotteryTicket();

            mLotteryTicket.setLabel(label);
            mLotteryTicket.setNumber(Integer.parseInt(number));
            mLotteryTicket.setBet(Float.parseFloat(bet));

            if (rgLottery.getCheckedRadioButtonId() == R.id.rb_christmas_lottery)
                mLotteryTicket.setLotteryType(PresentationLotteryType.CHRISTMAS);
            else if (rgLottery.getCheckedRadioButtonId() == R.id.rb_child_lottery)
                mLotteryTicket.setLotteryType(PresentationLotteryType.CHILD);

            if (mLotteryTicket.getId() == 0)
                presenter.createLotteryTicket(mLotteryTicket);
            else
                presenter.updateLotteryTicket(mLotteryTicket);
        }
    }

    @Override
    public void showCreateOrUpdateLotteryTicketSuccess() {
        finish();
    }

    @Override
    public void showCreateLotteryTicketError() {

    }

    @Override
    public void showUpdateLotteryTicketError() {

    }
}
